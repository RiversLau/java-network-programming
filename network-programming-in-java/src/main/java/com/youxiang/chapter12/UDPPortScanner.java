package com.youxiang.chapter12;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UDPPortScanner {
    public static void main(String[] args) {
        for (int port = 1024; port <= 65535; port++) {
            try {
                DatagramSocket server = new DatagramSocket(port);
                server.close();
            } catch (SocketException e) {
                System.out.println("There is a server on port " + port + ".");
            }
        }
    }
}
