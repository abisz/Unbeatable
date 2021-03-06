package root;

import java.util.Map;

public class MoveEntry<K, V> implements Map.Entry<K, V> {

    private K key;
    private V value;

    public MoveEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return this.value;
    }
}