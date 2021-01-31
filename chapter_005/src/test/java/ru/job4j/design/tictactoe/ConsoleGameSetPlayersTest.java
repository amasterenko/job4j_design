package ru.job4j.design.tictactoe;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleGameSetPlayersTest {
    private String sep = System.lineSeparator();

    @Test
    public void whenValidInput() {
        ConsoleGameSetPlayers setPlayers = new ConsoleGameSetPlayers();
        var markX = new ConsoleMarkX();
        var markO = new ConsoleMarkO();
        var in = new ByteArrayInputStream(("name1" + sep + "name2" + sep).getBytes());
        var out = new ByteArrayOutputStream();
        String expectedRequests = "Input first player's name:Input second player's name:";
        String notExpectedWarning = "The names must be different! Would you like to try input names again(y/n)?";
        ConsolePlayer[] expectedPlayers = {new ConsolePlayer("name1", markX),
                new ConsolePlayer("name2", markO)};
        var resultPlayerArray = setPlayers.playersInit(new ConsoleInput(in), out, markX, markO);
        assertThat(out.toString(), containsString(expectedRequests));
        assertThat(out.toString(), not(containsString(notExpectedWarning)));
        assertThat(resultPlayerArray.get(), is(expectedPlayers));
    }

    @Test
    public void whenInputTheSameNamesGetWarningAndExit() {
        ConsoleGameSetPlayers setPlayers = new ConsoleGameSetPlayers();
        var markX = new ConsoleMarkX();
        var markO = new ConsoleMarkO();
        var in = new ByteArrayInputStream(("thesamename" + sep + "thesamename" + sep + "n" + sep).getBytes());
        var out = new ByteArrayOutputStream();
        String expectedWarning = "The names must be different! "
                + "Would you like to try input names again(y/n)?";
        var resultPlayerArray = setPlayers.playersInit(new ConsoleInput(in), out, markX, markO);
        assertThat(out.toString(), containsString(expectedWarning));
        assertThat(resultPlayerArray.isEmpty(), is(true));
    }

    @Test
    public void whenInputTheSameNamesGetWarningAndInputDiffNames() {
        ConsoleGameSetPlayers setPlayers = new ConsoleGameSetPlayers();
        var markX = new ConsoleMarkX();
        var markO = new ConsoleMarkO();
        var in = new ByteArrayInputStream(("thesamename" + sep + "thesamename" + sep + "y" + sep + "name1"
                + sep + "name2" + sep).getBytes());
        var out = new ByteArrayOutputStream();
        String expectedWarning = "The names must be different! Would you like to try input names again(y/n)?";
        ConsolePlayer[] expectedPlayers = {new ConsolePlayer("name1", markX),
                                            new ConsolePlayer("name2", markO)};
        var resultPlayerArray = setPlayers.playersInit(new ConsoleInput(in), out, markX, markO);
        assertThat(out.toString(), containsString(expectedWarning));
        assertThat(resultPlayerArray.get(), is(expectedPlayers));
    }
}