package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store<Food> {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean putItem(Food item, float expRate) {
        boolean rsl = false;
        if (expRate <= 0.25) {
            foods.add(item);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getItems() {
        return foods;
    }
}
