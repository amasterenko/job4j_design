package ru.job4j.io;

import java.util.LinkedList;

public class Shell {
    private LinkedList<String> path = new LinkedList<>();

    public void cd(String path) {
        String[] splited = path.split("/");
        for (String subStr : splited) {
            if (subStr.isEmpty()) {
                continue;
            }
            if (subStr.equals("..")) {
                if (this.path.size() > 0) {
                    this.path.removeLast();
                }
                continue;
            }
            this.path.add(subStr);
        }
    }

    public String pwd() {
        if (path.isEmpty()) {
            return "/";
        }
        StringBuilder result = new StringBuilder();
        for (String subStr : this.path) {
            result.append("/").append(subStr);
        }
        return result.toString();
    }
}
