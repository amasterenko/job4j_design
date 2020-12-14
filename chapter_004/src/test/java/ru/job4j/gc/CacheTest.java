package ru.job4j.gc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class CacheTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenEmptyCache() {
        FileCache cache = new FileCache(folder.getRoot().toPath());
        assertThat(cache.size(), is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyNotFound() {
        FileCache cache = new FileCache(folder.getRoot().toPath());
        String result = cache.get("source.txt");
    }

    @Test
    public void whenCacheMissThenLoad() throws IOException {
        File source = folder.newFile("source.txt");
        String content = "line1\n"
                        + "line2\n"
                        + "line3\n";
        Files.writeString(source.toPath(), content);
        FileCache cache = new FileCache(folder.getRoot().toPath());
        String result = cache.get("source.txt");
        assertThat(result, is(content));
    }

    @Test
    public void whenCacheHit() throws IOException {
        File source = folder.newFile("source.txt");
        String content = "line1\n"
                        + "line2\n"
                        + "line3\n";
        Files.writeString(source.toPath(), content);
        FileCache cache = new FileCache(folder.getRoot().toPath());
        cache.get("source.txt");
        assertThat(source.delete(), is(true));
        String result = cache.get("source.txt");
        assertThat(result, is(content));
    }

}