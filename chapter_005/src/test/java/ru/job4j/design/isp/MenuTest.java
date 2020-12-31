package ru.job4j.design.isp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MenuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void whenAddRootItem() {
        Item item = new Item("Item1", new TestAction());
        Menu menu = new Menu();
        menu.addItem(item);
        List<Item> expected = List.of(item);
        assertThat(menu.getItems(), is(expected));
    }

    @Test
    public void whenAddRootItemAndSubItem() {
        Item item1 = new Item("Item1", new TestAction());
        Item item2 = new Item("Item1.1", new TestAction());
        Menu menu = new Menu();
        menu.addItem(item1);
        menu.addItem(item2, item1);
        List<Item> expected = List.of(item1);
        assertThat(menu.getItems(), is(expected));
        assertThat(menu.getItems().get(0).getChildren().get(0), is(item2));
    }

    @Test
    public void whenDeleteRootItem() {
        Item item1 = new Item("Item1", new TestAction());
        Menu menu = new Menu();
        menu.addItem(item1);
        menu.deleteItem(item1);
        List<Item> expected = Collections.emptyList();
        assertThat(menu.getItems(), is(expected));
    }

    @Test
    public void whenDeleteRootItemByName() {
        Item item1 = new Item("Item1", new TestAction());
        Menu menu = new Menu();
        menu.addItem(item1);
        menu.deleteItem(item1.getName());
        List<Item> expected = Collections.emptyList();
        assertThat(menu.getItems(), is(expected));
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void whenGetAction() {
        Action action = new TestAction();
        Item item1 = new Item("Item1", action);
        Menu menu = new Menu();
        menu.addItem(item1);
        menu.getItemAction(item1).doAction();
        String expected = "Test action" + System.lineSeparator();
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void whenGetActionByName() {
        Action action = new TestAction();
        Item item1 = new Item("Item1", action);
        Menu menu = new Menu();
        menu.addItem(item1);
        menu.getItemAction(item1.getName()).doAction();
        String expected = "Test action" + System.lineSeparator();
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void whenPrintMenu() {
        Action action = new TestAction();
        Item item1 = new Item("Item1", action);
        Item item2 = new Item("Item1.1", action);
        Item item3 = new Item("Item2", action);
        Menu menu = new Menu();
        menu.addItem(item1);
        menu.addItem(item2, item1);
        menu.addItem(item3);
        StringBuilder expected = new StringBuilder();
        expected.append("┝").append(item1.getName()).append(System.lineSeparator());
        expected.append("┝").append("╺╺").append(item2.getName()).append(System.lineSeparator());
        expected.append("┝").append(item3.getName()).append(System.lineSeparator());
        menu.print();
        assertThat(outContent.toString(), is(expected.toString()));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

}