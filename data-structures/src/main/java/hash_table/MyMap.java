package hash_table;

/**
 * @author: DoubleW2w
 * @description:
 * @date: 2023/12/24 14:00
 * @project: hello-java-algo
 */
public interface MyMap<K, V> {
    void put(K key, V value);

    V get(K key);
}
