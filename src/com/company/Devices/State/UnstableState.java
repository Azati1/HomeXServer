package com.company.Devices.State;

import com.company.Devices.SmartDevice;
import com.company.Net.TCPConnection;

public class UnstableState extends State {
    public UnstableState(SmartDevice sender, TCPConnection connection) {
        super(sender, connection);
    }

    @Override
    public void turnOn() {
        //super.turnOn();
        //connection.sendString(device.getName() + "turnOn device from LockedState");
    }

    @Override
    public void turnOff() {
        //Log.d("CDA", device.getName() + "turnOff device from LockedState");
        //connection.sendString(device.getName() + "turnOff device from LockedState");
    }

    @Override
    public void reset() {
        //Log.d("CDA", device.getName() + "reset device from LockedState");
        //connection.sendString(device.getName() + "reset device from LockedState");
    }

    @Override
    public void block() {
        //Log.d("CDA", device.getName() + "block device from LockedState");
        //connection.sendString(device.getName() + "block device from LockedState");
    }
}
