package ru.job4j.design.isp;

public interface MenuDeleteItem {

    boolean deleteItem(String itemName);

    boolean deleteItem(Item item);
}