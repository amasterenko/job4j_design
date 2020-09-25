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

    private int findNextEvenIndx() {
         int indx;
         indx = pointer;
         while (indx != data.length) {
             if (data[indx] % 2 == 0) {
                 return indx;
             }
             indx++;
         }
         return -1;
    }

    @Override
    public boolean hasNext() {
        if (pointer == -1) {
            return false;
        }
        pointer = findNextEvenIndx();
        return pointer != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
           throw new NoSuchElementException();
        }
        return data[pointer++];
    }
}
