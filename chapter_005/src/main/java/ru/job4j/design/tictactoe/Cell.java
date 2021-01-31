package ru.job4j.design.tictactoe;

public interface Cell<S> {
    int getX();

    int getY();

    Mark<S> getMark();
}
