package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UDPDiscardServer {

    private static final int PORT = 1919;
    private static final int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        byte[] buffer = new byte[MAX_PACKET_SIZE];

        try (DatagramSocket server = new DatagramSocket(PORT)) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                server.receive(packet);
                String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), "UTF-8");
                System.out.println(packet.getAddress() + " at port " + packet.getPort() + " says " + s);
//                packet.setLength(buffer.length);
            }
        } catch (IOException ex) {

        }
    }
}
