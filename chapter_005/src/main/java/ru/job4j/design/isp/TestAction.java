package ru.job4j.design.isp;

public class TestAction implements Action {
    @Override
    public void doAction() {
        System.out.println("Test action");
    }
}