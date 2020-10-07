package ru.job4j.map;

import ru.job4j.collection.SimpleSet;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class SimpleMap<K, V> implements Iterable<K> {
    private Object[] array;
    private int size;
    private int elementsCounter = 0;
    private float loadFactor;
    private int modCounter = 0;

    private static class Entry<K, V> {
        private K key;
        private V value;
        private int hash;

        public Entry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

    public SimpleMap() {
        this(4, 0.75f);
    }

    public SimpleMap(int initSize, float loadFactor) {
        if (initSize < 1) {
            throw new IllegalArgumentException("Initial size must be equal to or grater than 1.");
        }
        if (loadFactor <= 0 || loadFactor > 1) {
            throw new IllegalArgumentException("Load factor must be in the range of (0..1]");
        }
        this.size = initSize;
        this.array = new Object[initSize];
        this.loadFactor = loadFactor;
    }

    public boolean insert(K key, V value) {
        int hash = getHash(key);
        int index = getIndx(hash);
        Entry<K, V> newEntry = new Entry<>(key, value, hash);
        V rslPut = putToArray(newEntry, array, index);
        if (rslPut != null) {
            if (rslPut.equals(value)) {
                elementsCounter++;
            }
            modCounter++;
            if (elementsCounter > size * loadFactor) {
                resize();
            }
            return true;
        }
        return false;
    }

    public V get(K key) {
        int hash = getHash(key);
        int index = getIndx(hash);
        Entry<K, V> e = (Entry<K, V>) array[index];
        return e != null ? e.value : null;
    }

    public boolean delete(K key) {
        int hash = getHash(key);
        int index = getIndx(hash);
        if (array[index] != null) {
            array[index] = null;
            elementsCounter--;
            modCounter++;
            return true;
        }
        return false;
    }

    private void resize() {
        size *= 2;
        Object[] newArray = new Object[size];
        elementsCounter = 0;
        for (Object e : array) {
            if (e != null) {
                Entry<K, V> newEntry = (Entry<K, V>) e;
                int newIndex = getIndx(newEntry.hash);
                if (putToArray(newEntry, newArray, newIndex) != null) {
                    elementsCounter++;
                }
            }
        }
        array = newArray;
    }

    private int getHash(K key) {
        return (key != null) ? key.hashCode() ^ (key.hashCode() >>> 16) : 0;
    }

    private int getIndx(int hash) {
        return hash != 0 ? hash & (size - 1) : 0;
    }

    private V putToArray(Entry<K, V> newEntry, Object[] array, int index) {
        if (array[index] == null) {
            array[index] = newEntry;
            return newEntry.value;
        }
        Entry<K, V> entry = (Entry<K, V>) array[index];
        if (entry.hash == newEntry.hash && entry.key.equals(newEntry.key)) {
            array[index] = newEntry;
            return entry.value;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        if (elementsCounter == 0) {
            return Collections.EMPTY_LIST.iterator();
        }
        return new Iterator<>() {
            private int pointer = 0;
            private int expectedModCounter = modCounter;
            private int returnedElements = 0;

            @Override
            public boolean hasNext() {
                return returnedElements < elementsCounter;
            }

            @Override
            public K next() {
                if (expectedModCounter != modCounter) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (pointer < size) {
                    if (array[pointer] != null) {
                        Entry<K, V> entry = (Entry<K, V>) array[pointer];
                        returnedElements++;
                        pointer++;
                        return entry.key;
                    }
                    pointer++;
                }
                return null;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void forEachRemaining(Consumer<? super K> action) {
                throw new UnsupportedOperationException();
            }
        };
    }
}
