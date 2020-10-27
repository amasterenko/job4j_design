package ru.job4j.io;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArgZip {
    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        Set<Character> dict = new HashSet<>(Arrays.asList('d', 'e', 'o'));
        if (args.length != 3) {
            return false;
        }
        for (String arg: args) {
            if (arg.charAt(0) != '-'
                    || !(arg.charAt(2) == '=' || arg.charAt(2) == ' ')
                    || (arg.substring(3).isEmpty())) {
                return false;
            }
            if (!dict.remove(arg.charAt(1))) {
                return false;
            }
        }
        return true;
    }

    public String directory() {
        return getArgByName('d');
    }

    public String exclude() {
        String ex = getArgByName('e');
        return ex.replaceAll("[*]", "");
    }

    public String output() {
        return getArgByName('o');
    }

    private String getArgByName(Character name) {
        String rsl = null;
        for (String arg: args) {
            if (arg.charAt(1) == name) {
                rsl = arg.substring(3);
                break;
            }
        }
        return rsl;
    }
}
