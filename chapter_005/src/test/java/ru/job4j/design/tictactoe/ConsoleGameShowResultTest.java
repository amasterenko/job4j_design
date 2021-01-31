package ru.job4j.design.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleGameShowResultTest {
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
    public void whenWin() {
        InputStream in = new ByteArrayInputStream(("1 1" + sep + "1 2"  + sep
                                                + "2 1" + sep + "1 3" + sep
                                                + "3 1"  + sep).getBytes());
        var playingTurns = new ConsoleGamePlayTurns();
        playingTurns.execute(rules, board, new ConsoleInput(in), out);
        var optPlayer = rules.getWinner();
        assertThat(optPlayer, anyOf(equalTo(Optional.of(player1)), equalTo(Optional.of(player2))));
    }

    @Test
    public void whenDraw() {
        InputStream in = new ByteArrayInputStream(("1 1" + sep + "1 2"  + sep
                                                + "1 3" + sep + "2 3" + sep
                                                + "2 1" + sep + "3 1" + sep
                                                + "2 2" + sep + "3 3" + sep
                                                + "3 2" + sep).getBytes());
        var playingTurns = new ConsoleGamePlayTurns();
        playingTurns.execute(rules, board, new ConsoleInput(in), out);
        var optPlayer = rules.getWinner();
        assertThat(optPlayer, is(Optional.empty()));
        assertThat(rules.isNextTurnPossible(), is(false));
    }
}