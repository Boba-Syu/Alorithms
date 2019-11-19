package Vol_4;

/**
 * 深度优先遍历
 */
public class DepthFirstSearch {
    private boolean[] market;
    private int count;

    /**
     * 找出图g中所有和定点s相连的顶点
     *
     * @param g
     * @param s
     */
    public DepthFirstSearch(Graph g, int s) {
        market = new boolean[g.getV()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        market[v] = true;
        count++;
        for (int w : g.adj()) {
            if (!market[w]) {
                dfs(g, w);
            }
        }
    }

    /**
     * 判断s和v是否相连接
     *
     * @param w
     * @return
     */
    public boolean marked(int w) {
        return market[w];
    }

    /**
     * 返回和s相连接的顶点数目
     *
     * @return
     */
    public int getCount() {
        return count;
    }

}
