package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCounter = 0;
    private int outCounter = 0;

    public T poll() {
        if (inCounter == 0 && outCounter == 0) {
            throw new NoSuchElementException("Queue is empty!");
        }
        if (outCounter == 0) {
            while (inCounter > 0) {
                out.push(in.pop());
                outCounter++;
                inCounter--;
            }
        }
        outCounter--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inCounter++;
    }
}
