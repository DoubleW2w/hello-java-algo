### 哈希表是什么

当我们通过数组存放元素时，都是按照顺序存放的，当需要获取某个元素的时候，则需要对数组进行遍历，获取到指定的值。
通过这样的方式获取的时间复杂度为 $O(n)$。

在计算机科学中，一个哈希表（hash_table table、hash_table map）是一种实现关联数组的抽象数据结构，该结构将「键」通过「哈希计算」映射到「值」。

即当对一个 Key 通过某种计算后，得到的值就是该关联数组下的某个索引，然后将值存放在对应的槽位下。那么，这样当需要获取到指定的元素时，
只需要对 Key 进行某种计算后，就可以直接获取出来，从而达到时间复杂度为$O(1)$的情况。

### 哈希碰撞

随着元素的增多，很可能发生哈希冲突，或者哈希值波动不大导致索引计算相同，也就是一个索引位置出现多个元素情况。

<img src="https://bugstack.cn/images/article/algorithm/algorithms-220824-03.png?raw=true">
<p style="text-align: center">图片来自：<a href="https://bugstack.cn/">https://bugstack.cn/</a></p>


那么此时就出现了一系列解决方案，包括；HashMap 中的拉链寻址 + 红黑树、扰动函数、负载因子、ThreadLocal
的开放寻址、合并散列、杜鹃散列、跳房子哈希、罗宾汉哈希等各类数据结构设计。让元素在发生哈希冲突时，也可以存放到新的槽位，并尽可能保证索引的时间复杂度小于
$O(n)$
n

### 实现

#### 简单实现

```java

```

#### 拉链寻址法

既然我们没法控制元素不碰撞，但我们可以对碰撞后的元素进行管理。比如像 HashMap
中拉链法一样，把碰撞的元素存放到链表上。这里我们就来简化实现一下。

```java
public class HashMap02BySeparateChaining<K, V> implements MyMap<K, V> {
    private final Logger logger = LoggerFactory.getLogger(HashMap02BySeparateChaining.class);
    private final LinkedList<Node<K, V>>[] tab = new LinkedList[8];


    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if (tab[idx] == null) {
            tab[idx] = new LinkedList<>();
            tab[idx].add(new Node<>(key, value));
        } else {
            tab[idx].add(new Node<>(key, value));
        }
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        LinkedList<Node<K, V>> nodes = tab[idx];
        for (Node<K, V> node : nodes) {
            if (key.equals(node.getKey())) {
                return node.getValue();
            }
        }
        return null;
    }

    static class Node<K, V> {
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
```

- 对碰撞后的元素使用一个链表进行往后添加，在获取的时候，需要遍历获取。

#### 开放寻址法

在哈希桶上存放碰撞元素的方式。它叫开放寻址，也就是 ThreadLocal 中运用斐波那契散列+开放寻址的处理方式。

```java
public class HashMap03ByOpenAddressing<K, V> implements MyMap<K, V> {
    private final Node<K, V>[] tab = new Node[8];


    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if (tab[idx] == null) {
            tab[idx] = new Node<>(key, value);
        } else {
            for (int i = idx; i < tab.length; i++) {
                if (tab[i] == null) {
                    tab[i] = new Node<>(key, value);
                    break;
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        for (int i = idx; i < tab.length; i++) {
            if (tab[idx] != null && tab[idx].key == key) {
                return tab[idx].value;
            }
        }
        return null;
    }

    static class Node<K, V> {
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
```

- 开放寻址的设计会对碰撞的元素，寻找哈希桶上新的位置，这个位置从当前碰撞位置开始向后寻找，直到找到空的位置存放。
- 在 ThreadLocal 的实现中会使用斐波那契散列、索引计算累加、启发式清理、探测式清理等操作，以保证尽可能少的碰撞。
  <img src="https://bugstack.cn/images/article/algorithm/algorithms-220824-08.png">

#### 合并散列法

合并散列是开放寻址和单独链接的混合，碰撞的节点在哈希表中链接。此算法适合「固定分配内存」的哈希桶，通过存放元素时识别哈希桶上的最大空槽位来解决合并哈希中的冲突。
```java
public class HashMap04ByCoalescedHashing<K, V> implements MyMap<K, V> {
    private final Node<K, V>[] tab = new Node[8];

    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        if (tab[idx] == null) {
            tab[idx] = new Node<>(key, value);
            return;
        }
        // 找到一个key不同且有元素的的桶
        int cursor = tab.length - 1;
        while (tab[cursor] != null && tab[cursor].key != key) {
            --cursor;
        }
        tab[cursor] = new Node<>(key, value);

        // 将碰撞节点指向这个新节点
        while (tab[idx].idxOfNext != 0){
            idx = tab[idx].idxOfNext;
        }

        tab[idx].idxOfNext = cursor;

    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        while (tab[idx].key != key) {
            idx = tab[idx].idxOfNext;
        }
        return tab[idx].value;    }

    static class Node<K, V> {
        final K key;
        V value;
        int idxOfNext;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
```
<img src="https://bugstack.cn/images/article/algorithm/algorithms-220824-10.png">

合并散列的最大目的在于将碰撞元素链接起来，避免因为需要寻找碰撞元素所发生的循环遍历。也就是A、B元素存放时发生碰撞，那么在找到A元素的时候可以很快的索引到B元素所在的位置。

#### 杜鹃散列

#### 跳房子散列

#### 罗兵汉散列

罗宾汉哈希是一种「基于开放寻址」的冲突解决算法；冲突是通过偏向从其“原始位置”（即项目被散列到的存储桶）最远或最长探测序列长度（PSL）的元素的位移来解决的。