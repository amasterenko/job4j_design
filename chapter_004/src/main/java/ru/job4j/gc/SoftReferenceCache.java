package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftReferenceCache<K, V> {
    private Map<K, SoftReference<V>> map = new HashMap<>();

    public SoftReference<V> get(K key) {
        return map.get(key);
    }

    public void load(K key, V value) {
        map.put(key, new SoftReference<>(value));
    }

    public int size() {
        return map.size();
    }
}
