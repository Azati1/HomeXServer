package com.company.Devices.State;

import com.company.Devices.SmartDevice;
import com.company.Net.TCPConnection;

public class SwitchOffState extends State {

    public SwitchOffState(SmartDevice sender, TCPConnection connection) {
        super(sender, connection);
    }

    @Override
    public void turnOn() {
        System.out.println(sender.getName() + "turnOn device from SwitchOffState");
    }

    @Override
    public void turnOff() {
        System.out.println(sender.getName() + "already turnOff");
    }

    @Override
    public void reset() {
        System.out.println(sender.getName() + "reset device from SwitchOffState");
    }

    @Override
    public void block() {
        System.out.println(sender.getName() + "block device from SwitchOffState");
    }

}