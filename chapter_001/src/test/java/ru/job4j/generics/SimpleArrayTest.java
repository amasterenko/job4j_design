package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSizeWhileInitialize() {
        SimpleArray<String> simpleArray = new SimpleArray<>(-1);
    }

    @Test()
    public void whenAddAndGet() {
        SimpleArray<String> simpleArray = new SimpleArray<>(1);
        simpleArray.add("1");
        assertThat(simpleArray.get(0), is("1"));
    }

    @Test()
    public void whenWrongIndexWhileGet() {
        SimpleArray<String> simpleArray = new SimpleArray<>(1);
        simpleArray.add("1");
        assertThat(simpleArray.get(1), isEmptyOrNullString());
    }

    @Test()
    public void whenWrongIndexWhileSet() {
        SimpleArray<String> simpleArray = new SimpleArray<>(1);
        simpleArray.add("1");
        assertThat(simpleArray.set(2, "0"), is(false));
    }

    @Test()
    public void whenWrongIndexWhileRemove() {
        SimpleArray<String> simpleArray = new SimpleArray<>(1);
        simpleArray.add("1");
        assertThat(simpleArray.remove(2), is(false));
    }

    @Test()
    public void whenAddReturnsFalse() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        assertThat(simpleArray.add("1"), is(true));
        assertThat(simpleArray.add("2"), is(true));
        assertThat(simpleArray.add("3"), is(false));
        assertThat(simpleArray.add("4"), is(false));
    }

    @Test()
    public void whenAddSetRemoveSequence() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("1");
        simpleArray.add("2");
        simpleArray.add("3");
        simpleArray.add("4");
        simpleArray.add("5");
        simpleArray.set(0, "0");
        simpleArray.remove(3);
        assertThat(simpleArray.toString(), is("[0, 2, 3, 5]"));
    }

    @Test()
    public void whenRemoveSetAddSequence() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        assertThat(simpleArray.remove(1), is(false));
        assertThat(simpleArray.set(0, "0"), is(false));
        simpleArray.add("1");
        assertThat(simpleArray.toString(), is("[1]"));
    }

    @Test()
    public void whenAddRemoveAddSequence() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("1");
        simpleArray.add("2");
        simpleArray.add("3");
        assertThat(simpleArray.remove(0), is(true));
        assertThat(simpleArray.remove(0), is(true));
        assertThat(simpleArray.toString(), is("[3]"));
    }

    @Test
    public void whenEmptyIteratorThenHasNextFalse() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        Iterator<String> iterator = simpleArray.iterator();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyIteratorAndNextThenException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        Iterator<String> iterator = simpleArray.iterator();
        iterator.next();
    }

    @Test
    public void whenIteratorReadSequence() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("1");
        simpleArray.add("2");
        simpleArray.add("3");
        Iterator<String> iterator = simpleArray.iterator();
        assertThat(iterator.next(), is("1"));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.next(), is("3"));
    }

    @Test
    public void whenIteratorHasNextFalse() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("1");
        simpleArray.add("2");
        Iterator<String> iterator = simpleArray.iterator();
        iterator.next();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNextFromEmpty() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        Iterator<String> iterator = simpleArray.iterator();
        iterator.next();
    }
}