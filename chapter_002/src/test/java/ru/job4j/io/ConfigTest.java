package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./test/data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Dr. Evil")
        );
    }

    @Test
    public void whenPairsCommentsEmptyLine() {
        String path = "./data/pairs_comments_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name1"),
                is("Dr. Evil")
        );
        assertThat(
                config.value("name2"),
                is("Austin Powers")
        );
        assertThat(
                config.value("name3"),
                is("Mini-Me")
        );
        assertThat(
                config.value("##Comment"),
                is(Matchers.nullValue())
        );
        assertThat(
                config.value(""),
                is(Matchers.nullValue())
        );
    }

}