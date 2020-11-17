package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Config configReader = new Config("app.properties");
            configReader.load();
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/idea_db";
            String login = configReader.value("hibernate.connection.username");
            String password = configReader.value("hibernate.connection.password");
            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println(metaData.getUserName());
                System.out.println(metaData.getURL());
            }
    }
}