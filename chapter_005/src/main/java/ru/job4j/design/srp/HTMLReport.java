package ru.job4j.design.srp;

import java.util.function.Predicate;

public class HTMLReport implements Report {
    private Report devReport;

    public HTMLReport(Report devReport) {
        this.devReport = devReport;
    }

    @Override
    public String get(Predicate<Employee> filter, Store store) {
        return devReport.get(filter, store);
    }
}
