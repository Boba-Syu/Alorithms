package Vol_4;

import java.util.LinkedList;
import java.util.List;

/**
 * 基于深度优先搜索的顶点排序
 */
public class DepthFirstOrder {
    private boolean[] marked;
    /**
     * 顶点的前序排列
     * 在递归调用之前将顶点加入队列
     */
    private List<Integer> pre;
    /**
     * 顶点的后续排列
     * 在递归调用之后将顶点加入队列
     */
    private List<Integer> post;
    /**
     * 顶点的逆后续排列
     * 在地柜调用之后将顶点压入栈
     */
    private LinkedList<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        this.pre = new LinkedList<>();
        this.post = new LinkedList<>();
        this.reversePost = new LinkedList<>();
        this.marked = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        this.pre.add(v);
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        this.post.add(v);
        this.reversePost.addFirst(v);
    }

    public Iterable<Integer> getPre() {
        return this.pre;
    }

    public Iterable<Integer> getPost() {
        return this.post;
    }

    public Iterable<Integer> getReversePost() {
        return this.reversePost;
    }
}
