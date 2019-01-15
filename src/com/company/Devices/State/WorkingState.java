package com.company.Devices.State;

import com.company.Devices.SmartDevice;
import com.company.Net.TCPConnection;

public class WorkingState extends State {

    public WorkingState(SmartDevice sender, TCPConnection connection) {
        super(sender, connection);
    }

    @Override
    public void turnOn() {
    }

    @Override
    public void turnOff() {
    }

    @Override
    public void reset() {
    }

    @Override
    public void block() {
    }

}