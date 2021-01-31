package ru.job4j.design.tictactoe;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsolePlayerTest {

    @Test
    public void whenValidInput() {
        var in = new ByteArrayInputStream(("1 2" + System.lineSeparator()).getBytes());
        var out = new ByteArrayOutputStream();
        var mark = new ConsoleMarkX();
        var player = new ConsolePlayer("player", mark);
        var expected = Optional.of(new ConsoleCell(1, 2, mark));
        assertThat(player.play(new ConsoleInput(in), out), is(expected));
    }

    @Test
    public void whenInvalidInput() {
        var in = new ByteArrayInputStream(("one two" + System.lineSeparator()).getBytes());
        var out = new ByteArrayOutputStream();
        var player = new ConsolePlayer("player", new ConsoleMarkX());
        var expected = Optional.empty();
        assertThat(player.play(new ConsoleInput(in), out), is(expected));
    }
}