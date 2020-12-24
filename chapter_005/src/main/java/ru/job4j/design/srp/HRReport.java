package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRReport implements Report {
    private final Comparator<Employee> comparator;

    public HRReport(Comparator<Employee> comp) {
        this.comparator = comp;
    }

    @Override
    public String get(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        text.append(System.lineSeparator());
        List<Employee> sortedEmployee = store.findBy(e -> true);
        sortedEmployee.sort(comparator);
        for (Employee employee : sortedEmployee) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
