package ru.job4j.design.tictactoe;

import java.io.OutputStream;
import java.util.Optional;

public class ConsoleRules implements Rules<Input, OutputStream> {
    private final Player<Input, OutputStream> player1;
    private final Player<Input, OutputStream> player2;
    private final Board<OutputStream> board;
    private Player<Input, OutputStream> lastTurnPlayer;
    private Player<Input, OutputStream> winner;

    public ConsoleRules(Player<Input, OutputStream> player1, Player<Input, OutputStream> player2,
                        Board<OutputStream> board) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }

    /**
     * Define a player that will turn next
     * @return Optional with next player if next turn possible
     */
    @Override
    public Optional<Player<Input, OutputStream>> nextTurnPlayer() {
        if (!isNextTurnPossible()) {
            return Optional.empty();
        }
        if (lastTurnPlayer == null) {
            lastTurnPlayer = Math.round(Math.random()) == 1 ? player1 : player2;
        } else {
            lastTurnPlayer = lastTurnPlayer == player1 ? player2 : player1;
        }
        return Optional.of(lastTurnPlayer);
    }

    /**
     *
     * @return True if the board is not full and there is no winner of the game
     */
    @Override
    public boolean isNextTurnPossible() {
        getWinner();
        return !board.isFull() && winner == null;
    }

    /**
     * Defines if the game has a winner
     * @return Optional with a player if the winner exists
     */
    @Override
    public Optional<Player<Input, OutputStream>> getWinner() {
        if (winner == null && board.hasCompletedLine()) {
            winner = lastTurnPlayer;
        }
        return Optional.ofNullable(winner);
    }
}
