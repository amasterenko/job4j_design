package ru.job4j.design.isp;

import java.util.*;

public class Menu implements MenuAddItem, MenuDeleteItem, MenuGetItemAction, MenuPrint {
    private final List<Item> rootItems = new ArrayList<>();
    private final Set<String> itemNames = new HashSet<>();

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

    public boolean addItem(Item item, Item parent) {
        if (parent != null) {
            return this.addItem(item, parent.getName());
        }
        return false;
    }

    public boolean addItem(Item item) {
        if (itemNames.contains(item.getName())) {
            return false;
        }
        itemNames.add(item.getName());
        return rootItems.add(item);
    }

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

    public boolean deleteItem(Item item) {
        if (item != null) {
            return this.deleteItem(item.getName());
        }
        return false;
    }

    public Action getItemAction(String itemName) {
        Item item = findItemByName(itemName);
        return item == null ? null : item.getAction();
    }

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

    public static void main(String[] args) {
        Item item1 = new Item("Item1", new TestAction());
        Item item2 = new Item("Item1.1", new TestAction());
        Item item3 = new Item("Item1.2", new TestAction());
        Item item4 = new Item("Item2", new TestAction());
        Item item5 = new Item("Item2.1", new TestAction());
        Item item6 = new Item("Item2.2", new TestAction());
        Item item7 = new Item("Item2.2.1", new TestAction());
        Item item8 = new Item("Item2.2.2", new TestAction());
        Menu menu = new Menu();
        menu.addItem(item1);
        menu.addItem(item2, item1);
        menu.addItem(item3, item1);
        menu.addItem(item4);
        menu.addItem(item5, item4);
        menu.addItem(item6, item4);
        menu.addItem(item7, item6);
        menu.addItem(item8, item6);
        menu.print();
        menu.getItemAction(item6).doAction();
    }
}
