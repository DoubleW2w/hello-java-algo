package sort_algo.quick_sort;

import java.util.Arrays;

/**
 * @author: DoubleW2w
 * @description: 快速排序
 * @date: 2023/12/21 18:26
 * @project: hello-java-algo
 */
public class QuickSort {

    public static void quickSort(int[] a, int l, int r) {
        int n = a.length;
        if (n == 0) {
            return;
        }
        if (l < r) {
            int i, j, x;
            i = l;
            j = r;
            // 基准值
            x = a[i];
            while (i < j) {
                while(i < j && a[j] > x) {
	                j--; // 从右向左找第一个小于x的数
                }
                if(i < j) {
                    // 以覆盖的方式，第一次覆盖的是基准值，但x已经记住了基准值了
	                a[i++] = a[j];
                }
                while(i < j && a[i] < x) {
                    i++; // 从左向右找第一个大于x的数
                }
                if(i < j) {
                    a[j--] = a[i];
                }
            }
            // 最后放置基准值的位置
            a[i] = x;
            // 在这个分区退出之后，该基准就处于数列的中间位置。
            // 左半分区
            quickSort(a, l, i - 1);
            // 右半分区
            quickSort(a, i + 1, r);
        }
    }
}

