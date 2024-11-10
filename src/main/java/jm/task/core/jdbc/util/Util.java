package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static final String URL = "jdbc:mysql://localhost:3306/mysql";
    static final String USER = "root";
    static final String PASSWORD = "4619622790";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
