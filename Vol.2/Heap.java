/**
 * 用二叉堆实现优先队列
 */
public class Heap {
    int[] a;
    int N;

    public void exch(int i, int j) { // 交换元素a[i]和a[j]
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public void sink(int i) { // 下潜
        while (i*2 < N) {
            int j = i*2;
            if (j < N-1 && a[j] < a[j+1])
                j++;
            if(a[i] < a[j]) 
                break;
            exch(i, j);
            i = j;
        }
    }

    public void swim(int i) { // 上浮
        while(i > 1 && a[i] > a[i/2]) {
            exch(i, i/2);
            i /= 2;
        }
    }

    public int getTop() { // 返回最大值
        return a[0];
    }

    public void add(int n) { // 添加元素
        int b[] = new int[N+1];
        for (int i = 0; i < N; i++) {
            b[i] = a[i];
        }
        b[N++] = n;
        a = b;
        swim(n);
    }

    public void delTop() { //删除最大值
        a[0] = a[--N];
        sink(0);
    }
}