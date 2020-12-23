package ru.job4j.design.ocp;
/**
 * В методе saveToDataBase явно создается объект Connector.
 * При необходимости подключиться к новому типу БД, необходимо будет вносить измения
 * в класс Logger
 */
public class Logger {
    public void saveToDataBase() {
        Connector con = new Connector("Params...");
        con.start();
    }
}
