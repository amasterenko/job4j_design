package ru.job4j.design.dip;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store<Food> {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean putItem(Food item, float expRate) {
        boolean rsl = false;
        if (expRate > 1) {
            foods.add(item);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getItems() {
        return foods;
    }

    @Override
    public void clear() {
        foods.clear();
    }
}
