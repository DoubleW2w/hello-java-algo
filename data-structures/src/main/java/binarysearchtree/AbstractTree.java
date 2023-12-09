package binarysearchtree;

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
            printTree(node.right, true, indent + (isRight ? "        " : " |      "), tree);
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
            printTree(node.left, false, indent + (isRight ? " |      " : "        "), tree);
        }
    }

    private void printNodeValue(Node node, StringBuilder tree) {
        if (null == node.value) {
            tree.append("<NIL>");
        } else {
            tree.append(node.value);
        }
        tree.append("\r\n");
    }
}
