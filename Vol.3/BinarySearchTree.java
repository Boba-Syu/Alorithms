/**
 * 二叉排序树
 */
public class BinarySearchTree {
    private Node root; //二叉树的根结点

    private class Node {
        private int key; // 键
        private int val; // 值
        private Node left, right; // 左右子树
        private int N; // 以该结点为根的子树中的结点总数

        public Node(int key, int val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() { // 返回输的大小
        return size(root);
    }

    private int size(Node x) { // 返回指定结点的大小
        return x.N;
    }

    public int get(int key) { // 获得指定键对应的值
        return get(root, key);
    }
    private iny get(Node x, int key) { // 获得指定树中键对应的值
        if(x == null)
            return null;
        if(key < x.key)
            return get(x.left, key);
        if(key > x.key)
            return get(x.right, key);
        else    
            return x.val;
    }

    public void put(int key, int val) { // 插入新结点
        root = put(root, key, val);
    }

    private Node put(Node x, int key, int val) { // 在指定树中插入新结点
        if(x == null)
            return new Node(key, val, 1);
        if(key < x.key)
            x.left = put(x.left, key, val);
        else if(key > x.key)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int min() { // 找出键最小的结点
        return min(root).key;
    }

    private Node min(Node x) { // 找出指定树中键最小的结点
        if(x.left == null)
            return x;
        return min(x.left);
    }

    public int floor(int key) { // 找出不大于指定键的最大键结点
        Node x = floor(root, key);
        if (x == null)
            return -1;
        return x.key;
    }

    private Node floor(Node x, int key) { // 找出指定树中不大于指定键的最大节点
        if(x == null) 
            return null;
        if(key < x.key)
            return floor(x.left, key);
        else if(key == x.key) 
            return x;
        Node t = floor(x.right, key);
        if(t != null)
            return t;
        else 
            return x;
    }

    public int select(int k) { //返回排名为k的结点
        return select(root, k).key;
    }

    private Node select(Node x, int k) { // 返回指定树中排名为k的结点
        if(x == null)
            return null;
        int t = size(x.left);
        if(t > k)
            return select(x.left, k);
        else if(t < k) 
            return select(x.right, k);
        else 
            return x;
    }

    public int rank(int key) { // 返回树中键小于key的结点数量
        return rank(root, key);
    }

    private int rank(Node x, int key) { // 返回指定树中键小于key的结点数量
        if(x == null)
            return 0;
        if(key < x.key)
            return rank(x.left, key);
        else if(key < x.key)
            return 1+size(x.left)+ rank(x.right, key);
        else return size(x.left);
    }

    public void deleteMin() { // 删除最小结点
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) { // 删除指定树中的最小结点
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(int key) { // 删除键为key的结点
        root = delete(root ,key);
    }

    private Node delete(Node x, int key) { //删除指定树中键为key的结点
        if(x == null)
            return null;
        if(key <x.key) 
            x.left = delete(x.left, key);
        else if(key >x.key) 
            x.right = delete(x.right, key);
        else {
            if(x.right == null)
                return x.left;
            if(x.left == null)
                return x.right;
            x. min(t.right);
            x.right = delete(t.right);
            x.left = t.left;
        }
        x.N = size(x.size) + size(x.right) + 1;
        return x;
    }
}