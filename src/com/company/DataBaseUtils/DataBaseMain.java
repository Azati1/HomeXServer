package com.company.DataBaseUtils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseMain {

    static String userName = "root";
    static String password = "bulat001";
    static String connectionURL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    static private Connection connection;

    public static void main(String[] args) throws ClassNotFoundException {
        xmlTest();
    }

    private static void xmlTest() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Person person = new Person("lelele");
        String s = gson.toJson(person);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static class Person {
        private String name;
        public Person(String name) {
            this.name = name;
        }
    }
}
