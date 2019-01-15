package com.company.clients;

import com.company.Devices.Device;
import com.company.Devices.Hub;

public class HomeHubClient extends Client {

    public static void main(String[] args) {
        String ip = "localhost";
        int port = 3346;

        Device device = new Hub(ip, "Home");

        new HomeHubClient(ip, port, device);
    }

    private HomeHubClient(String ip, int port, Device device) {
        super(ip, port, device);
    }

}