/*
* 希尔排序
*/
public class ShellSort {
    public static void main(String[] args) {
        int a[] = new int[]{2, 4, 1, 5, 6, 8, 0, 7, 3, 9};
        for(int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");
        System.out.println();

        int h = 10 / 3;
        while (h >= 1) {
            for(int i = 0; i < 10; i += h) {
                for(int j = i; j >= h; j -= h) {
                    if(a[j] >= a[j-h])
                        break;
                    int tmp = a[j];
                    a[j] = a[j-h];
                    a[j-h] = tmp;
                }
            }
            h--;
        }

        for(int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
}