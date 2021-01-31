package ru.job4j.design.tictactoe;

import java.io.IOException;
import java.io.OutputStream;

public class ConsoleGameShowResult {
    /**
     * Shows win or draw result.
     * @param rules Game rules
     * @param out OutputStream
     */
    public void show(Rules<Input, OutputStream> rules, OutputStream out) {
        try {
            if (rules.getWinner().isPresent()) {
                out.write((rules.getWinner().get() + " wins!" + System.lineSeparator()).getBytes());
            } else if (!rules.isNextTurnPossible()) {
                out.write(("Draw." + System.lineSeparator()).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
