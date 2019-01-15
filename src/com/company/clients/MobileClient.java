package com.company.clients;

import com.company.Devices.Device;
import com.company.Devices.Hub;

import java.util.Scanner;

public class MobileClient extends Client {

    public static void main(String[] args) {
        String ip = "18.224.39.45";
        int port = 3346;

        Scanner in = new Scanner(System.in);

        System.out.println("Введите свое имя");

        String name = in.next();

        Device device = new Hub(ip, name);

        new MobileClient(ip, port, device);
    }

    protected MobileClient(String ip, int port, Device device) {
        super(ip, port, device);
    }
}
