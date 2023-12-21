package sort_algo.bubble_sort;

/**
 * @author: DoubleW2w
 * @description: 冒泡排序
 * @date: 2023/12/21 17:55
 * @project: hello-java-algo
 */
public class BubbleSort {
    /**
     * 冒泡排序
     * @param a
     */
    public static void sortOne(int[] a) {
        if (a.length == 0) {
            return;
        }
        int n = a.length;
        for (int i = n - 1; i > 0; i--) {
            // 将a[0...i]中最大的数据放在末尾
            for (int j = 0; j < i; j++) {
                // 符合交换条件
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }


    /**
     * 冒泡排序改进版
     * @param a
     */
    public static void sortTwo(int[] a) {
        int i, j;
        // 标记
        int flag;
        int n = a.length;
        if (n == 0) {
            return;
        }
        for (i = n - 1; i > 0; i--) {
            // 初始化标记为0
            flag = 0;
            // 将a[0...i]中最大的数据放在末尾
            for (j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    // 交换a[j]和a[j+1]
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    // 若发生交换，则设标记为1
                    flag = 1;
                }
            }
            if (flag==0) {
                // 若没发生交换，则说明数列已有序。
                break;
            }
        }
    }
}
