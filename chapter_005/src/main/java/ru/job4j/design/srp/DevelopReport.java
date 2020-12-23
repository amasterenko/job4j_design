package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

public class DevelopReport implements Report {
    @Override
    public String get(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>").append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("\t<meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("\t<title>Report</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<table>").append(System.lineSeparator())
                .append("\t<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("\t<tr><td>").append(employee.getName()).append("</td>")
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td></tr>")
                    .append(System.lineSeparator());
        }
        text.append("</table>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        return text.toString();
    }
}
