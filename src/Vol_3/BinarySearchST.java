package Vol_3;

import java.util.*;

/**
 * 基于二分查找的有序数组
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Objects[capacity];
    }

    public int size() {
        return N;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return values[i];
        } else {
            return null;
        }
    }

    /**
     * 查找键
     *
     * @param key
     * @return
     */
    private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    private boolean isEmpty() {
        return size() == 0;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    /**
     * 排名为key的键
     *
     * @param k
     * @return
     */
    public Key select(int k) {
        return keys[k];
    }

    /**
     * 大于等于key的最小键
     *
     * @param key
     * @return
     */
    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    /**
     * 小于等于key的最大键
     *
     * @param key
     * @return
     */
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        }
        return keys[i - 1];
    }

    public void delete(Key key) {
        if (isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }
        for (int j = i; j < N; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        N--;
        keys[N - 1] = null;
        values[N - 1] = null;
    }

    private boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new ArrayList<>();
        if (lo.compareTo(hi) > 0) {
            return list;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            list.add(keys[i]);
        }
        if (contains(hi)) {
            list.add(keys[rank(hi)]);
        }
        return list;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }
}
