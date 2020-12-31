package ru.job4j.design.isp;

import java.util.*;

public class Menu implements MenuAddItem, MenuDeleteItem, MenuGetItemAction, MenuPrint {
    private final List<Item> rootItems = new ArrayList<>();
    private final Set<String> itemNames = new HashSet<>();

    @Override
    public boolean addItem(Item item, String parentName) {
        if (item == null) {
            return false;
        }
        if (parentName == null) {
            addItem(item);
        }
        if (!itemNames.contains(parentName)) {
            return false;
        }
        Item parent = findItemByName(parentName);
        if (parent != null) {
            parent.addChild(item);
            itemNames.add(item.getName());
            return true;
        }
        return false;
    }

    @Override
    public boolean addItem(Item item, Item parent) {
        if (parent != null) {
            return this.addItem(item, parent.getName());
        }
        return false;
    }

    @Override
    public boolean addItem(Item item) {
        if (itemNames.contains(item.getName())) {
            return false;
        }
        itemNames.add(item.getName());
        return rootItems.add(item);
    }

    @Override
    public boolean deleteItem(String itemName) {
        if (!itemNames.contains(itemName)) {
            return false;
        }
        Item item = findItemByName(itemName);
        if (item != null && item.getParent() != null) {
            return item.getParent().deleteChild(item);
        }
        return rootItems.remove(item);
    }

    @Override
    public boolean deleteItem(Item item) {
        if (item != null) {
            return this.deleteItem(item.getName());
        }
        return false;
    }

    @Override
    public Action getItemAction(String itemName) {
        Item item = findItemByName(itemName);
        return item == null ? null : item.getAction();
    }

    @Override
    public Action getItemAction(Item item) {
        if (item != null) {
            return this.getItemAction(item.getName());
        }
        return null;
    }

    @Override
    public void print() {
        Map<String, String> itemGraphics = new HashMap<>();
        rootItems.forEach(i -> itemGraphics.put(i.getName(), "┝"));
        LinkedList<Item> stack = new LinkedList<>(rootItems);
        while (!stack.isEmpty()) {
            Item item = stack.pop();
            String name = item.getName();
            System.out.println(itemGraphics.get(name) + name);
            List<Item> children = item.getChildren();
            if (children != null) {
                children.forEach(
                        i -> itemGraphics.put(
                                i.getName(),
                                itemGraphics.get(name) + strFromChar('╺', name.length() / 2)));
                stack.addAll(0, item.getChildren());
            }
        }
    }

    public List<Item> getItems() {
        return rootItems;
    }

    private Item findItemByName(String name) {
        LinkedList<Item> stack = new LinkedList<>(rootItems);
        while (!stack.isEmpty()) {
            Item item = stack.pop();
            if (name.equals(item.getName())) {
                return item;
            }
            if (item.getChildren() != null) {
                stack.addAll(item.getChildren());
            }
        }
        return null;
    }

    private String strFromChar(char symbol, int number) {
        char[] repeat = new char[number];
        Arrays.fill(repeat, symbol);
        return new String(repeat);
    }
}
