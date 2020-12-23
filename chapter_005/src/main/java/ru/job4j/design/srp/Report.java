package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public interface Report {
    String get(Predicate<Employee> filter, Store store);
}