package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountingReport implements Report {
    private Converter converter;

    public AccountingReport(Converter converter) {
        this.converter = converter;
    }

    @Override
    public String get(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(converter.convert(employee.getSalary())).append(";");
        }
        text.append(System.lineSeparator());
        return text.toString();
    }
}
