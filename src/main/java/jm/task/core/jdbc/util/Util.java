package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String username = "root123";
    private static String password = "root123";
    private static String url = "jdbc:mysql://localhost:3306 / pre-project1.1";

    static Connection connection;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}

