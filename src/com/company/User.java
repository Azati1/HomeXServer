package com.company;

public class User {

    private String ip;
    private String name;

    public User(String ip, String name) {
        this.ip = ip;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }
}
