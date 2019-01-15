package com.company.Devices.State;

import com.company.Devices.SmartDevice;
import com.company.Net.TCPConnection;

public class LoadingState extends State {

    public LoadingState(SmartDevice sender, TCPConnection connection) {
        super(sender, connection);
        currentState = LOADED;
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