### 引入

在 1962 年，一个姓 AV 的大佬（G. M. Adelson-Velsky） 和一个姓 L 的大佬（ Evgenii Landis）提出「平衡二叉树」（AVL） 。



![image-20231210140634147](https://doublew2w-myblogimages.oss-cn-hangzhou.aliyuncs.com/img/202312101406674.png)


### 什么是平衡二叉树

1. 是「二叉排序树」
2. 任何一个节点的左子树或者右子树都是「平衡二叉树」
3. 左右高度差 <= 1



### 相关概念

- 平衡因子BF(Balance Factor)

左子树和右子树的高度差 = 左子树高度 - 右子树高度

>  **一般来说 BF 的绝对值大于 1，,平衡树二叉树就失衡，需要「旋转」纠正**

- 二叉树的「高度 height」：从根节点到最远叶节点所经过的边的数量
- 节点的「高度 height」：从距离该节点最远的叶节点到该节点所经过的边的数量
- 节点的「高度 height」 = 左子树高度和右子树高度中的最大值 + 1

![image-20231210141552734](https://doublew2w-myblogimages.oss-cn-hangzhou.aliyuncs.com/img/202312101415606.png)





## 旋转方式

![image-20231210141656625](https://doublew2w-myblogimages.oss-cn-hangzhou.aliyuncs.com/img/202312101416071.png)

<p style="text-align:center">图片来自：bugstack.cn</p>



#### 左旋

- 旧根节点为新根节点的左子树
- 新根节点的左子树（如果存在）为旧根节点的右子树

#### 右旋

- 旧根节点为新根节点的右子树
- 新根节点的右子树（如果存在）为旧根节点的左子树

#### 旋转类型

1. LL 型：插入左孩子的左子树，右旋
2. RR 型：插入右孩子的右子树，左旋
3. LR 型：插入左孩子的右子树，先左旋，再右旋
4. RL 型：插入右孩子的左子树，先右旋，再左旋



![动图](https://pic2.zhimg.com/v2-9247d59e93e9f7b3debb6fee9510a7f5_b.webp)

![动图](https://pic3.zhimg.com/v2-0e9657b952a8b92df9497570344e91de_b.webp)



### 代码实现

```java
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
```



```java
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
```

