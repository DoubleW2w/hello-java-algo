package binarysearchtree;

import avltree.AVLTree;
import redblacktree.RedBlackTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/9 15:36
 * @project: hello-java-algo
 */
public abstract class AbstractTree {
    public Node root;
    public List<Integer> elementList = new ArrayList<>();

    protected static final Node nilNode = new Node(RedBlackTree.class, null, null, null, null, Node.Color.BLACK);


    /**
     * 以新插入节点6，节点4不平衡，需要左旋为案例
     * /----- 6
     * /----- 5
     * /----- 4
     * 2
     * \----- 1
     * <p>
     * 左旋操作
     * <p>
     * /----- 6
     * /----- 5
     * |       \----- 4
     * 2
     * \----- 1
     * <p>
     * 步骤；
     * 1. 左旋的作用，相当于通过向上迁移树高差大于1的右子节点来降低树高的操作。
     * 2. 通过节点4拿到父节点2和右子节点5，把父节点2和右子节点5建立关联
     * 3. 节点5的左子节点，相当于是大于4的那么一个值，只不过这里不体现。那么这个节点5的左子节点，应该被迁移到节点4的右子节点上。
     * 4. 整理节点5的关系，左子节点为4。左子节点4的父节点为5
     * 5. 如果说迁移上来的节点5无父节点，那么它就是父节点 root = temp
     * 6. 迁移上来的节点5，找到原节点4是对应父节点的左子节点还是右子节点，对应的设置节点5的左右位置
     *
     * <p>旧根节点为新根节点的左子树</p>
     * <p>新根节点的左子树（如果存在）为旧根节点的右子树</p>
     */

    protected Node rotateLeft(Node node) {
        Node oldRoot = node;
        Node newRoot = node.right;
        newRoot.parent = node.parent;

        // newRoot 的左子树(如果存在)为 oldRoot 的右子树
        oldRoot.right = newRoot.left;
        if (newRoot.left != null && newRoot.left != nilNode) {
            newRoot.left.parent = oldRoot;
        }

        // oldRoot 为 newRoot 的左子树
        newRoot.left = oldRoot;
        oldRoot.parent = newRoot;

        // newRoot 替换 oldRoot 的位置
        if (newRoot.parent == null || newRoot.parent == nilNode) {
            root = newRoot;
        } else {
            if (newRoot.parent.left == oldRoot) {
                newRoot.parent.left = newRoot;
            } else {
                newRoot.parent.right = newRoot;
            }
        }

        return newRoot;
    }

    protected Node rotateLeftOld(Node node) {
        Node newRoot = node.right;
        newRoot.parent = node.parent;


        // 新根节点的左子树(如果存在)为旧根节点的右子树
        // 1. node.right = newRoot.left (建立单向连接)
        // 2. node.right.parent = node (建立连接)
        node.right = newRoot.left;
        if (node.right != null && node.right != nilNode) {
            node.right.parent = node;
        }
        // 旧根节点为新根节点的左子树
        newRoot.left = node;
        node.parent = newRoot;

        if (newRoot.parent == null || newRoot.parent == nilNode) {
            root = newRoot;
        } else {
            if (newRoot.parent.left == node) {
                newRoot.parent.left = newRoot;
            } else {
                newRoot.parent.right = newRoot;
            }
        }
        return newRoot;
    }

    protected Node rotateLeftNew(Node node) {
        Node oldRoot = node;
        Node newRoot = node.right;
        Node parent = node.parent;

        // 1.newRoot 替换 oldRoot 位置
        if (parent != null) {
            if (oldRoot.parent.value > oldRoot.value) {
                parent.left = newRoot;
            } else {
                parent.right = newRoot;
            }
        }
        newRoot.parent = parent;
        // 2.重新组装oldRoot（将newRoot的左子树给oldRoot的右子树)
        oldRoot.right = newRoot.left;
        if (newRoot.left != null) {
            newRoot.left.parent = oldRoot;
        }
        // 3. oldRoot为newRoot的左子树
        newRoot.left = oldRoot;
        oldRoot.parent = newRoot;
        return newRoot;
    }

    /**
     * 以新插入节点1，节点3不平衡，需要右旋为案例
     * <p>
     * /----- 5
     * 4
     * \----- 3
     * \----- 2
     * \----- 1
     * <p>
     * 右旋操作
     * <p>
     * /----- 5
     * 4
     * |       /----- 3
     * \----- 2
     * \----- 1
     * <p>
     * 步骤；
     * 1. 右旋的作用，相当于通过向上迁移树高差大于1的右子节点来降低树高的操作。
     * 2. 通过节点3拿到父节点4和左子节点2，把父节点7和左子节点2建立关联
     * 3. 节点2的右子节点，相当于是大于2小于3的那么一个值，只不过这里不体现。那么这个节点2的右子节点，应该被迁移到节点3的左子节点上。
     * 4. 整理节点2的关系，右子节点为3。右子节点3的父节点为2
     * 5. 如果说迁移上来的节点2无父节点，那么它就是父节点 root = temp
     * 6. 迁移上来的节点2，找到原节点3是对应父节点的左子节点还是右子节点，对应的设置节点2的左右位置
     * <p>旧根节点为新根节点的右子树</p>
     * <p>新根节点的右子树（如果存在）为旧根节点的左子树</p>
     */
    protected Node rotateRightOld(Node node) {
        Node newRoot = node.left;
        newRoot.parent = node.parent;

        node.left = newRoot.right;
        // 红黑树有空节点 nilNode
        if (node.left != null && node.left != nilNode) {
            node.left.parent = node;
        }

        newRoot.right = node;
        node.parent = newRoot;

        if (newRoot.parent == null || newRoot.parent == nilNode) {
            root = newRoot;
        } else {
            if (newRoot.parent.left == node) {
                newRoot.parent.left = newRoot;
            } else {
                newRoot.parent.right = newRoot;
            }
        }
        return newRoot;
    }

    protected Node rotateRightNew(Node node) {
        Node oldRoot = node;
        Node newRoot = node.left;
        Node parent = node.parent;
        // 1. newRoot 替换 oldRoot 的位置
        if (parent == null || parent == nilNode) {
            root = newRoot;
        } else {
            if (oldRoot.parent.value > oldRoot.value) {
                parent.left = newRoot;
            } else {
                parent.right = newRoot;
            }
        }
        newRoot.parent = parent;

        // 2. 重新组装 oldRoot（将 newRoot 的右子树给 oldRoot 的左子树）
        oldRoot.left = newRoot.right;
        if (newRoot.right != null && node.left != nilNode) {
            newRoot.right.parent = oldRoot;
        }

        // 3. oldRoot 为 newRoot 的右子树
        newRoot.right = oldRoot;
        oldRoot.parent = newRoot;
        return newRoot;
    }


    protected String printSubTree(Node node) {
        StringBuilder tree = new StringBuilder();
        if (node.right != null) {
            printTree(node.right, true, "", tree);
        }
        printNodeValue(node, tree);
        if (node.left != null) {
            printTree(node.left, false, "", tree);
        }
        return tree.toString();
    }

    private void printTree(Node node, boolean isRight, String indent, StringBuilder tree) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight
                                                  ? "        "
                                                  : " |      "), tree);
        }
        tree.append(indent);
        if (isRight) {
            tree.append(" /");
        } else {
            tree.append(" \\");
        }
        tree.append("----- ");
        printNodeValue(node, tree);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight
                                                  ? " |      "
                                                  : "        "), tree);
        }
    }

    private void printNodeValue(Node node, StringBuilder tree) {
        if (null == node.value) {
            tree.append("<NIL>");
        } else {
            tree.append(node.value);
            if (root.clazz.equals(AVLTree.class)) {
                tree.append("(").append(node.height).append(")");
            } else if (root.clazz.equals(RedBlackTree.class)) {
                tree.append("(").append(node.color == Node.Color.BLACK
                                        ? "黑"
                                        : "红").append(")");
            }
        }
        tree.append("\r\n");
    }
}
