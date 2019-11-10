package Vol_2;

/*
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        int a[] = new int[]{2, 4, 1, 5, 6, 8, 0, 7, 3, 9};
        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");
        System.out.println();

        for (int i = 0; i < 10; i++) {
            int min = i;
            for (int j = i + 1; j < 10; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            int tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
        }

        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }

}