package ru.job4j.design.tictactoe;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleBoardTest {
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidSize() {
        Board<OutputStream> board = new ConsoleBoard(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTakeTurnWithWrongCell() {
        Board<OutputStream> board = new ConsoleBoard(3);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(-1, 0, mark));
    }

    @Test
    public void whenTakeTurnWithRightCell() {
        Board<OutputStream> board = new ConsoleBoard(3);
        Mark<OutputStream> mark = new ConsoleMarkX();
        assertThat(board.takeTurn(new ConsoleCell(2, 2, mark)), is(true));
    }

    @Test
    public void whenTakeTurnWithBusyCell() {
        Board<OutputStream> board = new ConsoleBoard(3);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(2, 2, mark));
        assertThat(board.takeTurn(new ConsoleCell(2, 2, mark)), is(false));
    }

    @Test
    public void whenBoardIsEmpty() {
        Board<OutputStream> board = new ConsoleBoard(2);
        assertThat(board.isFull(), is(false));
    }

    @Test
    public void whenBoardIsFull() {
        Board<OutputStream> board = new ConsoleBoard(2);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(1, 1, mark));
        board.takeTurn(new ConsoleCell(1, 2, mark));
        board.takeTurn(new ConsoleCell(2, 1, mark));
        board.takeTurn(new ConsoleCell(2, 2, mark));
        assertThat(board.isFull(), is(true));
    }

    @Test
    public void whenBoardIsNotEmptyAndNotFull() {
        Board<OutputStream> board = new ConsoleBoard(2);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(2, 2, mark));
        assertThat(board.isFull(), is(false));
    }

    @Test
    public void whenBoardHasNoCompletedLine() {
        Board<OutputStream> board = new ConsoleBoard(2);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(2, 2, mark));
        assertThat(board.hasCompletedLine(), is(false));
    }

    @Test
    public void whenBoardHasCompletedHorizontalLine() {
        Board<OutputStream> board = new ConsoleBoard(2);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(1, 1, mark));
        board.takeTurn(new ConsoleCell(1, 2, mark));
        assertThat(board.hasCompletedLine(), is(true));
    }

    @Test
    public void whenBoardHasCompletedVerticalLine() {
        Board<OutputStream> board = new ConsoleBoard(2);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(1, 1, mark));
        board.takeTurn(new ConsoleCell(2, 1, mark));
        assertThat(board.hasCompletedLine(), is(true));
    }

    @Test
    public void whenBoardHasCompletedDiagonalLine1() {
        Board<OutputStream> board = new ConsoleBoard(2);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(1, 1, mark));
        board.takeTurn(new ConsoleCell(2, 2, mark));
        assertThat(board.hasCompletedLine(), is(true));
    }

    @Test
    public void whenBoardHasCompletedDiagonalLine2() {
        Board<OutputStream> board = new ConsoleBoard(2);
        Mark<OutputStream> mark = new ConsoleMarkX();
        board.takeTurn(new ConsoleCell(1, 2, mark));
        board.takeTurn(new ConsoleCell(2, 1, mark));
        assertThat(board.hasCompletedLine(), is(true));
    }

    @Test
    public void whenShowEmptyBoard2x2() {
        Board<OutputStream> board = new ConsoleBoard(2);
        var out = new ByteArrayOutputStream();
        var expected = new StringBuilder();
        expected.append("     1   2 ")
                .append(System.lineSeparator())
                .append("   +---+---+")
                .append(System.lineSeparator())
                .append("1  |   |   |")
                .append(System.lineSeparator())
                .append("   +---+---+")
                .append(System.lineSeparator())
                .append("2  |   |   |")
                .append(System.lineSeparator())
                .append("   +---+---+")
                .append(System.lineSeparator());
        board.print(out);
        assertThat(out.toString(), is(expected.toString()));
    }

    @Test
    public void whenShowBoard2x2WithMarksXO() {
        Board<OutputStream> board = new ConsoleBoard(2);
        var out = new ByteArrayOutputStream();
        board.takeTurn(new ConsoleCell(1, 1, new ConsoleMarkX()));
        board.takeTurn(new ConsoleCell(2, 2, new ConsoleMarkO()));
        var expected = new StringBuilder();
        expected.append("     1   2 ")
                .append(System.lineSeparator())
                .append("   +---+---+")
                .append(System.lineSeparator())
                .append("1  | X |   |")
                .append(System.lineSeparator())
                .append("   +---+---+")
                .append(System.lineSeparator())
                .append("2  |   | O |")
                .append(System.lineSeparator())
                .append("   +---+---+")
                .append(System.lineSeparator());
        board.print(out);
        assertThat(out.toString(), is(expected.toString()));
    }
}