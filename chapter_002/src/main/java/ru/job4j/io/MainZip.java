package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainZip {
    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        Zip zip = new Zip();
        if (!argZip.valid()) {
            throw new IllegalArgumentException("Invalid arguments! "
                    + "Usage: java -jar pack.jar -d=source dir path -e=excluded ext -o=target zip file path");
        }
        SearchFiles searcher = new SearchFiles(p -> !p.toFile().getName().endsWith(argZip.exclude()));
        Files.walkFileTree(Path.of(argZip.directory()), searcher);
        zip.packFiles(searcher.getPaths(), Path.of(argZip.output()));
        System.out.println("Done.");
    }
}
