package com.company.Devices.State;

import com.company.Devices.SmartDevice;
import com.company.Net.TCPConnection;

public class LockedState extends State {

    public LockedState(SmartDevice sender, TCPConnection connection) {
        super(sender, connection);
    }

    @Override
    public void turnOn() {
        System.out.println(sender.getName() + "turnOn device from LockedState");
    }

    @Override
    public void turnOff() {
        System.out.println(sender.getName() + "turnOff device from LockedState");
    }

    @Override
    public void reset() {
        System.out.println(sender.getName() + "reset device from LockedState");
    }

    @Override
    public void block() {
        System.out.println(sender.getName() + "block device from LockedState");
    }

}