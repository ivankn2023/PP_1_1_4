package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private Util() {

    }

    private static final String URL = "jdbc:mysql://localhost:3306/userstable";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "password";

     public static SessionFactory getSessionFactory() {
             return new Configuration()
                     .addAnnotatedClass(User.class)
                     .setProperty("hibernate.connection.url", URL)
                     .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                     .setProperty("hibernate.connection.username", LOGIN)
                     .setProperty("hibernate.connection.password", PASSWORD)
                     .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                     .setProperty("show_sql", "true")
                     .setProperty("format_sql", "true")
                     .setProperty("hibernate.hbm2ddl.auto", "none")
                     .buildSessionFactory();
    }

    public static Connection getJDBCConnection() {

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
