package ru.job4j.collection;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {
    @Test
    public void whenUsersAddDelete() {
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        Analize.User user4 = new Analize.User(4, "user4");
        Analize.User user5 = new Analize.User(5, "user5");
        Analize.User user6 = new Analize.User(6, "user6");
        List<Analize.User> prevList = (Arrays.asList(user1, user2, user3));
        List<Analize.User> curList = (Arrays.asList(user2, user3, user4, user5, user6));
        Analize.Info expectedInfo = new Analize.Info(3, 0, 1);
        assertThat(Analize.diff(prevList, curList), is(expectedInfo));
    }

    @Test
    public void whenUsersAddDeleteChange() {
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        Analize.User user4 = new Analize.User(4, "user4");
        Analize.User user5 = new Analize.User(5, "user5");
        Analize.User user3upd = new Analize.User(3, "user3-upd");
        List<Analize.User> prevList = (Arrays.asList(user1, user2, user3));
        List<Analize.User> curList = (Arrays.asList(user1, user3upd, user4, user5));
        Analize.Info expectedInfo = new Analize.Info(2, 1, 1);
        assertThat(Analize.diff(prevList, curList), is(expectedInfo));
    }

    @Test
    public void whenUsersDeleteChange() {
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        Analize.User user4 = new Analize.User(4, "user4");
        Analize.User user5 = new Analize.User(5, "user5");
        Analize.User user1upd = new Analize.User(1, "user1-upd");
        Analize.User user3upd = new Analize.User(3, "user3-upd");
        List<Analize.User> prevList = (Arrays.asList(user1, user2, user3, user4, user5));
        List<Analize.User> curList = (Arrays.asList(user3upd, user1upd));
        Analize.Info expectedInfo = new Analize.Info(0, 2, 3);
        assertThat(Analize.diff(prevList, curList), is(expectedInfo));
    }

    @Test
    public void whenUsersAddChange() {
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        Analize.User user4 = new Analize.User(4, "user4");
        Analize.User user5 = new Analize.User(5, "user5");
        Analize.User user1upd = new Analize.User(1, "user1-upd");
        Analize.User user2upd = new Analize.User(2, "user2-upd");
        Analize.User user3upd = new Analize.User(3, "user3-upd");
        List<Analize.User> prevList = (Arrays.asList(user1, user2, user3, user1));
        List<Analize.User> curList = (Arrays.asList(user3upd, user1upd, user4, user5, user2upd));
        Analize.Info expectedInfo = new Analize.Info(2, 3, 0);
        assertThat(Analize.diff(prevList, curList), is(expectedInfo));
    }

    @Test
    public void whenUsersAdd() {
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        Analize.User user4 = new Analize.User(4, "user4");
        Analize.User user5 = new Analize.User(5, "user5");
        List<Analize.User> prevList = (Arrays.asList(user1, user2, user3));
        List<Analize.User> curList = (Arrays.asList(user1, user2, user3, user4, user5));
        Analize.Info expectedInfo = new Analize.Info(2, 0, 0);
        assertThat(Analize.diff(prevList, curList), is(expectedInfo));
    }

    @Test
    public void whenUsersChange() {
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        Analize.User user1upd = new Analize.User(1, "user1-upd");
        Analize.User user2upd = new Analize.User(2, "user2-upd");
        Analize.User user3upd = new Analize.User(3, "user3-upd");
        List<Analize.User> prevList = (Arrays.asList(user1, user2, user3));
        List<Analize.User> curList = (Arrays.asList(user1upd, user2upd, user3upd));
        Analize.Info expectedInfo = new Analize.Info(0, 3, 0);
        assertThat(Analize.diff(prevList, curList), is(expectedInfo));
    }

    @Test
    public void whenCurrentUsersEmpty() {
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        List<Analize.User> prevList = (Arrays.asList(user1, user2, user3));
        List<Analize.User> curList = Collections.emptyList();
        Analize.Info expectedInfo = new Analize.Info(0, 0, 3);
        assertThat(Analize.diff(prevList, curList), is(expectedInfo));
    }

    @Test
    public void whenPreviousUsersEmpty() {
        Analize.User user1 = new Analize.User(1, "user1");
        Analize.User user2 = new Analize.User(2, "user2");
        Analize.User user3 = new Analize.User(3, "user3");
        List<Analize.User> prevList = Collections.emptyList();
        List<Analize.User> curList = (Arrays.asList(user1, user2, user3));
        Analize.Info expectedInfo = new Analize.Info(3, 0, 0);
        assertThat(Analize.diff(prevList, curList), is(expectedInfo));
    }

}