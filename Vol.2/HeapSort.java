public class HeapSort{
    public static void main(String[] args) {
        int a[] = new int[]{2,1,5,7,9,0,6,4,3,8};
        for(int i = 0; i < 10; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();

        for(int i = 0; i < 10; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }


    public static void hashSort(int[] a, int N) {
            for(int i = N/2; i >= 0; i--) { // 实现堆有序
                    sink(a, i, N);
            }
            for(int i = N-1; i >= 0; i--) { // 排序
                exch(a, 0, i);
                exch(a, 0, i);
            }
    }

    public static void sink(int[] a, int i, int N){ // 将小的数字下沉
            while(i*2+1 < N) {
                int j = i*2+1;
                if(j < N-1 && a[j] < a[j+1]) {
                    j++;
                }
                if(a[i] > a[j])
                    break;
                exch(a, i, j);
                i = j;
            }
    }

    public static void exch(int[] a, int i ,int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}