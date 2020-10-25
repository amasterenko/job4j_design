package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchDuplicateFiles implements FileVisitor<Path> {
    private List<Path> listPaths = new ArrayList<>();
    private Map<FileObj, Path> map = new HashMap<>();

    public List<Path> getPaths() {
        return listPaths;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.toFile().getName();
        FileObj fileObj = new FileObj(fileName, file.toFile().length());
        Path path = map.putIfAbsent(fileObj, file);
        if (path != null) {
            listPaths.add(path);
            listPaths.add(file);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    private static class FileObj {
        String name;
        long size;

        public FileObj(String name, long size) {
            this.name = name.toLowerCase();
            this.size = size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            FileObj file = (FileObj) o;
            return size == file.size
                    && Objects.equals(name, file.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, size);
        }
    }
}
