package Vol_3;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于拉链表的散列表
 */
public class SeparateChainingHashST<Key, Value> {
    /**
     * 键值对总数
     */
    private int N;
    /**
     * 散列表大小
     */
    private int M;
    /**
     * 存放链表对象的数组
     */
    private SeparateChainingHashST<Key, Value>[] st;

    public SeparateChainingHashST() {
        this(4);
    }

    public SeparateChainingHashST(int m) {
        M = m;
        st = (SeparateChainingHashST<Key, Value>[]) new SeparateChainingHashST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SeparateChainingHashST<>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    public Iterable<Key> keys() {
        List<Key> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                list.add(key);
            }
        }
        return list;
    }
}
