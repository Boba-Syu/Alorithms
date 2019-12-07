package Vol_4;

import java.util.ArrayList;
import java.util.List;

/**
 * 有向环检测
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    /**
     * 有向环中的所有顶点
     */
    private List<Integer> cycle;
    /**
     * 递归调用中栈上的所有顶点
     */
    private boolean[] onStack;

    public DirectedCycle(Digraph g) {
        this.onStack = new boolean[g.getV()];
        this.edgeTo = new int[g.getV()];
        this.marked = new boolean[g.getV()];
        for (int v = 0; v < g.getV(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (this.hasCycle()) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                this.cycle = new ArrayList<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.add(x);
                }
                cycle.add(w);
                cycle.add(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
