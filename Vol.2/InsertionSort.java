/*
* 插入排序(带哨兵)
* a[0]为哨兵
*/
public class InsertionSort{
    public static void main(String[] args) {
        int a[] = new int[]{-1, 2, 4, 1, 5, 6, 8, 0, 7, 3, 9};
        for(int i = 1; i < 11; i++)
            System.out.print(a[i] + " ");
        System.out.println();

        for(int i = 2; i < 10; i++) {
            a[0] = a[i];
            int j = i;
            for(; a[j] < a[j-1]; j--) {
                a[j] = a[j-1];
            }
            a[j-1] = a[0];
        }

        for(int i = 1; i < 11; i++)
            System.out.print(a[i] + " ");
        System.out.println();
    }
}