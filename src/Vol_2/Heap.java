package Vol_2;

/**
 * 用二叉堆实现优先队列
 */
public class Heap {
    int[] a;
    int N;

    /**
     * 交换元素a[i]和a[j]
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * 下潜
     *
     * @param i
     */
    public void sink(int i) {
        while (i * 2 < N) {
            int j = i * 2;
            if (j < N - 1 && a[j] < a[j + 1])
                j++;
            if (a[i] < a[j])
                break;
            swap(i, j);
            i = j;
        }
    }

    /**
     * 上浮
     *
     * @param i
     */
    public void swim(int i) {
        while (i > 1 && a[i] > a[i / 2]) {
            swap(i, i / 2);
            i /= 2;
        }
    }

    /**
     * 返回最大值
     *
     * @return
     */
    public int getTop() {
        return a[0];
    }

    /**
     * 添加元素
     *
     * @param n
     */
    public void add(int n) {
        int b[] = new int[N + 1];
        for (int i = 0; i < N; i++) {
            b[i] = a[i];
        }
        b[N++] = n;
        a = b;
        swim(n);
    }

    /**
     * 删除最大值
     */
    public void delTop() {
        a[0] = a[--N];
        sink(0);
    }
}