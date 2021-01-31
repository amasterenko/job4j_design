package ru.job4j.design.tictactoe;

import java.io.OutputStream;
import java.util.Objects;

/**
 * Stores a player's turn coordinates and his game mark
 */
public class ConsoleCell implements Cell<OutputStream> {
    private final int x;
    private final int y;
    private final Mark<OutputStream> mark;

    /**
     * @param x int [1..n], n - game board size
     * @param y int [1..n], n - game board size
     * @param mark player's mark
     */

    public ConsoleCell(int x, int y, Mark<OutputStream> mark) {
        this.x = x;
        this.y = y;
        this.mark = mark;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Mark<OutputStream> getMark() {
        return mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsoleCell that = (ConsoleCell) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
