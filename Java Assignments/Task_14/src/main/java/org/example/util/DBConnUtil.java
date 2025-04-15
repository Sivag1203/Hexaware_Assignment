package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {

    /**
     * Establishes a database connection using the given properties file.
     * @param propFileName the name of the properties file.
     * @return Connection object.
     */
    public static Connection getConnection(String propFileName) {
        Connection conn = null;
        try {
            Properties props = DBPropertyUtil.loadProperties(propFileName);
            if (props == null) {
                throw new RuntimeException("Could not load DB properties.");
            }

            String url = props.getProperty("url");
            String user = props.getProperty("username");
            String pass = props.getProperty("password");

            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }
}
