package hash_table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * @author: DoubleW2w
 * @description: 拉链寻址法
 * @date: 2023/12/24 14:06
 * @project: hello-java-algo
 */
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
            if(key.equals(node.getKey())){
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

        @Override
        public String toString() {
            return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
                    .add("key=" + key)
                    .add("value=" + value)
                    .toString();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HashMap02BySeparateChaining.class.getSimpleName() + "[", "]")
                .add("tab=" + Arrays.toString(tab))
                .toString();
    }
}
