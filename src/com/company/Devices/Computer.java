package com.company.Devices;

import com.company.Net.TCPConnection;

public class Computer extends Device {

    private static final String TYPE = "Computer";

    public Computer(String ip, String name) {
        super(ip, name, TYPE);
    }

    @Override
    public void waitMessage(TCPConnection connection) {

    }

    @Override
    public String getType() {
        return TYPE;
    }
}
