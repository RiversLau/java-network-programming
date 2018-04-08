package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class DaytimeUDPServer {

    private static final int PORT = 1313;

    private final static Logger audit  = Logger.getLogger("req");
    private final static Logger error = Logger.getLogger("error");

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            while (true) {
                try {
                    DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                    socket.receive(request);

                    String daytime = new Date().toString() + "\r\n";
                    byte[] data = daytime.getBytes("UTF-8");
                    DatagramPacket response = new DatagramPacket(data, data.length, request.getAddress(), request.getPort());
                    socket.send(response);
                    audit.info(daytime + " " + request.getAddress());
                } catch (IOException e) {
                    error.log(Level.SEVERE, e.getMessage(), e);
                }
            }
        } catch (IOException ex) {
            error.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
