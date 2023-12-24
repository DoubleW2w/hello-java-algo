package hash_table;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/24 14:17
 * @project: hello-java-algo
 */
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
        for (int i = idx; i < tab.length; i ++){
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
