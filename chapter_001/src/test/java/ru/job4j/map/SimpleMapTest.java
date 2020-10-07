package ru.job4j.map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {
    @Test
    public void whenInsertThenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("1", 10);
        int rsl = map.get("1");
        assertThat(rsl, is(10));
    }

    @Test
    public void whenInsertThenIt() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("1", 10);
        map.insert("2", 20);
        map.insert("3", 30);
        Iterator<String> it = map.iterator();
        assertThat(it.next(), is("1"));
        assertThat(it.next(), is("2"));
        assertThat(it.next(), is("3"));
    }

    @Test
    public void whenGetEmpty() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertThat(map.get("1"), Matchers.nullValue());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("1", 10);
        Iterator<String> it = map.iterator();
        map.insert("2", 20);
        it.next();
    }

    @Test
    public void whenInsertThenDelete() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("1", 10);
        map.insert("2", 20);
        map.insert("3", 30);
        map.delete("2");
        assertThat(map.get("2"), Matchers.nullValue());
    }

    @Test
    public void whenInsertTheSameKeysThenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert("1", 10);
        map.insert("1", 20);
        assertThat(map.get("1"), is(20));
    }

    @Test
    public void whenInsertNullKeyThenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.insert(null, 10);
        map.insert("1", 20);
        assertThat(map.get(null), is(10));
    }

    @Test
    public void whenResizeThenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>(2, 0.5f);
        map.insert("1", 10);
        map.insert("2", 20);
        map.insert("3", 30);
        assertThat(map.get("1"), is(10));
        assertThat(map.get("2"), is(20));
        assertThat(map.get("3"), is(30));
    }
}