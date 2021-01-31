package ru.job4j.design.tictactoe;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleGameSetBoardTest {
    private String sep = System.lineSeparator();

    @Test
    public void whenInputValidSize() {
        var settingBoard = new ConsoleGameSetBoard();
        var in = new ByteArrayInputStream(("3" + sep).getBytes());
        var out = new ByteArrayOutputStream();
        var expectedBoard = new ConsoleBoard(3);
        var expectedBoardDisplay = new ByteArrayOutputStream();
        expectedBoard.print(expectedBoardDisplay);
        String expectedRequest = "Input the board's size:";
        String notExpectedWarning = "Invalid board size! Would you like to try input the size again (y/n)?";
        var resultBoard = settingBoard.boardInit(new ConsoleInput(in), out);
        var resultBoardDisplay = new ByteArrayOutputStream();
        resultBoard.get().print(resultBoardDisplay);
        assertThat(out.toString(), containsString(expectedRequest));
        assertThat(out.toString(), not(containsString(notExpectedWarning)));
        assertThat(resultBoardDisplay.toString(), is(expectedBoardDisplay.toString()));
    }

    @Test
    public void whenInputInvalidSizeAndExit() {
        var settingBoard = new ConsoleGameSetBoard();
        var in = new ByteArrayInputStream(("0" + sep + "n" + sep).getBytes());
        var out = new ByteArrayOutputStream();
        var expectedBoard = Optional.empty();
        String expectedWarning = "Invalid board size! Would you like to try input the size again (y/n)?";
        var resultBoard = settingBoard.boardInit(new ConsoleInput(in), out);
        assertThat(out.toString(), containsString(expectedWarning));
        assertThat(resultBoard, is(expectedBoard));
    }

    @Test
    public void whenInputInvalidSizeAndInputValidSize() {
        var settingBoard = new ConsoleGameSetBoard();
        var in = new ByteArrayInputStream(("0" + sep + "y" + sep + "5").getBytes());
        var out = new ByteArrayOutputStream();
        var expectedBoard = new ConsoleBoard(5);
        var expectedBoardDisplay = new ByteArrayOutputStream();
        expectedBoard.print(expectedBoardDisplay);
        String expectedRequest = "Input the board's size:";
        String expectedWarning = "Invalid board size! Would you like to try input the size again (y/n)?";
        var resultBoard = settingBoard.boardInit(new ConsoleInput(in), out);
        var resultBoardDisplay = new ByteArrayOutputStream();
        resultBoard.get().print(resultBoardDisplay);
        assertThat(out.toString(), containsString(expectedRequest));
        assertThat(out.toString(), containsString(expectedWarning));
        assertThat(resultBoardDisplay.toString(), is(expectedBoardDisplay.toString()));
    }
}