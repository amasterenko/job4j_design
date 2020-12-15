package ru.job4j.gc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCache {
    private SoftReferenceCache<String, String> cache = new SoftReferenceCache<>();
    private final Path dir;

    public FileCache(Path dir) {
        this.dir = dir;
    }

    public String get(String key) {
        String fileContent = "";
        if (cache.get(key) != null) {
            fileContent = cache.get(key).get();
        }
        if (fileContent == null || fileContent.isEmpty()) {
            try {
                fileContent = Files.readString(Path.of(dir + "/" + key));
                cache.load(key, fileContent);
            } catch (IOException e) {
                throw new IllegalArgumentException("There is no such file or the file can't be read!");
            }
        }
        return fileContent;
    }

    public int size() {
        return cache.size();
    }

    public static void main(String[] args) {
        FileCache cache = new FileCache(Path.of("./chapter_004/data"));
            System.out.println(cache.get("names.txt"));
            System.out.println(cache.get("names2.txt"));
            System.out.println(cache.get("addresses.txt"));
    }

}
