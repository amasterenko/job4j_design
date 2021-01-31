package ru.job4j.design.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConsoleGamePlayTurnsTest {
    private ConsoleMarkX markX;
    private ConsoleMarkO markO;
    private ConsolePlayer player1;
    private ConsolePlayer player2;
    private ConsoleBoard board;
    private ConsoleRules rules;
    private InputStream in;
    private OutputStream out;
    private String sep;

    @Before
    public void init() {
        markX = new ConsoleMarkX();
        markO = new ConsoleMarkO();
        player1 = new ConsolePlayer("player1", markX);
        player2 = new ConsolePlayer("player2", markO);
        board = new ConsoleBoard(3);
        rules = new ConsoleRules(player1, player2, board);
        out = new ByteArrayOutputStream();
        sep = System.lineSeparator();
    }

    @Test
    public void whenValidTurns() {
        InputStream in = new ByteArrayInputStream(("1 1" + sep + "1 2"  + sep
                                                + "2 1" + sep + "1 3" + sep
                                                + "3 1" + sep).getBytes());
        var playingTurns = new ConsoleGamePlayTurns();
        boolean expected = true;
        boolean rsl = playingTurns.execute(rules, board, new ConsoleInput(in), out);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenValidTurnsWithOneWrongTurn() {
        InputStream in = new ByteArrayInputStream(("1 1" + sep + "1 1"  + sep
                + "y" + sep + "1 2" + sep
                + "2 1" + sep + "1 3" + sep
                + "3 1" + sep).getBytes());
        var playingTurns = new ConsoleGamePlayTurns();
        boolean expected = true;
        boolean rsl = playingTurns.execute(rules, board, new ConsoleInput(in), out);
        assertThat(rsl, is(expected));
    }

    @Test
    public void whenInvalidTurnAndExit() {
        InputStream in = new ByteArrayInputStream(("1 1" + "1 1" + "n" + "1 3" + "3 1").getBytes());
        var playingTurns = new ConsoleGamePlayTurns();
        boolean expected = false;
        boolean rsl = playingTurns.execute(rules, board, new ConsoleInput(in), out);
        assertThat(rsl, is(expected));
    }

}