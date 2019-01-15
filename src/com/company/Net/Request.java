package com.company.Net;

public class Request {

    private String sender;
    private String address;
    private String funcName;
    private String[] funcArgs;
    private int id;

    public Request(String sender, String address, String funcName, String[] funcArgs) {
        this.address = address;
        this.sender = sender;
        this.funcName = funcName;
        this.funcArgs = funcArgs;
    }

    public Request(int id, String sender, String address, String funcName, String[] funcArgs) {
        this.address = address;
        this.sender = sender;
        this.funcName = funcName;
        this.funcArgs = funcArgs;
        this.id = id;
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

    public int getId() {
        return id;
    }

    /*@Override
    public String toString() {
        return sender + ":" + command;
    }

    public Command getCommand() {
        return command;
    }

    public String getSender() {
        return sender;
    }

    public String getAddress() {
        return address;
    }

    public static Request parse(String value) {

        String sender = "";
        String address = "";
        String command = "";

        int i = 0;

        while (value.charAt(i) != ':')
            sender += value.charAt(i++);
        i++;
        while (value.charAt(i) != ':')
            address += value.charAt(i++);
        i++;
        while (value.length() > i) {
            command += value.charAt(i++);
        }

        Command command1 = Command.parse(command);
        Request request = new Request(sender, address, command1);

        return request;
    }*/
}
