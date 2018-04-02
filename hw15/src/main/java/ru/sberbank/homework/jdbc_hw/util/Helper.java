package ru.sberbank.homework.jdbc_hw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Helper {
    private static final String URL = "jdbc:hsqldb:file:/home/dmitry/projects/Homework/hw15/src/test/resources/hsqldb/schedule";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
