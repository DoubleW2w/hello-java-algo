package heap;

/**
 * @author: DoubleW2w
 * @description: 堆接口定义
 * Java泛型中的标记符含义：
 * E - Element （元素,在集合中使用）
 * @date: 2023/12/8 14:31
 * @project: hello-java-algo
 */
public interface IHeap<E> {
    boolean add(E e);

    boolean offer(E e);

    E poll();

    E peek();
}
