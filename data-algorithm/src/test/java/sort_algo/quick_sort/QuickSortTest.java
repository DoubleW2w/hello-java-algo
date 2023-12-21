package sort_algo.quick_sort;

import org.junit.jupiter.api.Test;
import sort_algo.BaseSortAlgoTest;
import sort_algo.bubble_sort.BubbleSort;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/21 18:54
 * @project: hello-java-algo
 */
class QuickSortTest extends BaseSortAlgoTest {

	@Test
	void testQuickSort() {
//		int[] a = new int[]{58,1,3,44,6,69,62,27,36,71,45,60,11,27,77,10,12,63,98,5,34,50,53,14,62,35,11,92,37,10,21, 94,87,13,54,47,69};
		int[] a = new int[]{58,1,3,44,6,69,62,27,92,37};
		sortBeforePrint(a);
		QuickSort.quickSort(a, 0, a.length-1);
		sortAfterPrint(a);
	}
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: https://weirddev.com/forum#!/testme