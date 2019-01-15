package com.company.Net;

import com.company.Devices.Device;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPConnection {

    private final Socket socket;
    private final Thread thread;
    private final BufferedReader in;
    private final BufferedWriter out;
    private final TCPConnectionListener eventListener;
    private final Device sender;

    public TCPConnection(TCPConnectionListener eventListener, String ip, int port, Device device) throws IOException {
        this.eventListener = eventListener;
        this.socket = new Socket(ip, port);
        this.sender = device;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(TCPConnection.this);
                    while (!thread.isInterrupted()) {
                        String msg = in.readLine();
                        eventListener.onReceiveString(TCPConnection.this, msg);
                    }
                } catch (IOException e) {
                    eventListener.onException(TCPConnection.this, e);
                } finally {
                    eventListener.onDisconnect(TCPConnection.this);
                }
            }
        });
        thread.start();
    }

    public TCPConnection(TCPConnectionListener eventListener, Socket socket, Device device) throws IOException {
        this.eventListener = eventListener;
        this.socket = socket;
        this.sender = device;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(TCPConnection.this);
                    while (!thread.isInterrupted()) {
                        char[] buffer = new char[4096];
                        String msg = null;
                        /*int size;
                        while (true) {
                            size = in.read(buffer);
                            msg = new String(buffer, 0, size);
                            if (size == -1)
                                break;
                        }*/
                        int size = in.read(buffer);
                        msg = new String(buffer, 0, size);
                        eventListener.onReceiveString(TCPConnection.this, msg);
                        /*String msg = in.readLine();
                        eventListener.onReceiveString(TCPConnection.this, msg);*/
                    }
                } catch (IOException e) {
                    eventListener.onException(TCPConnection.this, e);
                } finally {
                    eventListener.onDisconnect(TCPConnection.this);
                }
            }
        });
        thread.start();
    }

    public synchronized void sendString(String value) {

        try {
            out.write(value);
            out.flush();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this, e);
            disconnect();
        }
    }

    public synchronized void disconnect() {
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onException(TCPConnection.this, e);
        }
    }

    public Device getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "TCPConnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }
}
