package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class SimpleArrayIterator<T> implements Iterator<T> {
    private final SimpleArray<T> array;
    private final int size;
    private int pointer = 0;
    private int expectedModCounter;

    public SimpleArrayIterator(SimpleArray<T> array, int size) {
        this.array = array;
        this.size = size;
        this.expectedModCounter = array.getModCounter();
    }

    @Override
    public boolean hasNext() {
        return pointer < size;
    }

    @Override
    public T next() {
        if (expectedModCounter != array.getModCounter()) {
            throw new ConcurrentModificationException();
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array.get(pointer++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer action) {
        throw new UnsupportedOperationException();
    }
}
