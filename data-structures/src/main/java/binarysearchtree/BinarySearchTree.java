package binarysearchtree;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/9 15:12
 * @project: hello-java-algo
 */
public class BinarySearchTree extends AbstractTree implements ITree {
    protected int size;

    @Override
    public Node insert(int e) {
        // 缓存
        elementList.add(e);

        if (null == root) {
            root = new Node(this.getClass(),e, null, null, null);
            size++;
            return root;
        }
        // 索引出待插入元素位置，也就是插入到哪个父元素下
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
        Node newNode = new Node(this.getClass(),e, parent, null, null);
        if (parent.value > newNode.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
        return newNode;
    }

    @Override
    public Node delete(int e) {
        // 缓存
        elementList.add(e);

        Node delNode = search(e);
        if (null == delNode) {
            return null;
        }
        return delete(delNode);
    }

    protected Node delete(Node delNode) {
        if (delNode == null) {
            return null;
        }
        Node result = null;
        if (delNode.left == null) {
            result = transplant(delNode, delNode.right);
        } else if (delNode.right == null) {
            result = transplant(delNode, delNode.left);
        } else {
            // 如果待删除的节点有两颗子树，
            // 那么删除该节点后，需要找一个新节点替换
            // 为了保证新节点构成的树满足二叉搜索树，那么新节点的值应该是 从待删除节点构成的树中的「右子树部分」找一个最小的值
            // 这样，「最小的值」由于从右子树部分寻找，因为保证了比 左子树任一节点大，比右子树任一节点小
            // 那么，「最小的值」应该是从 待删除节点构成的树中的右子树部分的最左侧
            Node miniNode = getMiniNode(delNode.right);
            if (miniNode.parent != delNode) {
                // 交换位置，用miniNode右节点，替换miniNode
                transplant(miniNode, miniNode.right);
                // 把miniNode 提升父节点，设置右子树并进行挂链。替代待删节点
                miniNode.right = delNode.right;
                miniNode.right.parent = miniNode;
            }
            // 交换位置，删除节点和miniNode 可打印测试观察；System.out.println(this);
            transplant(delNode, miniNode);
            // 把miniNode 提升到父节点，设置左子树并挂链
            miniNode.left = delNode.left;
            miniNode.left.parent = miniNode;
            result = miniNode;
        }
        size--;
        return result;
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

    /**
     * 节点替换
     *
     * @param delNode 删除节点
     * @param addNode 替换节点
     */
    protected Node transplant(Node delNode, Node addNode) {
        // 如果待删除节点没有父亲，意味着待删除节点是根节点
        // 删除后 addNode 就是新的根节点
        if (delNode.parent == null) {
            this.root = addNode;
        } else if (delNode.parent.left == delNode) {
            // 待删除节点是左节点
            // 待删除节点形成的子树比根节点「小」，所以待删除节点被删除后，替换节点就是代替了它的位置
            delNode.parent.left = addNode;
        } else {
            // 待删除节点是右节点
            delNode.parent.right = addNode;
        }
        // 设置父节点
        if (null != addNode) {
            addNode.parent = delNode.parent;
        }
        return addNode;
    }

    /**
     * 树中最小的值应该在左子树中寻找，即 root.left == null 时就是最小值的节点
     * 否则 root.left 比 root 还要小。
     *
     * @param node 树
     * @return 最小值节点
     */
    protected Node getMiniNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
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
}
