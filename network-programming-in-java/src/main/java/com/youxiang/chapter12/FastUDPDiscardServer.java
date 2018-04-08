package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class FastUDPDiscardServer extends UdpServer {

    public static final int DEFAULT_PORT = 1919;

    public FastUDPDiscardServer() {
        super(DEFAULT_PORT);
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket packet) throws IOException {

    }

    public static void main(String[] args) {
        UdpServer server = new FastUDPDiscardServer();
        Thread t = new Thread(server);
        t.start();
    }
}
