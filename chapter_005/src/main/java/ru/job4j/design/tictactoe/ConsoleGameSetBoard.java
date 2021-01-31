package ru.job4j.design.tictactoe;

import java.io.IOException;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.Optional;

public class ConsoleGameSetBoard {
    /**
     * Creates a board object, checks its size
     * @param in Input object
     * @param out OutputStream
     * @return Optional with Board object if it was created
     */
    public Optional<Board<OutputStream>> boardInit(Input in, OutputStream out) {
        boolean goOn = true;
        Board<OutputStream> board = null;
        while (goOn) {
            try {
                out.write(("Input the board's size:").getBytes());
                board = new ConsoleBoard(Integer.parseInt(in.nextStr()));
                board.print(out);
                goOn = false;
            } catch (IllegalArgumentException | InputMismatchException | IOException e) {
                try {
                    out.write(("Invalid board size! Would you like to try input the size again (y/n)?"
                            + System.lineSeparator()).getBytes());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                goOn = in.nextStr().toLowerCase().equals("y");
            }
        }
        return Optional.ofNullable(board);
    }
}
