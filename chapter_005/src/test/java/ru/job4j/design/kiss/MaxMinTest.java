package ru.job4j.design.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class MaxMinTest {

    @Test
    public void whenIntMax() {
        List<Integer> list = List.of(2, 5, 10, -1, 0, 3);
        MaxMin mm = new MaxMin();
        Comparator<Integer> comp = Comparator.comparingInt(el -> el);
        assertThat(mm.max(list, comp), is(10));
    }

    @Test
    public void whenIntMin() {
        List<Integer> list = List.of(2, 5, 10, -1, 0, 3);
        MaxMin mm = new MaxMin();
        Comparator<Integer> comp = Comparator.comparingInt(el -> el);
        assertThat(mm.min(list, comp), is(-1));
    }

}