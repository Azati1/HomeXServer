package com.company.Devices;

import com.company.Net.TCPConnection;

import java.util.Scanner;

public class Mobile extends Device {

    private static final String TYPE = "MobileClient";

    public Mobile(String ip, String name) {
        super(ip, name, TYPE);
    }

    @Override
    public void waitMessage(TCPConnection connection) {
        Scanner in = new Scanner(System.in);
        while (true) {
            String msg = in.next();
            connection.sendString(msg);
        }
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
