package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class DaytimeUDPClient {

    private final static int PORT = 1313;
    private static final String HOST_NAME = "localhost";

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(300000);
            InetAddress host = InetAddress.getByName(HOST_NAME);
            DatagramPacket req = new DatagramPacket(new byte[1], 1, host, PORT);
            DatagramPacket resp = new DatagramPacket(new byte[1024], 1024);
            socket.send(req);
            socket.receive(resp);
            String result = new String(resp.getData(), 0, resp.getLength(), "US-ASCII");
            System.out.println(result);
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }
}
