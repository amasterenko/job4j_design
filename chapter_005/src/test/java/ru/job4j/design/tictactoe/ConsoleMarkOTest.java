package ru.job4j.design.tictactoe;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleMarkOTest {

    @Test
    public void print() {
        var out = new ByteArrayOutputStream();
        new ConsoleMarkO().print(out);
        assertThat(out.toString(), is(" O "));
    }
}