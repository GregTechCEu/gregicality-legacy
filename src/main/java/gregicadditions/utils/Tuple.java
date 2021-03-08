package gregicadditions.utils;

public class Tuple<K, V> {
    private final K key;
    private final V value;

    public Tuple(K key, V value) {
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
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple))
            return false;
        Tuple<?, ?> tuple = (Tuple<?, ?>) obj;
        return tuple.key.equals(key) && tuple.value.equals(value);
    }
}