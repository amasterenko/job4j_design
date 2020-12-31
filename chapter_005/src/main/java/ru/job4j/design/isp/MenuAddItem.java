package ru.job4j.design.isp;

public interface MenuAddItem {

    boolean addItem(Item item, String parentName);

    boolean addItem(Item item, Item parent);
}
