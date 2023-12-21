package sort_algo.bubble_sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sort_algo.BaseSortAlgoTest;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/21 18:02
 * @project: hello-java-algo
 */
public class BubbleSortTest extends BaseSortAlgoTest {

    @Test
    void testSortOne() {
        int[] a = {20, 40, 30, 10, 60, 50};
        sortBeforePrint(a);
        BubbleSort.sortOne(a);
        sortAfterPrint(a);
        int[] expected = {10,20,30,40,50,60};
        Assertions.assertArrayEquals(expected,a);
    }

    @Test
    void testSortTwo() {
        int[] a = {20, 40, 30, 10, 60, 50};
        sortBeforePrint(a);
        BubbleSort.sortOne(a);
        sortAfterPrint(a);
        int[] expected = {10,20,30,40,50,60};
        Assertions.assertArrayEquals(expected,a);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: https://weirddev.com/forum#!/testme