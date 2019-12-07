package Vol_4;

/**
 * 有向图的DFS
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.getV()];
        dfs(g, s);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    /**
     * 顶点 s 是否可到达顶点 v
     *
     * @param v
     * @return
     */
    public boolean hasPathTo(int v) {
        return this.marked[v];
    }
}
