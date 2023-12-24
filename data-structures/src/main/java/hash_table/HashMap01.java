package hash_table;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/24 14:01
 * @project: hello-java-algo
 */
public class HashMap01<K, V> implements MyMap<K, V> {
    private final Object[] tab = new Object[8];
    @Override
    public void put(K key, V value) {
        int idx = key.hashCode() & (tab.length - 1);
        tab[idx] = value;
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length - 1);
        return (V) tab[idx];
    }
}
