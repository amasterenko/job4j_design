package ru.job4j.design.tictactoe;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

public class ConsoleGameFlow implements GameFlow {
    private final Input in;
    private final OutputStream out;
    private final Mark<OutputStream> markX;
    private final Mark<OutputStream> markO;

    public ConsoleGameFlow(Input in, OutputStream out) {
        this.in = in;
        this.out = out;
        markX = new ConsoleMarkX();
        markO = new ConsoleMarkO();
    }

    /**
     * Starts the game loop
     */
    @Override
    public void start() {
        try {
            out.write(("*** Tic-tac-toe ***" + System.lineSeparator()).getBytes());
            Optional<Object[]> optPlayers;
            optPlayers = new ConsoleGameSetPlayers().playersInit(in, out, markX, markO);
            boolean goOn = optPlayers.isPresent();
            while (goOn) {
                Optional<Board<OutputStream>> optBoard;
                optBoard = new ConsoleGameSetBoard().boardInit(in, out);
                if (optBoard.isEmpty()) {
                    break;
                }
                Rules<Input, OutputStream> rules = new ConsoleRules((ConsolePlayer) optPlayers.get()[0],
                                                            (ConsolePlayer) optPlayers.get()[1],
                                                            optBoard.get());
                ConsoleGamePlayTurns playingTurns = new ConsoleGamePlayTurns();
                if (playingTurns.execute(rules, optBoard.get(), in, out)) {
                    ConsoleGameShowResult results = new ConsoleGameShowResult();
                    results.show(rules, out);
                }
                out.write(("Would you like to play again(y/n)?" + System.lineSeparator()).getBytes());
                goOn = in.nextStr().toLowerCase().equals("y");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Input systemIn = new ConsoleInput(System.in);
        GameFlow game = new ConsoleGameFlow(systemIn, System.out);
        game.start();
    }
}
