package Vol_4;

/**
 * 使用深度优先找出图中所有的连通分量
 */
public class CC {
    private boolean[] marked;
    /**
     * v所在连通分量标识符
     */
    private int[] id;
    /**
     * 连通分量数
     */
    private int count;

    public CC(Graph g) {
        id = new int[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int i : g.adj(v)) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int getId(int v) {
        return id[v];
    }

    public int getCount() {
        return this.count;
    }
}
