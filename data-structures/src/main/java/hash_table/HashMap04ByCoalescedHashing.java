package hash_table;

/**
 * @author: DoubleW2w
 * @description: 合并散列法
 * @date: 2023/12/24 14:24
 * @project: hello-java-algo
 */
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
