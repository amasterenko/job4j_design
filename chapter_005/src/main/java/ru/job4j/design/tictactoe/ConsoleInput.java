package ru.job4j.design.tictactoe;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput implements Input {
    private final Scanner scanner;

    public ConsoleInput(InputStream is) {
        scanner = new Scanner(is);
    }

    @Override
    public String nextStr() {
        return scanner.next();
    }
}
