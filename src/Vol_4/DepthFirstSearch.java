package Vol_4;

import java.util.ArrayList;
import java.util.List;

/**
 * 深度优先遍历
 */
public class DepthFirstSearch {
    /**
     * 该顶点是否调用了dfs()
     */
    private boolean[] marked;
    /**
     * 从起点到一个顶点的已知路径上的最后一个顶点
     */
    private int[] edgeTo;
    /**
     * 起点
     */
    private int s;
    /**
     * 与起点相连接的顶点数
     */
    private int count;

    /**
     * 找出图g中所有和定点s相连的顶点
     *
     * @param g
     * @param s
     */
    public DepthFirstSearch(Graph g, int s) {
        this.marked = new boolean[g.getV()];
        this.edgeTo = new int[g.getV()];
        this.s = s;
        this.count = 0;
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                this.edgeTo[w] = v;
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
    public boolean hasPathTo(int w) {
        return marked[w];
    }

    /**
     * 返回和s相连接的顶点数目
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * 获取起点s到顶点v的路径
     *
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        List<Integer> path = new ArrayList<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.add(i);
        }
        path.add(s);
        return path;
    }

}
