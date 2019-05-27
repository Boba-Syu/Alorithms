/**
 * 红黑树
 */
public class RedBlackBST {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private int key;
        private int val;
        private Node left, right;
        private int N;
        private boolean color;
        
        Node(int key, int val, int N, int color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }
    
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x.N;
    }

    private boolean isRed(Node x) {
        if(x == null)
            return BLACK;
        return x.color == RED;
    }

    public Node rotateLeft(Node h) { // 左旋
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.H = h.H;
        h.H = 1 + size(h.left) + size(h.right);
    }

    public Node rotateRight(Node h) { // 右旋
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.H = h.H;
        h.H = 1 + size(h.left) + size(h.right);
    }

    /**
     * 将一个结点和两个红色子节点变色, 此时该以结点为根结点的数高度+1
     */
    public void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(int Key, int value) { // 修改键为key的结点的值
        root = put(root, key, value);
        root.color = BLACK;
    }

    public Node put(Node h, int key, int value) { // 在指定树中修改键为key的结点的值
        if(h == null) 
            return new Node(key, val, 1, RED);

        if(key < h.key)
            h.left = put(h.left, key, value);
        else if(key > h.key)
            h.right = put(h.right, key, value);
        else
            h.val = value;
        
        h = balance(h); // 调整红黑树的结构
        return h;
    }

    private Node balance(Node h) { // 平衡红黑树
        if(isRed(h.right) && isRed(h.left)) 
            h = rotateLeft(h);  
        if(isRed(h.left) && !isRed(h.left.left))
            h = rotateRight(h);
        if(isRed(h.left) && isRed(h.right))
            flipColors(h);
            
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void deleteMin() { // 删除最小的结点
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if(root != null)
            root.color = BLACK;
    }

    /**
     * 假设结点h为红色, h.left和h.left.left都为黑色
     * 将h.left或者h.left.left的子结点之一变红
     * @param h
     * @return
     */
    private Node moveRedLeft(Node h) { 
        flipColors(h);
        if(isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node deleteMin(Node h) { // 删除指定树中最小的结点
        if(h.left == null) 
            return null;
        if(!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if(!isRed(h.left.left))
            h = rotateRight(h);
        return h;
    }

    public void deleteMax() {
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if(root != null)
            root.color = BLACK;
    }

    private Node deleteMax(Node h) {
        if(isRed(h.left))
        h = rotateRight(h);
        if(h.right == null)
            return null;
        if(!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(int key) {
        if(!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if(root != null)
            root.color = BLACK;        
    }

    private Node delete(Node h, int key) {
        if(key < h.key) {
            if(!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if(isRed(h.left))
                h = rotateRight(h);
            if(key == h.key && h.right == null )
                return null;
            if(!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
                if(key == h.key) {
                    h.val = get(h.right, min(h.right).key);
                    h.key = min(h.right).key;
                    h.right = deleteMin(h);
                } else 
                    h.right = delete(h.right, key);
        } 
        return balance(h);
    }

}