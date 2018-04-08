package com.youxiang.chapter12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UDPDiscardClient {

    private static final int PORT = 1919;

    public static void main(String[] args) {
        String hostName = "localhost";
        try (DatagramSocket theSocket = new DatagramSocket()) {
            InetAddress server = InetAddress.getByName(hostName);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String theLine = userInput.readLine();
                if (".".equals(theLine)) {
                    break;
                }
                byte[] data = theLine.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, server, PORT);
                theSocket.send(packet);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
