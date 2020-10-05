package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSetTest {

    @Test
    public void whenAddThenIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        String rsl = set.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddExistingElementThenIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("second");
        set.add("first");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        Iterator<String> it = set.iterator();
        set.add("second");
        it.next();
    }
}