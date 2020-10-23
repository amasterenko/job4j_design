package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTable {
    public static void main(String[] args) {
        try (FileOutputStream fileOut = new FileOutputStream("multiplication_table.txt")) {
            StringBuilder sOut = new StringBuilder("   ");
            for (int i = 1; i <= 10; i++) {
                sOut.append(i).append("  ");
            }
            sOut.append(System.lineSeparator());
            fileOut.write(sOut.toString().getBytes());
            sOut.setLength(0);
            for (int i = 1; i <= 10; i++) {
                sOut.append(i).append(i != 10 ? "  " : " ");
                for (int j = 1; j <= 10; j++) {
                    sOut.append(i * j).append(i * j >= 10 ? " " : "  ");
                }
                sOut.append(System.lineSeparator());
                fileOut.write(sOut.toString().getBytes());
                sOut.setLength(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
