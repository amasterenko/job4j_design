package ru.job4j.design.isp;

public interface MenuGetItemAction {
    Action getItemAction(String itemName);

    Action getItemAction(Item item);
}
