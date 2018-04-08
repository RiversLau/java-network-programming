package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public abstract class UdpServer implements Runnable {

    private final int bufferSize;
    private final int port;
    private final static Logger logger = Logger.getLogger(UdpServer.class.getCanonicalName());

    private volatile boolean isShutDown = false;

    public UdpServer(int port, int bufferSize) {
        this.port = port;
        this.bufferSize = bufferSize;
    }

    public UdpServer(int port) {
        this(port, 8192);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[bufferSize];
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(10000);
            while (true) {
                if (isShutDown) {
                    return;
                }
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                try {
                    socket.receive(incoming);
                    this.respond(socket, incoming);
                } catch (SocketTimeoutException ex) {
                    if (isShutDown) {
                        return;
                    }
                } catch (IOException ex) {
                    logger.log(Level.WARNING, ex.getMessage(), ex);
                }
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not bind to port:" + port, ex);
        }
    }

    public abstract void respond(DatagramSocket socket, DatagramPacket packet) throws IOException;

    public void shutDown() {
        this.isShutDown = true;
    }
}
