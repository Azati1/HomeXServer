package com.company.DataBaseUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBProcessor {

    private Connection connection;

    public DBProcessor(String connectionURL, String userName, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionURL, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }
}
