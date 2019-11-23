package Vol_4;

/**
 * 用深度优先判断是否为二分图
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph g) {
        marked = new boolean[g.getV()];
        color = new boolean[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int i : g.adj(v)) {
            if (!marked[i]) {
                color[i] = !color[v];
                dfs(g, i);
            } else if (color[i] == color[v]) {
                this.isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return this.isTwoColorable;
    }
}
