package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.List;

public class Item implements ItemAddChild, ItemDeleteChild {
    private String name;
    private Item parent;
    private List<Item> children;
    private Action action;

    public Item(String name, Action action) {
        if (name == null || action == null) {
            throw new IllegalArgumentException("Name and parent cannot be null!");
        }
        this.name = name;
        this.children = new ArrayList<>();
        this.action = action;
    }

    @Override
    public void addChild(Item item) {
        item.setParent(this);
        this.children.add(item);
    }

    @Override
    public boolean deleteChild(Item child) {
        if (this.children == null) {
            return false;
        }
        return this.children.remove(child);
    }

    public List<Item> getChildren() {
        return children;
    }

    public Item getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }

    private void setParent(Item item) {
        this.parent = item;
    }
}