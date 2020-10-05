package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> container;

    public SimpleSet() {
        this.container = new SimpleArray<>();
    }

    public void add(T value) {
        if (!ifContains(value)) {
            container.add(value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }

    private boolean ifContains(T value) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
