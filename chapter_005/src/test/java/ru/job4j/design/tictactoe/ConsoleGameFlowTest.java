package ru.job4j.design.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleGameFlowTest {
    private OutputStream out;
    private String sep;

    @Before
    public void init() {
        out = new ByteArrayOutputStream();
        sep = System.lineSeparator();
    }

    @Test
    public void oneGame3x3WithWinner() {
        String setPlayersAndBoard = "player1" + sep + "player2"  + sep + "3" + sep;
        String turnsForWin = "1 1" + sep + "1 2"  + sep
                            + "2 1" + sep + "1 3" + sep
                            + "3 1" + sep;
        String playAgain = "n";
        InputStream in = new ByteArrayInputStream((setPlayersAndBoard + turnsForWin + playAgain).getBytes());
        var game = new ConsoleGameFlow(new ConsoleInput(in), out);
        game.start();
        assertThat(out.toString(), containsString("wins!"));
        assertThat(out.toString(), not(containsString("Draw.")));
    }

    @Test
    public void oneGame3x3WithDraw() {
        String setPlayersAndBoard = "player1" + sep + "player2"  + sep + "3" + sep;
        String turnsForDraw = "1 1" + sep + "1 2"  + sep
                            + "1 3" + sep + "2 3" + sep
                            + "2 1" + sep + "3 1" + sep
                            + "2 2" + sep + "3 3" + sep
                            + "3 2" + sep;
        String playAgain = "n";
        InputStream in = new ByteArrayInputStream((setPlayersAndBoard + turnsForDraw + playAgain).getBytes());
        var game = new ConsoleGameFlow(new ConsoleInput(in), out);
        game.start();
        assertThat(out.toString(), not(containsString("wins!")));
        assertThat(out.toString(), containsString("Draw."));
    }

    @Test
    public void twoGames3x3WithWinDraw() {
        String setPlayersAndBoard = "player1" + sep + "player2"  + sep + "3" + sep;
        String turnsForWin = "1 1" + sep + "1 2"  + sep
                + "2 1" + sep + "1 3" + sep
                + "3 1" + sep;
        String turnsForDraw = "1 1" + sep + "1 2"  + sep
                + "1 3" + sep + "2 3" + sep
                + "2 1" + sep + "3 1" + sep
                + "2 2" + sep + "3 3" + sep
                + "3 2" + sep;
        String playAgain1 = "y" + sep;
        String setBoard2 = "3" + sep;
        String playAgain2 = "n";
        InputStream in = new ByteArrayInputStream((setPlayersAndBoard + turnsForWin + playAgain1
                                                + setBoard2 + turnsForDraw + playAgain2).getBytes());
        var game = new ConsoleGameFlow(new ConsoleInput(in), out);
        game.start();
        assertThat(out.toString(), containsString("wins!"));
        assertThat(out.toString(), containsString("Draw."));
    }
}