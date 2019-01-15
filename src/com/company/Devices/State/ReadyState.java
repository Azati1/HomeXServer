package com.company.Devices.State;

import com.company.Devices.SmartDevice;
import com.company.Net.TCPConnection;

public class ReadyState extends State {

    public ReadyState(SmartDevice sender, TCPConnection connection) {
        super(sender, connection);
    }

    @Override
    public void turnOn() {
        System.out.println(sender.getName() + "already turnOn");
    }

    @Override
    public void turnOff() {
        System.out.println(sender.getName() + "turnOff device from ReadyState");
    }

    @Override
    public void reset() {
        System.out.println(sender.getName() + "reset device from ReadyState");
    }

    @Override
    public void block() {
        System.out.println(sender.getName() + "block device from ReadyState");
    }

}