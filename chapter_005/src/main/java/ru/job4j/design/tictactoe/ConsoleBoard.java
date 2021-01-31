package ru.job4j.design.tictactoe;

import java.io.IOException;
import java.io.OutputStream;

public class ConsoleBoard implements Board<OutputStream> {
    private final Object[][] board;
    private final int size;
    private int freeCells;

    public ConsoleBoard(int boardSize) {
        if (boardSize <= 0) {
            throw new IllegalArgumentException("Size must be grater than or equal to 0.");
        }
        this.board = new Object[boardSize][boardSize];
        this.size = boardSize;
        this.freeCells = boardSize * boardSize;
    }

    /**
     * Takes a Cell object and checks if the turn is possible
     * @param cell Cell object with coordinates of a turn
     * @return true if the cell already exists
     */
    @Override
    public boolean takeTurn(Cell<OutputStream> cell) {
        if (cell.getX() < 1 || cell.getX() > size || cell.getY() < 0 || cell.getY() > size) {
            throw new IllegalArgumentException("The cell coordinates must be in the range of [1.." + size + "]");
        }
        if (board[cell.getX() - 1][cell.getY() - 1] != null) {
            return false;
        }
        board[cell.getX() - 1][cell.getY() - 1] = cell.getMark();
        freeCells--;
        return true;
    }

    @Override
    public boolean isFull() {
        return freeCells == 0;
    }
    /**
     *
     * @return true if the board has at least one filled line
     */

    @Override
    public boolean hasCompletedLine() {
        return checkHorizontalLines() || checkVerticalLines() || checkDiagonalLines();
    }

    /**
     *
     * @return true if the board has at least one filled horizontal line
     */
    private boolean checkHorizontalLines() {
        boolean rsl = false;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                rsl = board[i][j] == board[i][0] && board[i][0] != null;
                if (!rsl) {
                    break;
                }
            }
            if (rsl) {
                break;
            }
        }
        return rsl;
    }

    /**
     *
     * @return true if the board has at least one filled vertical line
     */

    private boolean checkVerticalLines() {
        boolean rsl = false;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                rsl = board[j][i] == board[0][i] && board[0][i] != null;
                if (!rsl) {
                    break;
                }
            }
            if (rsl) {
                break;
            }
        }
        return rsl;
    }

    /**
     *
     * @return true if the board has at least one diagonal
     */

    private boolean checkDiagonalLines() {
        boolean rsl = false;
        for (int i = 1; i < size; i++) {
            rsl = board[i][i] == board[0][0] && board[0][0] != null;
            if (!rsl) {
                break;
            }
        }
        if (rsl) {
            return rsl;
        }
        for (int i = 1; i < size; i++) {
            rsl = board[size - 1 - i][i] == board[size - 1][0] && board[size - 1][0] != null;
            if (!rsl) {
                break;
            }
        }
        return rsl;
    }

    public void print(OutputStream screen) {
        try {
            screen.write(("   ").getBytes());
            for (int i = 0; i < size; i++) {
                screen.write(("  " + (i + 1) + " ").getBytes());
            }
            screen.write(System.lineSeparator().getBytes());
            writeLine(screen);
            for (int i = 0; i < size; i++) {
                screen.write(((i + 1) + "  |").getBytes());
                for (int j = 0; j < size; j++) {
                    if (board[i][j] != null) {
                        Mark<OutputStream> mark = (Mark<OutputStream>) board[i][j];
                        mark.print(screen);
                    } else {
                        screen.write("   ".getBytes());
                    }
                    screen.write("|".getBytes());
                }
                screen.write(System.lineSeparator().getBytes());
                writeLine(screen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeLine(OutputStream screen) {
        try {
            screen.write("   +".getBytes());
            screen.write("---+".repeat(size).getBytes());
            screen.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
