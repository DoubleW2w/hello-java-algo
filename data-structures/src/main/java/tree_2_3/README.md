### 概念引入

在BST二叉搜索树可能退化成链表的基础上。引出了自平衡二叉树。

AVL树和Java API HashMap中用到的红黑树，它们都属于BalancedTree，也统称为B树，平衡的意思。

2-3树也是一种简单的平衡树，其中每个具有子节点（内部节点）的节点要么有两个子节点（2 节点）和一个数据元素，要么有三个子节点（3
节点）和两个数据元素。

<img src="https://bugstack.cn/images/article/algorithm/tree-23-01.png?raw=true">

<p style="text-align: center">图片来自:bugstack.cn</p>

### 问题

#### Node 节点属性信息都包括什么？

- 一个数组的元素集合
- 元素的序号
- 孩子元素（一个数组）。一个节点最多可临时放入3个元素，那么就会最多有4个孩子元素

#### 插入值，是否需要创建新的 Node？

本身2-3树插入元素的开始阶段，并不是直接创建一个新的节点，而是在初始化的数组空间中存入元素。所以在节点中提供了一个插入元素的方法
insert 来处理新增元素。

#### 插入后，节点内有3个元素后，怎么迁移元素？

进行迁移。把三个节点的中间节点晋升上来，其余两个节点为子节点。
如果进行一次调衡后，上一层父节点达到3个元素，则需要2次调衡，来满足2-3树的规则

### 演示例子
<img src="https://bugstack.cn/images/article/algorithm/tree-23-02.png?raw=true">
<p style="text-align: center">图片来自:bugstack.cn</p>
