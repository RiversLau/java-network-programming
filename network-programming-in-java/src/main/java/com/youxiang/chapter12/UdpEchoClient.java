package com.youxiang.chapter12;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UdpEchoClient {

    private static final int PORT = 7777;

    public static void main(String[] args) {
        String hostName = "localhost";

        try {
            InetAddress address = InetAddress.getByName(hostName);
            DatagramSocket socket = new DatagramSocket();
            SenderThread sender = new SenderThread(socket, address, PORT);
            sender.start();
            Thread receiver = new ReceiverThread(socket);
            receiver.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
