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
        if (cache.get(key) != null && cache.get(key).get() != null) {
            return cache.get(key).get();
        }
        try {
            String fileContent = Files.readString(Path.of(dir + "/" + key));
            cache.load(key, fileContent);
            return fileContent;
        } catch (IOException e) {
            throw new IllegalArgumentException("There is no such file or the file can't be read!");
        }
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
