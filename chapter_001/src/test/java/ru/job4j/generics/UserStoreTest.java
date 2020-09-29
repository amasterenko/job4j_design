package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class UserStoreTest {
    @Test
    public void whenAddAndFind() {
        UserStore store = new UserStore();
        User user1 = new User("1", "user1", "user1login1");
        User user2 = new User("2", "user2", "user1login2");
        User user3 = new User("3", "user3", "user1login3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        assertThat(store.findById("1"), is(user1));
        assertThat(store.findById("2"), is(user2));
        assertThat(store.findById("3"), is(user3));
    }

    @Test
    public void whenDelete() {
        UserStore store = new UserStore();
        User user1 = new User("1", "user1", "user1login1");
        User user2 = new User("2", "user2", "user1login2");
        User user3 = new User("3", "user3", "user1login3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        store.delete("2");
        assertThat(store.findById("2"), is(nullValue()));
    }

    @Test
    public void whenReplace() {
        UserStore store = new UserStore();
        User user1 = new User("1", "user1", "user1login1");
        User user2 = new User("2", "user2", "user1login2");
        User user3 = new User("3", "user3", "user1login3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        store.replace("2", user1);
        assertThat(store.findById("1"), is(user1));
        assertThat(store.findById("2"), is(nullValue()));
        assertThat(store.findById("3"), is(user3));
    }

    @Test
    public void whenReplaceFailed() {
        UserStore store = new UserStore();
        User user1 = new User("1", "user1", "user1login1");
        store.add(user1);
        assertThat(store.replace("2", user1), is(false));
    }

    @Test
    public void whenDeleteFailed() {
        UserStore store = new UserStore();
        User user1 = new User("1", "user1", "user1login1");
        store.add(user1);
        assertThat(store.delete("2"), is(false));
    }

    @Test
    public void whenFindNull() {
        UserStore store = new UserStore();
        User user1 = new User("1", "user1", "user1login1");
        store.add(user1);
        assertThat(store.findById("2"), is(nullValue()));
    }

}