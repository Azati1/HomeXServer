package com.company.Net;

public class Response {
    private String sender;
    private String address;
    private String funcName;
    private String[] funcArgs;
    private int id;

    public Response(int id, String sender, String address, String funcName, String[] funcArgs) {
        this.address = address;
        this.sender = sender;
        this.funcName = funcName;
        this.funcArgs = funcArgs;
    }

    public String getSender() {
        return sender;
    }

    public String getAddress() {
        return address;
    }

    public String getFuncName() {
        return funcName;
    }

    public String[] getFuncArgs() {
        return funcArgs;
    }
}
