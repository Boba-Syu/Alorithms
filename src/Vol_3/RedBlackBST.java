package Vol_3;

import java.util.ArrayList;
import java.util.List;

/**
 * 红黑树
 * <p>
 * 红链接将两个2-结点连接起来构成3-结点
 * 3-结点表示为一条左斜的红色链接, 红链接均为左连接
 * 没有任何一个结点同时和两条红链接相连
 * 任意空结点到根结点路径上的黑链接数量都相等
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int N;
        /**
         * 由父结点指向它的链接的颜色
         */
        boolean color;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /**
     * 左旋
     * 将一个结点的右结点作为自己的父结点
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    /**
     * 右旋
     * 将一个结点的左结点作为自己的父结点
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = RED;
    }

    public int size() {
        return size(root);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    public Key min() {
        return min(root).key;
    }

    public Key max() {
        return max(root).key;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (root != null) {
            root.color = BLACK;
        }
    }

    public void deleteMax() {
        if (!isRed(root.left)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (root != null) {
            root.color = BLACK;
        }
    }

    public void delete(Key key) {
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (root != null) {
            root.color = BLACK;
        }
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new ArrayList<>();
        keys(root, list, lo, hi);
        return list;
    }


    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    private Node put(Node h, Key key, Value value) {
        if (h == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            h.right = put(h.right, key, value);
        } else {
            h.value = value;
        }

        // 红黑树结构调整
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        return min(x.left);
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        return max(x.right);
    }

    /**
     * 大于等于key的最小键
     *
     * @param x
     * @param key
     * @return
     */
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, key);
        } else if (cmp == 0) {
            return x;
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        }
        return x;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        }
        return x;
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return rank(key, x.right) + size(x.left) + 1;
        }
        return size(x.left);
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) {
            h = rotateRight(h);
        }
        if (h.right == null) {
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }
        h.right = deleteMax(h);
        return balance(h);
    }

    private Node deleteMin(Node h) {
        if (h.left == null) {
            return h.right;
        }
        if (!isRed(h)) {
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        h.N = size(h.left) + size(h.right) + 1;
        return balance(h);
    }

    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
                h.left = delete(h.left, key);
            }
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (key.compareTo(h.key) == 0 && (h.right == null)) {
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.right)) {
                h = moveRedRight(h);
            }
            if (key.compareTo(h.key) == 0) {
                h.value = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    private void keys(Node x, List<Key> list, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0) {
            keys(x.left, list, lo, hi);
        }
        if (cmpLo <= 0 && cmpHi >= 0) {
            list.add(x.key);
        }
        if (cmpHi > 0) {
            keys(x.right, list, lo, hi);
        }
    }
}
