package redblacktree;


import binarysearchtree.AbstractTree;
import binarysearchtree.ITree;
import binarysearchtree.Node;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/9 22:42
 * @project: hello-java-algo
 */
public class RedBlackTree extends AbstractTree implements ITree {

    protected int size;

    @Override
    public Node insert(int e) {
        // 正常的插入节点
        Node newNode = privateInsert(e);
        // 给节点新增NIL节点
        newNode.left = nilNode;
        newNode.right = nilNode;
        root.parent = nilNode;
        // 染色+平衡
        fixInsertBalance(newNode);
        return newNode;
    }

    private void fixInsertBalance(Node current) {
        // 新增节点默认是红色
        // 1.父节点是根节点 或者 新增节点就是根节点
        if (current.parent == root || current == root) {
            // 直接插入不做任何染色+平衡操作
            root.color = Node.Color.BLACK;
        }
        // 2.父节点是黑色
        if (current.parent.color == Node.Color.BLACK) {
            // 直接插入不做任何染色+平衡操作
            root.color = Node.Color.BLACK;
        }
        // 3.除此之外，父节点不是根节点且为红色，通过染色、左旋、右旋调整树高达到平衡
        while (current.parent != root && current.parent.color == Node.Color.RED) {
            // 父亲节点
            Node parent = current.parent;
            // 爷爷节点
            Node grandParent = parent.parent;

            // ↙左倾结构；当前节点的父节点，是当前节点爷爷节点的左孩子
            if (parent == grandParent.left) {
                // 叔叔节点
                Node uncle = grandParent.right;
                // 染色
                // 1.父亲和叔叔都是红色，爷爷的颜色和（父亲和叔叔的）颜色进行对调
                if (uncle.color == Node.Color.RED) {
                    parent.color = Node.Color.BLACK;
                    uncle.color = Node.Color.BLACK;
                    grandParent.color = Node.Color.RED;
                    current = grandParent;
                }
                // 旋转
                else {
                    // 偏右↘，先左旋一次调衡
                    if (current == parent.right) {
                        current = parent;
                        super.rotateLeft(current);
                        parent = current.parent;
                    }
                    super.rotateRightNew(grandParent);
                    // 叔叔是黑色的情况
                    parent.color = Node.Color.BLACK;
                    grandParent.color = Node.Color.RED;
                }
            }
            // ↘右倾结构；当前节点的父节点，是当前节点爷爷节点的右孩子
            if (parent == grandParent.right) {
                Node uncle = grandParent.left;
                // 染色
                // 1.父亲和叔叔都是红色，爷爷的颜色和（父亲和叔叔的）颜色进行对调
                if (uncle.color == Node.Color.RED) {
                    parent.color = Node.Color.BLACK;
                    uncle.color = Node.Color.BLACK;
                    grandParent.color = Node.Color.RED;
                    current = grandParent;
                }
                // 旋转
                else {
                    // 偏左↙，先右旋一次调衡
                    if (current == parent.left) {
                        // 以当前节点为根节点进行右旋
                        current = parent;
                        super.rotateRightNew(current);
                        // 建立指向关系
                        parent = current.parent;
                    }
                    parent.color = Node.Color.BLACK;
                    grandParent.color = Node.Color.RED;
                    super.rotateLeft(grandParent);
                }
            }
        }
        // 遵循整个树的根节点是黑色
        root.color = Node.Color.BLACK;
    }

    @Override
    public Node delete(int e) {
        Node deleteNode = search(e);
        if (deleteNode != null) {
            return delete(deleteNode);
        } else {
            return null;
        }
    }

    protected Node delete(Node deleteNode) {
        Node replaceNode = null;
        if (deleteNode != null && deleteNode != nilNode) {
            Node removedOrMovedNode = deleteNode;
            Node.Color removedOrMovedNodeColor = removedOrMovedNode.color;

            if (deleteNode.left == nilNode) {
                // 1.单节点或者带有一个子节点
                replaceNode = deleteNode.right;
                rbTreeTransplant(deleteNode, deleteNode.right);
            } else if (deleteNode.right == nilNode) {
                // 2.单节点或者带有一个子节点
                replaceNode = deleteNode.left;
                rbTreeTransplant(deleteNode, deleteNode.left);
            } else {
                // 3.带有两个子节点

                removedOrMovedNode = getMinimum(deleteNode.right);
                removedOrMovedNodeColor = removedOrMovedNode.color;
                replaceNode = removedOrMovedNode.right;
                if (removedOrMovedNode.parent == deleteNode) {
                    // 待删除节点正好是父亲，则需要更新一下 父子和右子树的关系
                    replaceNode.parent = removedOrMovedNode;
                } else {
                    rbTreeTransplant(removedOrMovedNode, removedOrMovedNode.right);
                    removedOrMovedNode.right = deleteNode.right;
                    removedOrMovedNode.right.parent = removedOrMovedNode;
                }
                rbTreeTransplant(deleteNode, removedOrMovedNode);
                removedOrMovedNode.left = deleteNode.left;
                removedOrMovedNode.left.parent = removedOrMovedNode;
                removedOrMovedNode.color = deleteNode.color;
            }
            size--;
            if (removedOrMovedNodeColor == Node.Color.BLACK) {
                fixDeleteBalance(replaceNode);
            }
        }
        return null;
    }


    protected Node deleteMy(Node deleteNode) {
        Node parent = deleteNode.parent;
        // 1. 待删除节点为单个红节点（直接删除)
        if (deleteNode.color == Node.Color.RED && deleteNode.left == nilNode && deleteNode.right == nilNode) {
            if (deleteNode.value < parent.value) {
                parent.left = nilNode;
            } else {
                parent.right = nilNode;
            }
        }

        // 2. 待删除节点为单个黑节点
        // 2.1 兄弟节点是黑色
        // 2.1.1 对侄红（兄弟节点带有一个子节点，且子节点的所在树的方向跟待删除节点所在的树的方向不一致），
        // 比如  兄弟节点的子节点 对于 兄弟节点来说，该子节点在「左子树」，而待删除节点对于 其父亲来说在「右子树」
        // 2.1.2 顺侄红（兄弟节点带有一个子节点，且子节点的所在树的方向跟待删除节点所在的树的方向一致）
        // 比如  兄弟节点的子节点 对于 兄弟节点来说，该子节点在「左子树」，而待删除节点对于 其父亲来说在「左子树」
        // 2.1.3 双侄黑（兄弟节点带有两个黑色的子节点)
        // 2.2 兄弟是红节点

        // 3. 带有一个子节点(deleteNode的子节点必然是红，deleteNode必然是黑，否则不满足 「相同数量的黑色节点」）
        if ((deleteNode.left != null && deleteNode.right == null) || (deleteNode.left == null && deleteNode.right != null)) {
            // 用子节点替换deleteNode
            if (deleteNode.left != null) {
                parent.left = deleteNode.left;
                deleteNode.left.parent = parent;
                deleteNode.left.color = deleteNode.color;
            } else {
                parent.right = deleteNode.right;
                deleteNode.right.parent = parent;
                deleteNode.right.color = deleteNode.color;
            }
        }
        // 4. 带有两个子节点
        // 为了保证树的大小情况，
        // 删除节点时，要从「左子树」中找到最大的节点来替换待删除节点
        // 或者 从「右子树」中找到最小的节点来替换待删除节点
        // 这样就能保证，替换的节点能大于左子树的任何一个节点，同时又小于右子树的任何一个节点
        if (deleteNode.left != null && deleteNode.right != null) {
            Node maximum = getMaximum(deleteNode.left);
            maximum.left = deleteNode.left;
            maximum.right = deleteNode.right;
            deleteNode.left.parent = maximum;
            deleteNode.right.parent = maximum;

            // 判断 maximum 的情况
        }
        return null;
    }


    @Override
    public Node search(int e) {
        Node node = root;
        while (node != null && node.value != null && node.value != e) {
            // 目标值比节点小，就往左子树找
            // 目标值比节点大，就往右子树找
            if (e < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    private Node privateInsert(int e) {
        // 加入缓存
        elementList.add(e);
        // 根节点是空，那么新增节点就是根节点
        if (root == null) {
            // 默认是红色
            Node newNode = new Node(this.getClass(), e, null, null, null);
            root = newNode;
            size++;
            return newNode;
        }

        // 基于 e 找到合适的根节点
        Node parent = root;
        Node search = root;
        while (search != null && search.value != null) {
            parent = search;
            if (e < search.value) {
                search = search.left;
            } else {
                search = search.right;
            }
        }
        // 插入元素
        Node newNode = new Node(this.getClass(), e, parent, null, null);
        if (parent.value > newNode.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
        return newNode;
    }


    @Override
    public String toString() {
        String str = elementList.toString();
        str = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]")).replace(" ", "");
        int nullIdx = str.indexOf("null");
        if (nullIdx > 0) {
            str = str.substring(0, str.indexOf("null"));
            str = str.substring(0, str.lastIndexOf(","));
        }
        System.out.println(this.getClass().getSimpleName() + "，输入节点：" + str + "\r\n");
        return printSubTree(root);
    }

    private boolean isBlack(Node node) {
        return node != null && node.color == Node.Color.BLACK;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == Node.Color.RED;
    }

    /**
     * @param nodeToReplace 被替换的节点
     * @param newNode       替换的节点
     */
    private void rbTreeTransplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == nilNode) {
            // 被替换的节点为根节点的情况
            this.root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            // 被替换的节点位于其父节点的左子树
            nodeToReplace.parent.left = newNode;
        } else {
            // 被替换的节点位于其父节点的右子树
            nodeToReplace.parent.right = newNode;
        }
        // 替换节点的父亲就是被替换节点的父亲
        newNode.parent = nodeToReplace.parent;
    }

    /**
     * 找出树的最小节点
     *
     * @param node 根节点
     * @return 树的最小节点
     */
    private Node getMinimum(Node node) {
        while (node.left != nilNode) {
            node = node.left;
        }
        return node;
    }

    /**
     * 找出树的最大节点
     *
     * @param node 根节点
     * @return 树的最大节点
     */
    private Node getMaximum(Node node) {
        while (node.right != nilNode) {
            node = node.left;
        }
        return node;
    }

    private void fixDeleteBalance(Node x) {
        while (x != root && isBlack(x)) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (isRed(w)) {
                    w.color = Node.Color.BLACK;
                    x.parent.color = Node.Color.RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if (isBlack(w.left) && isBlack(w.right)) {
                    w.color = Node.Color.RED;
                    x = x.parent;
                } else if (w != nilNode) {
                    if (isBlack(w.right)) {
                        w.left.color = Node.Color.BLACK;
                        w.color = Node.Color.RED;
                        rotateRightNew(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = Node.Color.BLACK;
                    w.right.color = Node.Color.BLACK;
                    rotateLeft(x.parent);
                    x = root;
                } else {
                    x.color = Node.Color.BLACK;
                    x = x.parent;
                }
            } else {
                Node w = x.parent.left;
                if (isRed(w)) {
                    w.color = Node.Color.BLACK;
                    x.parent.color = Node.Color.RED;
                    rotateRightNew(x.parent);
                    w = x.parent.left;
                }
                if (isBlack(w.left) && isBlack(w.right)) {
                    w.color = Node.Color.RED;
                    x = x.parent;
                } else if (w != nilNode) {
                    if (isBlack(w.left)) {
                        (w.right).color = Node.Color.BLACK;
                        w.color = Node.Color.RED;
                        rotateLeft(w);
                        w = x.parent.left;
                    }
                    w.color = (x.parent).color;
                    (x.parent).color = Node.Color.BLACK;
                    (w.left).color = Node.Color.BLACK;
                    rotateRightNew(x.parent);
                    x = root;
                } else {
                    x.color = Node.Color.BLACK;
                    x = x.parent;
                }
            }
        }
    }
}
