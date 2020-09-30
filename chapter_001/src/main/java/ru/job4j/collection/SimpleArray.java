package ru.job4j.collection;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int length;
    private int elementsCounter = 0;
    private float factor;
    private int modCounter = 0;

    public SimpleArray(int initialLength, float factor) {
        if (initialLength <= 0) {
            throw new IllegalArgumentException("Size of the SimpleArray must be grater than 0.");
        }
        if (factor <= 1) {
            throw new IllegalArgumentException("The extend factor must be grater than 1.");
        }
        this.container = new Object[initialLength];
        this.factor = factor;
        this.length = initialLength;
    }

    public SimpleArray() {
        this(10, 1.5f);
    }

    public T get(int index) {
        return (T) container[Objects.checkIndex(index, elementsCounter)];
    }

    public void add(T model) {
        if (elementsCounter == length - 1) {
            container = extend();
        }
        container[elementsCounter++] = model;
        modCounter++;
    }

    private Object[] extend() {
        int newLength =  (int) Math.ceil(length * factor);
        Object[] newContainer = new Object[newLength];
        System.arraycopy(container, 0, newContainer, 0, length - 1);
        length = newLength;
        return newContainer;
    }

    int getModCounter() {
        return modCounter;
    }

    @Override
    public Iterator<T> iterator() {
        if (elementsCounter == 0) {
            return Collections.EMPTY_LIST.iterator();
        }
        return new SimpleArrayIterator<>(this, elementsCounter);
    }
}
