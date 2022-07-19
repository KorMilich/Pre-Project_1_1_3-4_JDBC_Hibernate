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
    private static Util util;

    public static Util getUtil() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

    public Util() {
    }

    public Connection getConnection()   {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//        if (connection == null) {
//
//            return DriverManager.getConnection(url, username, password);
//        } else connection.isClosed();
//        return DriverManager.getConnection(url, username, password);



}

