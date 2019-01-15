package com.company.Devices;

import com.company.Net.TCPConnection;

public abstract class Device {

    protected String ip;
    protected String name;
    protected String type;

    public abstract void waitMessage(TCPConnection connection);

    public Device(String ip, String name, String type) {
        this.ip = ip;
        this.name = name;
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();
}