
/**
 * 基于拉链法的散列表
 */
public class SeparateChainingHash<Key, Value> {
    private int N; // 键值对总数
    private int M; // 散列表大小
    private SequentialSearchST<Key, Value>[] st;
    public SeparateChainingHash(){
        SeparateChainingHash(997);
    }

    public SeparateChainingHash(int m) {
        this.M = m;
        st = new SequentialSearchST<Key, Value>[m];
        for(int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        st[hash(key)].put(key, value);
    }

    public void delete(Key key) {
        st[hash(key)].delete(key, value);
    }

    private class SequentialSearchST<Key, Value> {
        private Key key;
        private Value value;
        private SequentialSearchST<Key, Value> next;

        public SequentialSearchST() {

        }

        public SequentialSearchST(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public void put(Key key, Value value) {
            if (this.key.equals(key))
                this.value = value;
            else if (this.next == null) {
                this.next = new SequentialSearchST<Key, Value>(key, value);
            } else
                this.next.put(key, value);
        }

        public Value get(Key key) {
            if (this.key.equals(key))
                return this.value;
            else {
                if (this.next == null)
                    return null;
                else
                    return this.next.get(key);
            }
        }

        public void delete(Key key) {
            if(this.next == null) 
                return;
            if(this.next.key.equals(key)) {
                if(this.next.next != null) {
                    this.next = this.next.next;
                } else 
                    this.next = null;
            } else 
                this.next.delete(key);
        }
    }
}