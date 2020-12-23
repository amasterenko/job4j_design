package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new RegularReport();
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenAccountingReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 150000);
        store.add(worker);
        double currencyRate = 75;
        Report report = new AccountingReport("usd", currencyRate);
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary(usd);")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() / currencyRate).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        List<Employee> workers = List.of(
                new Employee("Ivan", now, now, 100),
                new Employee("Petr", now, now, 200),
                new Employee("Vladimir", now, now, 300));
        store.add(workers);
        Report report = new HRReport(Comparator.comparing(Employee::getSalary).reversed());
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expect = new StringBuilder("Name; Hired; Fired; Salary;").append(System.lineSeparator());
                for (int i = 2; i >= 0; i--) {
                expect.append(workers.get(i).getName()).append(";")
                        .append(workers.get(i).getHired()).append(";")
                        .append(workers.get(i).getFired()).append(";")
                        .append(workers.get(i).getSalary()).append(";")
                        .append(System.lineSeparator());
                }
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenDevelopReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new DevelopReport();
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>").append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("\t<meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("\t<title>Report</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<table>").append(System.lineSeparator())
                .append("\t<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>")
                .append(System.lineSeparator())
                .append("\t<tr><td>")
                .append(worker.getName()).append("</td><td>")
                .append(worker.getHired()).append("</td><td>")
                .append(worker.getFired()).append("</td><td>")
                .append(worker.getSalary()).append("</td></tr>")
                .append(System.lineSeparator())
                .append("</table>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}