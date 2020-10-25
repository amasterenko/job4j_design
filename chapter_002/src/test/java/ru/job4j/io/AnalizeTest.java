package ru.job4j.io;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.Rule;

import java.io.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSourceHas200And300TypesThenTargetIsEmpty() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(source)))) {
            writer.println("200 10:56:01");
            writer.println("200 10:57:01");
            writer.println("300 10:58:01");
            writer.println("200 10:59:01");
            writer.println("300 11:01:02");
            writer.println("200 11:02:02");
        }
        Analize analize = new Analize();
        analize.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString(), is(""));
    }

    @Test
    public void whenSourceHas400And500TypesThenTargetHasOneLine() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(source)))) {
            writer.println("400 10:56:01");
            writer.println("400 10:57:01");
            writer.println("500 10:58:01");
            writer.println("400 10:59:01");
            writer.println("400 11:01:02");
            writer.println("500 11:02:02");
        }
        String expected = "10:56:01;11:02:02";
        Analize analize = new Analize();
        analize.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString(), is(expected));
    }

    @Test
    public void whenSourceHasAllTypesThenTarget3Lines() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(source)))) {
            writer.println("200 10:56:01");
            writer.println("400 10:57:01");
            writer.println("500 10:58:01");
            writer.println("300 10:59:01");
            writer.println();
            writer.println("200 11:01:02");
            writer.println("400 11:02:02");
            writer.println("300 11:03:02");
            writer.println("500 11:04:02");
            writer.println("200 11:05:02");
        }
        String expected = "10:57:01;10:59:01"
                + "11:02:02;11:03:02"
                + "11:04:02;11:05:02";
        Analize analize = new Analize();
        analize.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString(), is(expected));
    }
}