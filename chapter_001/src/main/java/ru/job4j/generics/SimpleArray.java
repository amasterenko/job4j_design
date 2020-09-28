package ru.job4j.generics;

import java.util.*;
import java.util.function.Consumer;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] array;
    private final int size;
    private int lastElementIndx = -1;

    public SimpleArray() {
        this(10);
    }

    public SimpleArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size of the SimpleArray must be grater than 0.");
        }
        this.size = size;
        this.array = (T[]) new Object[size];
    }

    @Override
    public Iterator<T> iterator() {
        if (lastElementIndx == -1) {
            return Collections.EMPTY_LIST.iterator();
        }
        return new SimpleArrayIterator<>(Arrays.copyOfRange(array, 0, lastElementIndx + 1));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException();
    }

    public boolean add(T model) {
        try {
            int i = lastElementIndx + 1;
            array[Objects.checkIndex(i, size)] = model;
            lastElementIndx = i;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean set(int index, T model) {
        try {
            array[Objects.checkIndex(index, lastElementIndx + 1)] = model;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean remove(int index) {
        try {
            int i = Objects.checkIndex(index, lastElementIndx + 1);
            System.arraycopy(array, i + 1, array, i, lastElementIndx - i);
            array[lastElementIndx--] = null;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public T get(int index) {
        return array[Objects.checkIndex(index, lastElementIndx + 1)];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, 0, lastElementIndx + 1));
    }
}
