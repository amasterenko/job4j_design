package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int pointer = 0;

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        throw new UnsupportedOperationException();
    }

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (pointer != data.length) {
            if (data[pointer] % 2 == 0) {
                return true;
            }
            pointer++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
           throw new NoSuchElementException();
        }
        return data[pointer++];
    }
}
