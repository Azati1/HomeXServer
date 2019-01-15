package com.company.Net;

import java.util.Arrays;

public class Command {

    private String funcName;
    private String[] args;

    public Command(String funcName, String[] args) {
        this.funcName = funcName;
        this.args = args;
    }

    public String getFuncName() {
        return funcName;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return funcName + "#" + Arrays.toString(args);
    }

    public static Command parse(String value) {
        String funcName = "";
        String[] args = new String[]{""};

        int i = 0;
        int j = 0;

        while (value.charAt(i) != '#')
            funcName += value.charAt(i++);
        i++;
        while (value.length() > i) {
            if (value.charAt(i) == '[' || value.charAt(i) == ']') {
                i++;
                continue;
            }
            while (value.charAt(i) != ',' || value.length() > i)
                args[j] += value.charAt(i++);
            j++;
        }
        Command command = new Command(funcName, args);
        return command;

    }

}