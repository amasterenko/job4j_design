package ru.job4j.design.ocp;

public class Storage<T> {
    public boolean put(T item) {
        return false;
    }

    public T get() {
        return null;
    }
}
