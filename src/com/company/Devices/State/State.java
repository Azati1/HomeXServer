package com.company.Devices.State;

import com.company.Devices.Controllable;
import com.company.Devices.SmartDevice;
import com.company.Net.TCPConnection;

public abstract class State implements Controllable {

    protected SmartDevice sender;
    protected TCPConnection connection;
    public static final int ENABLED = 1;
    public static final int DISABLED = 2;
    public static final int RESETED = 3;
    public static final int BLOCKED = 4;
    public static final int LOADED = 5;
    protected int currentState;

    @Override
    public void turnOn() {
        currentState = ENABLED;
    }

    @Override
    public void turnOff() {
        currentState = DISABLED;
    }

    @Override
    public void reset() {
        currentState = RESETED;
    }

    @Override
    public void block() {
        currentState = BLOCKED;
    }

    public State(SmartDevice sender, TCPConnection connection) {
        this.sender = sender;
        this.connection = connection;
    }

    public int getState() {
        return currentState;
    }
}