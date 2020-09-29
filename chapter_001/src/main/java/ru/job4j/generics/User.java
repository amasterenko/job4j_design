package ru.job4j.generics;

public class User extends Base {
    private String name;
    private String login;

    protected User(String id, String name, String login) {
        super(id);
        this.name = name;
        this.login = login;
    }
}
