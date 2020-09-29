package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int indx = findIndxById(id);
        if (indx != -1) {
            mem.set(indx, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        int indx = findIndxById(id);
        if (indx != -1) {
            mem.remove(indx);
            return true;
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int indx = findIndxById(id);
        if (indx != -1) {
            return mem.get(indx);
        }
        return null;
    }

    private int findIndxById(String id) {
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i) != null && mem.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
