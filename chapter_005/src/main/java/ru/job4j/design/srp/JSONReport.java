package ru.job4j.design.srp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.Predicate;

public class JSONReport implements Report {

    @Override
    public String get(Predicate<Employee> filter, Store store) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        JSONObject json = new JSONObject();
        json.put("header", "Name; Hired; Fired; Salary;");
        JSONArray rows = new JSONArray();
        for (Employee employee : store.findBy(filter)) {
            StringBuilder row = new StringBuilder();
            row.append(employee.getName()).append(";")
                    .append(sdf.format(employee.getHired().getTime())).append(";")
                    .append(sdf.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";");
            rows.put(row);
        }
        json.put("rows", rows);
        return json.toString();
    }
}
