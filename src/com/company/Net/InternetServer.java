package com.company.Net;

import com.company.DataBaseUtils.DataBase;
import com.company.Devices.Computer;
import com.company.Devices.Device;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;

public class InternetServer implements TCPConnectionListener {

    public static void main(String[] args) {
        new InternetServer();
    }

    private final ArrayList<TCPConnection> connectedUsers = new ArrayList<>();
    private final ArrayList<TCPConnection> connectedSmartDevices = new ArrayList<>();
    private final DataBase dataBase;

    private InternetServer() {
        System.out.println("InternetServer running...");
        try {
            dataBase = new DataBase(this);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Device device = new Computer("127.0.0.1", "Server");
        try (ServerSocket serverSocket = new ServerSocket(3346)) {
            while (true) {
                try {
                    new TCPConnection(this, serverSocket.accept(), device);
                } catch (IOException e) {
                    System.out.println("TCPConnection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPConnection connection) {

        String sender = connection.getSender().getName();
        System.out.println(connection);

        switch (sender) {
            case "MobileClient" : {
                System.out.println("new mobile connection");
                connectedUsers.add(connection);
                break;
            }

            case "WebClient" : {
                System.out.println("new web connection");
                break;
            }

            case "DesktopClient" : {
                System.out.println("new desktop connection");
                break;
            }

            case "SmartDevice" : {
                System.out.println("new device connection");
                connectedSmartDevices.add(connection);
                break;
            }

            case "Server" : {
                System.out.println("new server connection");
                break;
            }

            default : {
                System.out.println("new default connection");
                break;
            }
        }

        sendToAllConnections("Client connected: " + connection);
    }

    @Override
    public synchronized void onReceiveString(TCPConnection connection, String value) {

        System.out.println(value);
        runCommand(value, connection);
    }

    private void runCommand(String request, TCPConnection connection) {
        dataBase.request(request, connection);
    }

    @Override
    public synchronized void onDisconnect(TCPConnection connection) {
        connectedUsers.remove(connection);
        sendToAllConnections("Client disconnected: " + connection);
    }

    @Override
    public synchronized void onException(TCPConnection connection, Exception e) {
        System.out.println("TCPConnection exception: " + e);
    }

    private void sendToAllConnections(String value) {
        System.out.println(value);
        for (int i = 0; i < connectedUsers.size(); i++) {
            connectedUsers.get(i).sendString(value);
        }
    }
}
