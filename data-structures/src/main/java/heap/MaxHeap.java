package heap;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/8 17:23
 * @project: hello-java-algo
 */
public class MaxHeap extends Heap<Integer> {

    /**
     *
     * @param firstElement 第一个数
     * @param secondElement 第二个数
     * @return firstElement = secondElement 返回0
     *         firstElement < secondElement 返回正数
     *         firstElement > secondElement 返回负数
     */
    @Override
    public int compareTo(Integer firstElement, Integer secondElement) {
        return secondElement.compareTo(firstElement);
    }
}
