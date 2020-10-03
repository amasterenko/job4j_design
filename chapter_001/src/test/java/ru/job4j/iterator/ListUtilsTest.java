package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterFirstElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void whenAddAfterLastElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 1, 3);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 3, 3);
    }

    @Test
    public void whenRemoveIfPredicateTrue() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThat(ListUtils.removeIf(input, i -> i == 1 || i == 3 || i == 5),
                Is.is(Arrays.asList(2, 4)));
    }

    @Test
    public void whenRemoveIfPredicateFalse() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        assertThat(ListUtils.removeIf(input, i -> i == 3),
                Is.is(Arrays.asList(1, 2)));
    }

    @Test
    public void whenRemoveIfListEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList());
        assertThat(ListUtils.removeIf(input, i -> i == 3),
                Is.is(Arrays.asList()));
    }

    @Test
    public void whenReplaceIfPredicateTrue() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThat(ListUtils.replaceIf(input, i -> i == 1 || i == 3 || i == 5, 0),
                Is.is(Arrays.asList(0, 2, 0, 4, 0)));
    }

    @Test
    public void whenReplaceIfPredicateFalse() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        assertThat(ListUtils.replaceIf(input, i -> i == 3, 0),
                Is.is(Arrays.asList(1, 2)));
    }

    @Test
    public void whenReplaceIfListEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList());
        assertThat(ListUtils.replaceIf(input, i -> i == 3, 1),
                Is.is(Arrays.asList()));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3, 5));
        assertThat(ListUtils.removeAll(input, elements),
                Is.is(Arrays.asList(2, 4)));
    }

    @Test
    public void whenRemoveAllWhenListEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList());
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3, 5));
        assertThat(ListUtils.removeAll(input, elements),
                Is.is(Arrays.asList()));
    }

    @Test
    public void whenRemoveAllWhenElementsEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> elements = new ArrayList<>(Arrays.asList());
        assertThat(ListUtils.removeAll(input, elements),
                Is.is(Arrays.asList(1, 2, 3)));
    }

}