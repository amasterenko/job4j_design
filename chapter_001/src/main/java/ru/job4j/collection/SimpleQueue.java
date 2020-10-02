package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int inCounter = 0;
    int outCounter = 0;

    public T poll() {
        if (inCounter == 0) {
            throw new NoSuchElementException("Queue is empty!");
        }
        while (inCounter > 1) {
            out.push(in.pop());
            outCounter++;
            inCounter--;
        }
        inCounter--;
        return in.pop();
    }

    public void push(T value) {
        while (outCounter > 0) {
            in.push(out.pop());
            outCounter--;
            inCounter++;
        }
        in.push(value);
        inCounter++;
    }
}
