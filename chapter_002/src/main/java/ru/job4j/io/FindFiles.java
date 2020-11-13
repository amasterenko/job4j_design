package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

public class FindFiles {
    public static void main(String[] args) {
        ArgsFind argsFind = new ArgsFind(args);
        if (!argsFind.validate()) {
            System.out.println("Wrong arguments!");
            System.out.println("Usage: java -jar find.jar -d c:/ -n *.txt -m|-f|-r -o log.txt\n"
                    + "-d - start directory\n"
                    + "-n - search pattern(file/mask/regexp)\n"
                    + "-m - search by mask\n"
                    + "-f - search by complete match\n"
                    + "-r - search by regexp\n"
                    + "-o - output file");
            return;
        }
        Path start = Paths.get(argsFind.getDir());
        List<Path> output;
        try {
            output = search(start, argsFind.getPattern());
        } catch (IOException e) {
            System.out.println("An i/o error occurred while searching files!");
            return;
        }
        try (PrintWriter printer = new PrintWriter(argsFind.getLogPath())) {
            if (output != null) {
                output.forEach(printer::println);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while creating the output file!");
            return;
        }
        System.out.println("Done!");

    }

    public static List<Path> search(Path root, String pattern) throws IOException {
        Pattern searchPattern = Pattern.compile(pattern);
        SearchFiles searcher = new SearchFiles(p -> searchPattern.matcher(p.toFile().getName()).matches());
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
