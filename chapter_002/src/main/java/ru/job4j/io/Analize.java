package ru.job4j.io;

import java.io.*;

public class Analize {

    public void unavailable(String source, String target) {
        String inputLine;
        boolean isUnavailable = false;
        StringBuilder outputLine = new StringBuilder();
        PrintWriter writer = null;
        String time = "";
        String type;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)));
            while ((inputLine = reader.readLine()) != null) {
                if (inputLine.isEmpty()) {
                    continue;
                }
                type = inputLine.split(" ")[0];
                time = inputLine.split(" ")[1];
                if ((type.equals("400") || type.equals("500")) && !isUnavailable) {
                    outputLine.append(time).append(";");
                    isUnavailable = true;
                }
                if ((type.equals("200") || type.equals("300")) && isUnavailable) {
                    isUnavailable = false;
                    outputLine.append(time);
                    writer.println(outputLine.toString());
                    outputLine.setLength(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                if (isUnavailable) {
                    outputLine.append(time);
                    writer.println(outputLine.toString());
                }
                writer.close();
            }
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
