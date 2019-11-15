package Vol_3;

import java.util.Objects;

/**
 * 基于线性探测的符号表
 *
 * @param <Key>
 * @param <Value>
 */
public class LineProbingHashST<Key, Value> {
    /**
     * 符号表中键值对的总数
     */
    private int N;
    /**
     * 线性探测表的大小
     */
    private int M;
    private Key[] keys;
    private Value[] values;

    public LineProbingHashST() {
        this(16);
    }

    public LineProbingHashST(int cap) {
        this.M = cap;
        keys = (Key[]) new Objects[cap];
        values = (Value[]) new Objects[cap];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value value) {
        if (N >= M / 2) {
            resize(2 * M);
        }
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) / M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Value get(Key key) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToRedo, valToRedo);
        }
        N--;
        if (N > 0 && N == M / 8) {
            resize(M / 2);
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     * 调整数组大小
     *
     * @param cap
     */
    private void resize(int cap) {
        LineProbingHashST<Key, Value> t = new LineProbingHashST<>(cap);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], values[i]);
            }
        }
        keys = t.keys;
        values = t.values;
    }

}
