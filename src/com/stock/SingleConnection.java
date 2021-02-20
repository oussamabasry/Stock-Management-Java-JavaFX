package com.stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
    private String dbName = "stock_management";
    private String dataBaseUser = "root";
    private String dataBasePassword = "";
    private String url = "jdbc:mysql://localhost:3306/" + dbName;
    private static Connection connection = null;

    private SingleConnection() {
        try {
            connection = DriverManager.getConnection(url, dataBaseUser, dataBasePassword);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (connection == null) new SingleConnection();
        return connection;
    }
}
