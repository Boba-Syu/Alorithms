public class QuickSort {
    public static void main(String[] args) {
        int a[] = new int[]{2,1,5,7,9,0,6,4,3,8};
        for(int i = 0; i < 10; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();

        //sort(a, 0, 9);
        quick3way(a, 0, 9);
        for(int i = 0; i < 10; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static void sort(int[] a, int lo, int hi) {
        if(hi <= lo) return;
        int j =  partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }    

    private static int partition(int[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        int v = a[lo];
        while(true) {
            while(a[++i] < v) 
                if(i == hi) break;
            while(a[--j] > v) 
                if(j == lo) break;
            if(i >= j) break;
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        int tmp = a[lo];
        a[lo] = a[j];
        a[j] = tmp;
        return j;
    }

    private static void quick3way(int[] a, int lo, int hi) {
        if(hi <= lo)
            return;
        int lt = lo, i = lo+1, gt = hi;
        int v = a[lo];
        while(i <= gt) {
            if(a[i] < v) {
                int tmp = a[i];
                a[i++] = a[lt];
                a[lt++] = tmp;
            } else if(a[i] > v) {
                int tmp = a[i]; 
                a[i] = a[gt];
                a[gt--] = tmp;
            } else i++;
        }
        quick3way(a, lo, lt-1);
        quick3way(a, gt+1, hi);
    }
}