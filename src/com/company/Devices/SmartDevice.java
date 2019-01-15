package com.company.Devices;

import com.company.DeviceType;
import com.company.Devices.State.*;
import com.company.PlaceGroup;
import com.company.Net.TCPConnection;

public abstract class SmartDevice implements Controllable {

    private String name;
    private int id;
    private transient State state;
    private transient TCPConnection connection;
    private static int counter;
    private PlaceGroup place;
    private DeviceType deviceType;
    private transient int currentState;

    public SmartDevice(DeviceType deviceType, String name, PlaceGroup placeGroup, final TCPConnection connection) {
        this.id = counter++;
        this.name = name;
        this.place = placeGroup;
        this.deviceType = deviceType;
        this.connection = connection;
        state = new LoadingState(this, connection);
        load();
        placeGroup.addSmartDevice(this);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    private void changeState(State state) {
        this.state = state;
    }

    @Override
    public void turnOn() {
        state.turnOn();
        changeState(new ReadyState(this, connection));
    }

    @Override
    public void turnOff() {
        state.turnOff();
        changeState(new SwitchOffState(this, connection));
    }

    @Override
    public void reset() {
        state.reset();
    }

    @Override
    public void block() {
        state.block();
        changeState(new LockedState(this, connection));
    }

    private void load() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO(Принять сообщение о готовности устройства к работе)
                changeState(new ReadyState(SmartDevice.this, connection));
            }
        });
        thread.start();
    }

    public int getState() {
        return currentState;
    }

    public PlaceGroup placedIn() {
        return place;
    }

    public boolean isEnabled() {
        if (state.getState() == State.ENABLED)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "SmartDevice{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", state=" + state +
                ", connection=" + connection +
                ", place=" + place +
                ", deviceType=" + deviceType +
                ", currentState=" + currentState +
                '}';
    }

    public static SmartDevice parse(String string) {
        //TODO(Распарсить утсройство и создать новый объект
        return null;
    }
}