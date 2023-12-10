package avltree;

import binarysearchtree.BinarySearchTree;
import binarysearchtree.ITree;
import binarysearchtree.Node;

/**
 * @author: DoubleW2w
 * @description: AVL 树
 * 当二叉树的左右分支树高差不为1时，需要进行左旋或者右旋，来调衡树高。
 * @date: 2023/12/9 22:36
 * @project: hello-java-algo
 */
public class AVLTree extends BinarySearchTree implements ITree {
    @Override
    public Node insert(int e) {
        Node addNode = super.insert(e);
        rebalance(addNode);
        return addNode;
    }

    @Override
    public Node delete(int e) {
        Node delNode = search(e);
        if (delNode != null) {
            Node delNodeSuccess = super.delete(delNode);
            if (delNodeSuccess != null) {
                Node min = delNodeSuccess.right != null
                           ? getMiniNode(delNodeSuccess.right)
                           : delNodeSuccess;
                recomputeHeight(min);
                rebalance(min);
            } else {
                recomputeHeight(delNodeSuccess.parent);
                rebalance(delNodeSuccess.parent);
            }
            return delNodeSuccess;
        }
        return null;
    }

    @Override
    public Node search(int e) {
        Node node = root;
        while (node != null && node.value != null && node.value != e) {
            node = e < node.value
                   ? node.left
                   : node.right;
        }
        return node;
    }

    /**
     * 重新计算节点高度
     *
     * @param node 节点
     */
    private void recomputeHeight(Node node) {
        while (node != null) {
            node.height = maxHeight(node.left, node.right) + 1;
            node = node.parent;
        }
    }

    /**
     * 两个节点中最大的高度
     *
     * @param node1 节点1
     * @param node2 节点2
     * @return 返回两个节点中最大的高度，如果节点都不存在则返回-1
     */
    private int maxHeight(Node node1, Node node2) {
        if (node1 != null && node2 != null) {
            return Math.max(node1.height, node2.height);
        } else if (node1 == null) {
            return node2 != null
                   ? node2.height
                   : -1;
        } else if (node2 == null) {
            return node1 != null
                   ? node1.height
                   : -1;
        }
        return -1;
    }

    /**
     * 平衡树
     *
     * @param node 进行平衡的节点
     */
    private void rebalance(Node node) {
        while (node != null) {
            Node parent = node.parent;
            // 左子树高度
            int leftHeight = (node.left == null)
                             ? -1
                             : (node.left).height;
            // 右子树高度
            int rightHeight = (node.right == null)
                              ? -1
                              : (node.right).height;
            // 平衡因子
            int factor = leftHeight - rightHeight;

            switch (factor) {
                // 右旋
                case 2:
                    if (factor(node.left) >= 0) {
                        // 直接右旋
                        Node temp = super.rotateRightNew(node);
                        refreshHeight(temp.right);
                        refreshHeight(temp);
                    } else {
                        // 先左旋右右旋
                        Node temp = super.rotateLeftOld(node.left);
                        refreshHeight(temp.left);
                        refreshHeight(temp);
                        node.left = temp;

                        temp = super.rotateRightNew(node);
                        refreshHeight(temp.right);
                        refreshHeight(temp);
                    }
                    break;
                // 左旋
                case -2:
                    if (factor(node.right) <= 0) {
                        Node temp = super.rotateLeftOld(node);
                        refreshHeight(temp.left);
                        refreshHeight(temp);
                    } else {
                        // 先右旋后左旋
                        Node temp = super.rotateRightNew(node.right);
                        refreshHeight(temp.right);
                        refreshHeight(temp);
                        node.right = temp;

                        temp = super.rotateLeftOld(node);
                        refreshHeight(temp.left);
                        refreshHeight(temp);
                    }
                    break;
                default:
                    refreshHeight(node);
                    break;
            }
            node = parent;
        }
    }

    /**
     * 节点的高度=Max(左子树高度,右子树高度)+1
     *
     * @param node
     */
    private void refreshHeight(Node node) {
        int leftHeight = (node.left == null)
                         ? -1
                         : (node.left).height;
        int rightHeight = (node.right == null)
                          ? -1
                          : (node.right).height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * 左右子树的平衡因子
     *
     * @param node 二叉树
     * @return 平衡因子
     */
    private int factor(Node node) {
        if(node == null){
            return 0;
        }
        int leftHeight = (node.left == null)
                         ? -1
                         : (node.left).height;
        int rightHeight = (node.right == null)
                          ? -1
                          : (node.right).height;
        return leftHeight - rightHeight;
    }
}
