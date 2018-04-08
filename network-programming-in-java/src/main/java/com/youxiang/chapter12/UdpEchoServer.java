package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UdpEchoServer extends UdpServer {

    public static final int DEFAULT_PORT = 7777;

    public UdpEchoServer() {
        super(DEFAULT_PORT);
    }

    @Override
    public void respond(DatagramSocket socket, DatagramPacket packet) throws IOException {
        DatagramPacket outgoing = new DatagramPacket(packet.getData(), packet.getLength(),
                packet.getAddress(), packet.getPort());
        socket.send(outgoing);
    }

    public static void main(String[] args) {
        UdpServer server = new UdpEchoServer();
        Thread t = new Thread(server);
        t.start();
    }
}
