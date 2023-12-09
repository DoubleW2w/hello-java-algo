package binarysearchtree;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/9 15:13
 * @project: hello-java-algo
 */
public interface ITree {
    /**
     * 插入节点
     */
    Node insert(int e);

    /**
     * 删除节点
     */
    Node delete(int e);

    /**
     * 搜索节点
     */
    Node search(int e);
}
