/*
* 并查集实现程序
* 使用路径压缩的加权quit-union算法
*/
public class UF {
    private int[] id;
    private int count; //连通图的个数
    private int sz[];

    public UF(int N) { // 构造方法
        id = new int[N];
        sz = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
        count = N;
    }

    public void union( int p, int q) { // 连接两个点
            int rp = find(p);
            int rq = find(q);
            if(rq == rp)
                return;
            if(sz[rp] < sz[rq]) {
                id[rp] = rq;
                sz[rq] += sz[rp];
            } else {
                id[rq] = rp;
                sz[rp] += sz[rq];
            }
            count--;
    }

    public  int find(int i) { // 查找根节点
        while(id[i] != i)
            i = id[i];
        int j = i;
        while(id[j] != j) {
            int tmp = j;
            j = id[j];
            id[tmp] = i; // 将其直接与根节点相连
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
        for(int i : id) {
            str += i+ " ";
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
