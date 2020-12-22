package ru.job4j.gc;

public class User {
    private String name;
    private String login;
    private int age;
    private char sex;

    public User(String name, String login, int age, char sex) {
        this.name = name;
        this.login = login;
        this.age = age;
        this.sex = sex;
    }

    @Override
    protected void finalize() throws Throwable {
        //System.out.printf("Removed %d %n", age);
    }
}
