package Vol_4;

/**
 * 用深度优先判断是否非无环图
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph g) {
        marked = new boolean[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            if (!marked[i]) {
                dfs(g, i, i);
            }
        }
    }

    private void dfs(Graph g, int s, int w) {
        marked[s] = true;
        for (int i : g.adj(s)) {
            if (!marked[i]) {
                dfs(g, i, s);
            } else if (i != w) {
                this.hasCycle = true;
            }
        }
    }

    public boolean isHasCycle() {
        return this.hasCycle;
    }
}
