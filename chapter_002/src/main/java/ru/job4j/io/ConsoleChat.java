package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> log = new ArrayList<>();
    private List<String> answersCache = null;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private String getAnswer() {
        if (answersCache == null) {
            answersCache = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
                reader.lines().forEach(answersCache::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (answersCache.isEmpty()) {
            return "";
        }
        int rndLineIndx = (int) (Math.random() * answersCache.size());
        return answersCache.get(rndLineIndx);
    }

    private void print(String line) {
        System.out.print(line);
        log.add(line);
    }

    public void run() {
        print("Chat begins." + System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            boolean isBotStopped = false;
            boolean isRunning = true;
            while (isRunning) {
                print("User: ");
                String input = reader.readLine();
                if (input.toLowerCase().equals(OUT)) {
                    isRunning = false;
                    continue;
                }
                if (input.toLowerCase().equals(STOP)) {
                    isBotStopped = true;
                    continue;
                }
                if (input.toLowerCase().equals(CONTINUE)) {
                    isBotStopped = false;
                }
                if (!isBotStopped) {
                    print("Bot: " + getAnswer() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        print("Chat is over.");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            for (String line: log) {
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("log.txt", "answers.txt");
        cc.run();
    }
}