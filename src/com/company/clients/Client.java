package com.company.clients;

import com.company.Devices.Device;
import com.company.Net.TCPConnection;
import com.company.Net.TCPConnectionListener;

import java.io.IOException;

public abstract class Client implements TCPConnectionListener {

    protected TCPConnection connection;

    protected Client(String ip, int port, Device device) {
        try {
            connection = new TCPConnection(this, ip, port, device);
            device.waitMessage(connection);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void onConnectionReady(TCPConnection connection) {
        System.out.println("TCPConnection ready: " + connection);
    }

    @Override
    public void onReceiveString(TCPConnection connection, String value) {
        System.out.println(value);
    }

    @Override
    public void onDisconnect(TCPConnection connection) {
        System.out.println("TCPConnection disconnect: " + connection);
    }

    @Override
    public void onException(TCPConnection connection, Exception e) {
        System.out.println("TCPConnection exception: " + connection + ", " + e);
    }

}