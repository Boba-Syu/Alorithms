public class MergeSort {
    static int aux[]; 
    public static void main(String[] args) {
        int a[] = new int[]{2,1,5,7,9,0,6,4,3,8};
        aux = new int[a.length];
        for(int i = 0; i < 10; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();

        top_down_sort(a, 0, a.length-1);
        for(int i = 0; i < 10; i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();

    }

    public static void merge(int[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for(int k = lo; k <= hi; k++) {
            if(i > mid) // 左边元素取尽
                a[k] = aux[j++];
            else if(j > hi) // 右边元素取尽 
                a[k] = aux[i++];
            else if(aux[j] > aux[i])
                a[k] = aux[i++];
            else 
                a[k] = aux[j++];
        }
    }

    public static void top_down_sort(int[] a, int lo, int hi) {
        if(hi <= lo)
            return;
        int mid = lo + (hi-lo)/2;
        top_down_sort(a ,lo, mid);
        top_down_sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    } 

    public static void down_top_sort() {

    }
}