package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class XMLReport implements Report {
    @Override
    public String get(Predicate<Employee> filter, Store store) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder text = new StringBuilder();
        text.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(System.lineSeparator())
                .append("<report>").append(System.lineSeparator())
                .append("<header>Name; Hired; Fired; Salary;</header>").append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<row>")
                    .append(employee.getName()).append(";")
                    .append(sdf.format(employee.getHired().getTime())).append(";")
                    .append(sdf.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append("</row>").append(System.lineSeparator());
        }
        text.append("<report>");
        return text.toString();
    }
}