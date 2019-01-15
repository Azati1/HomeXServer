package com.company.clients;

import com.company.Devices.Device;
import com.company.Devices.Mobile;
import com.company.Devices.SmartDevice;

public class SmartDeviceClient extends Client {
    protected SmartDeviceClient(String ip, int port, Device device) {
        super(ip, port, device);
    }

    public static void main(String[] args) {

        String ip = "localhost";
        int port = 3346;

        //Device device = new SmartDevice(ip, "Лампа в гостиной 4");
        Device device = new Mobile(ip, "мобилка");

        new SmartDeviceClient(ip, port, device);
    }
}
