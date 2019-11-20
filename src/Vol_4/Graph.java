package Vol_4;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无向图
 */
public class Graph {
    /**
     * 顶点的数目
     */
    private final int V;
    /**
     * 边的数目
     */
    private int E;
    /**
     * 邻接表
     */
    private Map<Integer, Set<Integer>> adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        for (int i = 0; i < V; i++) {
            adj.put(i, new HashSet<>());
        }
    }

    public int getV() {
        return this.V;
    }

    public int getE() {
        return this.E;
    }

    /**
     * 添加边
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
        this.E++;
    }

    public Iterable<Integer> adj(int v) {
        return this.adj.get(v);
    }
}
