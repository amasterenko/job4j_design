package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportEngine {
    private Store store;
    private Report report;

    public ReportEngine(Store store, Report rep) {
        this.store = store;
        this.report = rep;
    }

    public String generate(Predicate<Employee> filter) {
        return report.get(filter, store);
    }
}