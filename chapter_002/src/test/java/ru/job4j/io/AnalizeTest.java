package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizeTest {
    private final String targetFile = "target.log";

    @Test
    public void whenSourceHas200And300TypesThenTargetIsEmpty() {
        Analize analize = new Analize();
        analize.unavailable("./data/source200And300.log", targetFile);
        List<String> result = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(targetFile))) {
            result = reader.lines()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(Collections.EMPTY_LIST));
    }

    @Test
    public void whenSourceHas400And500TypesThenTargetHasOneLine() {
        Analize analize = new Analize();
        analize.unavailable("./data/source400And500.log", targetFile);
        List<String> result = null;
        List<String> expected = Arrays.asList("10:56:01;11:02:02");
        try (BufferedReader reader = new BufferedReader(new FileReader(targetFile))) {
            result = reader.lines()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(expected));
    }

    @Test
    public void whenSourceHasAllTypesThenTarget3Lines() {
        Analize analize = new Analize();
        analize.unavailable("./data/sourceAllTypes.log", targetFile);
        List<String> result = null;
        List<String> expected = Arrays.asList(
                "10:57:01;10:59:01",
                "11:02:02;11:03:02",
                "11:04:02;11:05:02");
        try (BufferedReader reader = new BufferedReader(new FileReader(targetFile))) {
            result = reader.lines()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result, is(expected));
    }
}