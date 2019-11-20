package Vol_4;

import java.util.ArrayList;
import java.util.List;

/**
 * 广度优先遍历
 */
public class BreadthFirstPaths {
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

    public BreadthFirstPaths(Graph g, int s) {
        this.marked = new boolean[g.getV()];
        this.edgeTo = new int[g.getV()];
        this.s = s;
        bfs(g, s);
    }

    private void bfs(Graph g, int s) {
        List<Integer> list = new ArrayList<>();
        marked[s] = true;
        list.add(s);
        while (!list.isEmpty()) {
            int last = list.size() - 1;
            int v = list.get(last);
            list.remove(last);
            for (int w : g.adj(v)) {
                if (marked[w]) {
                    edgeTo[w] = v;
                    marked[w] = true;
                    list.add(w);
                }
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
