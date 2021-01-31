package ru.job4j.design.tictactoe;

import java.io.OutputStream;
import java.util.Objects;
import java.util.Optional;

public class ConsolePlayer implements Player<Input, OutputStream> {
    private String name;
    private Mark<OutputStream> mark;

    /**
     *
     * @param name Player's name
     * @param mark Game mark (X/O)
     */
    public ConsolePlayer(String name, Mark<OutputStream> mark) {
        this.name = name;
        this.mark = mark;
    }

    /**
     * Creates a cell with coordinates received from the input object
     * @param in Input object
     * @param out OutputStream object
     * @return Optional with Cell object if it was created
     */
    @Override
    public Optional<Cell<OutputStream>> play(Input in, OutputStream out) {
        Cell<OutputStream> cell = null;
        try {
            out.write((name + " turns. Input x y coordinate (separated by space):").getBytes());
            String x = in.nextStr();
            String y = in.nextStr();
            cell = new ConsoleCell(Integer.parseInt(x), Integer.parseInt(y), mark);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return Optional.ofNullable(cell);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConsolePlayer that = (ConsolePlayer) o;
        return Objects.equals(name, that.name)
                && Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mark);
    }
}
