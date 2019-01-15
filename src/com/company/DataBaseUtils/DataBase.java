package com.company.DataBaseUtils;

import com.company.Devices.SmartDevice;
import com.company.Net.InternetServer;
import com.company.Net.Request;
import com.company.Net.Response;
import com.company.Net.TCPConnection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private List<SmartDevice> smartDevices;
    private DBProcessor dbProcessor;
    private Statement statement;
    private InternetServer server;

    String userName = "root";
    String password = "bulat001";
    String connectionURL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    private Gson gson;

    public DataBase(InternetServer server) throws ClassNotFoundException, SQLException {

        this.server = server;

        gson = new GsonBuilder().setPrettyPrinting().create();

        dbProcessor = new DBProcessor(connectionURL, userName, password);
        statement = dbProcessor.getConnection().createStatement();

        smartDevices = loadSmartDevices();
    }

    private List<SmartDevice> loadSmartDevices() {
        return new ArrayList<>();
    }

    public void addDevice(SmartDevice smartDevice) {
        smartDevices.add(smartDevice);
    }

    public SmartDevice getSmartDevice(int id) throws Exception {
        for (SmartDevice smartDevice : smartDevices) {
            if (smartDevice.getId() == id)
                return smartDevice;
        }
        throw new Exception("getSmartDevice exception while searching smart device by id");
    }

    public List<SmartDevice> getAllSmartDevices() {
        return smartDevices;
    }

    public void request(String stringRequest, TCPConnection connection) {

        Request request = gson.fromJson(stringRequest, Request.class);

        if (stringRequest.contains("changeColor")) {

        } else if (stringRequest.contains("turnOff")) {

        } else if (stringRequest.contains("turnOn")) {

        } else if (stringRequest.contains("register")) {
            Response result = register(request);

            String jsonResult = gson.toJson(result);

            connection.sendString(jsonResult);
        } else if (stringRequest.contains("login")) {
            Response result = login(request);

            String jsonResult = gson.toJson(result);

            connection.sendString(jsonResult);
        }
    }

    private Response register(Request request) {

        Response response = new Response(request.getId(), request.getAddress(), request.getSender(), request.getFuncName(), new String[]{"error"});

        String[] args = request.getFuncArgs();

        String firstNameString = args[0];
        String emailString = args[1];
        String passwordString = args[2];

        try {
            statement = dbProcessor.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO users (firstName, email, password) VALUES ('" + firstNameString + "', '" + emailString + "', '" + passwordString + "')");
            response = new Response(request.getId(), request.getAddress(), request.getSender(), request.getFuncName(), new String[]{"ok"});
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

    private Response login(Request request) {

        int id = request.getId();
        String address = request.getSender();
        String sender = request.getAddress();
        String funcName = request.getFuncName();
        String[] args = request.getFuncArgs();

        String email = args[0];
        String password = args[1];

        Response response = new Response(id, address, sender, funcName, new String[]{"error"});

        boolean isEmpty = true;

        try {
            statement = dbProcessor.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE email = '" + email + "' AND password = '" +password + "'");
            String name = "";
            while (resultSet.next()) {
                isEmpty = false;
                name = resultSet.getString("firstName");
            }
            if (!isEmpty) {
                response = new Response(id, address, sender, funcName, new String[]{"ok", name});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return response;
    }

}
