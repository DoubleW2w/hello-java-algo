package hash_table;

import com.alibaba.fastjson2.JSON;

import java.lang.reflect.Array;

/**
 * @author: DoubleW2w
 * @description: 罗宾汉哈希
 * @date: 2023/12/24 15:57
 * @project: hello-java-algo
 */
public class HashMap07ByRobinHoodHashing<K, V> implements MyMap<K, V> {
    /**
     * 初始容量
     */
    private static final int DEFAULT_CAPACITY = 8;
    /**
     * 负载因子
     */
    private static final double DEFAULT_LOAD_FACTOR = 0.5;

    private Entry[] table;
    private int size;
    private final double loadFactor;

    public HashMap07ByRobinHoodHashing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap07ByRobinHoodHashing(int initCap, double lf) {
        clear(initCap);
        loadFactor = lf;
    }

    @Override
    public void put(K key, V value) {
        Entry entry = new Entry(key, value);
        int idx = hash(key);
        System.out.println(key + " " + idx);
        // 元素碰撞检测
        while (table[idx] != null) {
            if (entry.offset > table[idx].offset) {
                // 当前偏移量不止一个，则查看条目交换位置，entry 是正在查看的条目，增加现在搜索的事物的偏移量和 idx
                Entry garbage = table[idx];
                table[idx] = entry;
                entry = garbage;
                idx = increment(idx);
                entry.offset++;
            } else if (entry.offset == table[idx].offset) {
                // 当前偏移量与正在查看的检查键是否相同，如果是则它们交换值，如果不是，则增加 idx 和偏移量并继续
                if (table[idx].key.equals(key)) {
                    // 发现相同值
                    V oldVal = table[idx].value;
                    table[idx].value = value;
                } else {
                    idx = increment(idx);
                    entry.offset++;
                }
            } else {
                // 当前偏移量小于我们正在查看的我们增加 idx 和偏移量并继续
                idx = increment(idx);
                entry.offset++;
            }
        }

        // 已经到达了 null 所在的 idx，将新/移动的放在这里
        table[idx] = entry;
        size++;

        // 超过负载因子扩容
        if (size >= loadFactor * table.length) {
            rehash(table.length * 2);
        }
    }

    @Override
    public V get(K key) {
        int offset = 0;
        int idx = hash(key);
        while (table[idx] != null) {
            if (offset > table[idx].offset) {
                return null;
            } else if (offset == table[idx].offset) {
                if (table[idx].key.equals(key)) {
                    return table[idx].value;
                } else {
                    offset++;
                    idx = increment(idx);
                }
            } else {
                offset++;
                idx = increment(idx);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void clear(int cap) {
        table = (Entry[]) Array.newInstance(Entry.class, cap);
        size = 0;
    }

    private int increment(int idx) {
        idx++;
        return idx == table.length ? 0 : idx;
    }

    private int hash(K key) {
        return key.hashCode() & (table.length - 1);
    }

    private void rehash(int newCap) {
        Entry[] oldTable = table;
        clear(newCap);
        for (Entry e : oldTable) {
            // skip nulls
            if (e != null) {
                this.put(e.key, e.value);
            }
        }
    }


    private class Entry {
        K key;
        V value;
        int offset;

        Entry(K k, V v) {
            this.key = k;
            this.value = v;
            offset = 0;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getOffset() {
            return offset;
        }
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "tab=" + JSON.toJSONString(table) +
                '}';
    }
}
