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

    public static void quickSortTwo(int[] a,int l,int r){
        if (l < r) {
            int i = partition(a, l, r);
            quickSortTwo(a, l, i - 1);
            quickSortTwo(a, i + 1, r);
        }
    }

    private static int partition(int[] a, int l, int r) {
        // 基准值
        int pivot = a[r];
        // 左指针
        int i = l - 1;
        for (int j = l; j < r; j++) {
            // 从左到右找到第一个小于基准值的元素
            if (a[j] < pivot) {
                i++;
                swap(a, i, j);
            }
        }

        swap(a, i + 1, r);
        return i + 1;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }



    public static void quickSort(Integer[] arr, int left, int right) {
        int l, r, s;
        while (right > left) {
            l = left;
            r = right;
            s = arr[left];
            while (l < r) {
                while (arr[r] > s) {
                    r--;
                }
                arr[l] = arr[r];
                while ( arr[l] <= s && l < r) {
                    l++;
                }
                arr[r] = arr[l];
            }
            arr[l] = s;
            quickSort(arr, left, l - 1);
            left = l + 1;
        }
    }
}

