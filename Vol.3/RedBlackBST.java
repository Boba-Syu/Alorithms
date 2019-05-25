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

    private isRed() {
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
}