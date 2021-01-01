package ru.job4j.design.dip;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store<Food> {
    private List<Food> foods = new ArrayList<>();

    @Override
    public boolean putItem(Food item, float expirationRate) {
        boolean rsl = false;
        if (expirationRate > 0.25 && expirationRate <= 1) {
            if (expirationRate > 0.75) {
                item.setDiscount(25);
            }
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
