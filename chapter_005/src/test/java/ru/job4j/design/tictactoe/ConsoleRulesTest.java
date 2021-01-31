package ru.job4j.design.tictactoe;


import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleRulesTest {

    @Test
    public void nextTurnPlayer() {
        var player1 = new ConsolePlayer("player1", new ConsoleMarkX());
        var player2 = new ConsolePlayer("player2",  new ConsoleMarkO());
        var board = new ConsoleBoard(3);
        var rules = new ConsoleRules(player1, player2, board);
        var expected = rules.nextTurnPlayer().get() == player1 ? player2 : player1;
        assertThat(rules.nextTurnPlayer().get(), is(expected));
    }

    @Test
    public void whenNextTurnIsPossible() {
        var board = new ConsoleBoard(2);
        var markX = new ConsoleMarkX();
        var markO = new ConsoleMarkO();
        var rules = new ConsoleRules(new ConsolePlayer("player1", markX),
                new ConsolePlayer("player2", markO), board);
        board.takeTurn(new ConsoleCell(1, 1, markX));
        board.takeTurn(new ConsoleCell(1, 2, markO));
        assertThat(rules.isNextTurnPossible(), is(true));
    }

    @Test
    public void whenNextTurnIsImpossible() {
        var board = new ConsoleBoard(2);
        var markX = new ConsoleMarkX();
        var markO = new ConsoleMarkO();
        var rules = new ConsoleRules(new ConsolePlayer("player1", markX),
                new ConsolePlayer("player2", markO), board);
        board.takeTurn(new ConsoleCell(1, 1, markX));
        board.takeTurn(new ConsoleCell(1, 2, markO));
        board.takeTurn(new ConsoleCell(2, 1, markX));
        board.takeTurn(new ConsoleCell(2, 2, markO));
        assertThat(rules.isNextTurnPossible(), is(false));
    }

    @Test
    public void whenFirstPlayerWins() {
        var board = new ConsoleBoard(2);
        var markX = new ConsoleMarkX();
        var markO = new ConsoleMarkO();
        var rules = new ConsoleRules(new ConsolePlayer("player1", markX),
                new ConsolePlayer("player2", markO), board);
        var expected = rules.nextTurnPlayer();
        board.takeTurn(new ConsoleCell(1, 1, markX));
        rules.nextTurnPlayer();
        board.takeTurn(new ConsoleCell(1, 2, markO));
        rules.nextTurnPlayer();
        board.takeTurn(new ConsoleCell(2, 1, markX));
        assertThat(rules.getWinner(), is(expected));
    }

    @Test
    public void whenSecondPlayerWins() {
        var board = new ConsoleBoard(3);
        var markX = new ConsoleMarkX();
        var markO = new ConsoleMarkO();
        var player1 = new ConsolePlayer("player1", markX);
        var player2 = new ConsolePlayer("player2", markO);
        var rules = new ConsoleRules(player1, player2, board);
        var firstPlayer = rules.nextTurnPlayer().get();
        var expected = firstPlayer == player1 ? player2 : player1;
        board.takeTurn(new ConsoleCell(1, 1, markX));
        rules.nextTurnPlayer();
        board.takeTurn(new ConsoleCell(1, 2, markO));
        rules.nextTurnPlayer();
        board.takeTurn(new ConsoleCell(1, 3, markX));
        rules.nextTurnPlayer();
        board.takeTurn(new ConsoleCell(2, 2, markO));
        rules.nextTurnPlayer();
        board.takeTurn(new ConsoleCell(3, 1, markX));
        rules.nextTurnPlayer();
        board.takeTurn(new ConsoleCell(3, 2, markO));
        assertThat(rules.getWinner().get(), is(expected));
    }
}