package ru.job4j.design.srp;

import java.util.function.Predicate;

public class AccountingReport implements Report {
    private String currency;
    private double rate;

    public AccountingReport(String currency, double rate) {
        this.rate = rate;
        this.currency = currency;
    }

    @Override
    public String get(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary(").append(currency).append(");");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() / rate).append(";");
        }
        text.append(System.lineSeparator());
        return text.toString();
    }
}
