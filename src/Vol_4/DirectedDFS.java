package Vol_4;

import java.util.ArrayList;
import java.util.List;

/**
 * 有向图的DFS
 */
public class DirectedDFS {
    private boolean[] marked;
    private int s;
    private int[] edgeTo;

    public DirectedDFS(Digraph g, int s) {
        marked = new boolean[g.getV()];
        edgeTo = new int[g.getV()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[v] = w;
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
