package sort_algo.insertion_sort;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/22 16:14
 * @project: hello-java-algo
 */
public class InsertionSort {
	public static void insertSort(int[] a) {

		int n = a.length;
		int i, j, k;

		for (i = 1; i < n; i++) {

			//为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
			for (j = i - 1; j >= 0; j--) {
				if (a[j] < a[i]) {
					System.out.println(StrUtil.format("{}", Arrays.toString(a)));
					break;
				}
			}

			//如找到了一个合适的位置
			if (j != i - 1) {
				//将比a[i]大的数据向后移
				int temp = a[i];
				for (k = i - 1; k > j; k--) {
					a[k + 1] = a[k];
					System.out.println(StrUtil.format("{}", Arrays.toString(a)));
				}
				//将a[i]放到正确位置上
				a[k + 1] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[] a = {20, 40, 30, 10, 60, 50};
		insertSort(a);
		System.out.println(StrUtil.format("{}", Arrays.toString(a)));
	}
}
