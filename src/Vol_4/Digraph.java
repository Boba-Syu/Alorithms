package Vol_4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 有向图
 */
public class Digraph {
    /**
     * 顶点总数
     */
    private final int v;
    /**
     * 边总数
     */
    private int e;
    /**
     * 邻接表
     * key: 有向边的起点
     * value: 起点为 key 的有向边终点的集合
     */
    private Map<Integer, Set<Integer>> adj;

    public Digraph(int v) {
        this.v = v;
        this.e = 0;
        adj = new HashMap<>();
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return this.e;
    }

    /**
     * 添加从 v 到 w 的边
     *
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        this.e++;
    }

    /**
     * 返回起点为 v 的边的终点的集合
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }

    /**
     * 返回该图的反向图
     * @return
     */
    public Digraph reverse() {
        Digraph r = new Digraph(this.v);
        for (int i = 0; i < this.v; i++) {
            for (int w : adj(i)) {
                r.addEdge(w, i);
            }
        }
        return r;
    }
}
