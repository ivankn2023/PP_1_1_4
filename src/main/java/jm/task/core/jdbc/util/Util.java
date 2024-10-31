package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Util(){

    }

    private static final String URL = "jdbc:mysql://localhost:3306/userstable";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() {

        Connection connection = null;
        Driver driver;
        try {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
