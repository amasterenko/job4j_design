package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SearchDuplicates {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Specify the directory path as a parameter!");
            return;
        }
        Path start = Paths.get(args[0]);
        search(start).forEach(System.out::println);
    }

    public static List<Path> search(Path root) throws IOException {
        SearchDuplicateFiles seacher = new SearchDuplicateFiles();
        Files.walkFileTree(root, seacher);
        return seacher.getPaths();
    }
}
