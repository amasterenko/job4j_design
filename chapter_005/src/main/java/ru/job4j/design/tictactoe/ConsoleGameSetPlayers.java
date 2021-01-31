package ru.job4j.design.tictactoe;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public class ConsoleGameSetPlayers {
    /**
     * Creates two players with different game marks, checks players name for uniqueness
     * @param in - Input object
     * @param out - OutputStream
     * @param markX - object for mark
     * @param markO - object for mark
     * @return - Optional with array of players if it was created
     */
    public Optional<Object[]> playersInit(Input in, OutputStream out,
                                          Mark<OutputStream> markX, Mark<OutputStream> markO) {
        ConsolePlayer[] players = null;
        boolean goOn = true;
        try {
            while (goOn) {
                out.write(("Input first player's name:").getBytes());
                String name1 = in.nextStr();
                out.write(("Input second player's name:").getBytes());
                String name2 = in.nextStr();
                if (name1.equals(name2)) {
                    out.write(("The names must be different! Would you like to try input names again(y/n)?"
                            + System.lineSeparator()).getBytes());
                    goOn = in.nextStr().toLowerCase().equals("y");
                } else {
                    players = new ConsolePlayer[2];
                    players[0] = new ConsolePlayer(name1, markX);
                    players[1] = new ConsolePlayer(name2, markO);
                    goOn = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(players);
    }
}
