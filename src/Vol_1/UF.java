package Vol_1;

/*
 * 并查集实现程序
 * 使用路径压缩的加权quit-union算法
 */
public class UF {
    private int[] id;
    /**
     * 连通图的个数
     */
    private int count;
    private int sz[];

    /**
     * 构造方法
     *
     * @param N
     */
    public UF(int N) {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
        count = N;
    }

    /**
     * 连接两个点
     *
     * @param p
     * @param q
     */
    public void union(int p, int q) {
        int rp = find(p);
        int rq = find(q);
        if (rq == rp)
            return;
        if (sz[rp] < sz[rq]) {
            id[rp] = rq;
            sz[rq] += sz[rp];
        } else {
            id[rq] = rp;
            sz[rp] += sz[rq];
        }
        count--;
    }

    /**
     * 查找根节点
     *
     * @param i
     * @return
     */
    public int find(int i) {
        while (id[i] != i)
            i = id[i];
        int j = i;
        while (id[j] != j) {
            int tmp = j;
            j = id[j];
            // 将其直接与根节点相连
            id[tmp] = i;
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        String str = "";
        for (int i : id) {
            str += i + " ";
        }
        return str;

    }

    public static void main(String[] args) {
        UF uf = new UF(10);
        System.out.println("id: " + uf.toString());
        uf.union(2, 5);
        System.out.println("id: " + uf.toString());
        uf.union(3, 2);
        System.out.println("id: " + uf.toString());
        System.out.println("2--3: " + uf.connected(2, 7));
        System.out.println("2--5: " + uf.connected(2, 5));
        System.out.println("2: " + uf.find(2));
        System.out.println("7: " + uf.find(7));
        System.out.println("5: " + uf.find(5));
        System.out.println("count: " + uf.getCount());
    }

}
