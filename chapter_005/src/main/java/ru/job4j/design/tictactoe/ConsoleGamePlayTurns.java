package ru.job4j.design.tictactoe;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public class ConsoleGamePlayTurns {
    /**
     * A loop of the player's turns
     * @param rules Rules object
     * @param board Board object
     * @param in Input object
     * @param out OutputStream object
     * @return True if there was no exit from jne of the turns
     */
    public boolean execute(Rules<Input, OutputStream> rules, Board<OutputStream> board, Input in, OutputStream out) {
        while (rules.isNextTurnPossible()) {
            try {
                if (!playTurn(rules, board, in, out)) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            board.print(out);
        }
        return true;
    }

    /**
     * A loop of playing turn
     * @param rules Rules object
     * @param board Board object
     * @param in Input object
     * @param out OutputStream object
     * @return True if there was no exit from the turn
     * @throws IOException
     */
    private boolean playTurn(Rules<Input, OutputStream> rules, Board<OutputStream> board, Input in, OutputStream out) throws IOException {
        boolean goOn = true;
        Optional<Player<Input, OutputStream>> opt = rules.nextTurnPlayer();
        Player<Input, OutputStream> curPlayer = null;
        if (opt.isPresent()) {
            curPlayer = opt.get();
        } else {
            goOn = false;
        }
        while (goOn) {
            try {
                var turn = curPlayer.play(in, out);
                if (turn.isEmpty() || !board.takeTurn(turn.get())) {
                    out.write(("The turn is impossible! Would you like to try again (y/n)?"
                            + System.lineSeparator()).getBytes());
                    goOn = in.nextStr().toLowerCase().equals("y");
                } else {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                out.write(("Invalid cell's coordinates! Would you like to try input the coordinates again (y/n)?"
                        + System.lineSeparator()).getBytes());
                goOn = in.nextStr().toLowerCase().equals("y");
            }
        }
        return false;
    }
}
