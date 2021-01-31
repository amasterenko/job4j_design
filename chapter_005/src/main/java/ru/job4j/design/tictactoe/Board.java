package ru.job4j.design.tictactoe;

public interface Board<S> {
    boolean takeTurn(Cell<S> cell);

    boolean isFull();

    boolean hasCompletedLine();

    void print(S screen);
}
