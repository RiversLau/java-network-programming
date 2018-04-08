package com.youxiang.chapter12;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UDPPoke {

    private int bufferSize;
    private int timeout;
    private InetAddress host;
    private int port;

    public UDPPoke(InetAddress host, int port, int bufferSize, int timeout) {
        this.bufferSize = bufferSize;
        this.host = host;
        if (port < 1 || port > 65535) {
            throw new IllegalArgumentException("Port out of range.");
        }
        this.port = port;
        this.timeout = timeout;
    }

    public UDPPoke(InetAddress host, int port, int bufferSize) {
        this(host, port, bufferSize, 30000);
    }

    public UDPPoke(InetAddress host, int port) {
        this(host, port, 8192, 30000);
    }

    public byte[] poke() {
        try (DatagramSocket socket = new DatagramSocket(0)) {
            DatagramPacket packet = new DatagramPacket(new byte[1], 1, host, port);
            socket.connect(host, port);
            socket.setSoTimeout(timeout);
            socket.send(packet);
            DatagramPacket incoming = new DatagramPacket(new byte[bufferSize], bufferSize);
            socket.receive(incoming);
            int numBytes = incoming.getLength();
            byte[] response = new byte[numBytes];
            System.arraycopy(incoming.getData(), 0, response, 0, numBytes);
            return response;
        } catch (IOException ex) {
            return null;
        }
    }

    public static void main(String[] args) throws UnknownHostException, UnsupportedEncodingException {
        InetAddress host = InetAddress.getByName("rama.poly.edu");
        int port = 13;

        UDPPoke poke = new UDPPoke(host, port);
        byte[] response = poke.poke();
        if (response == null) {
            System.out.println("No response within allotted time");
            return;
        }

        String result = new String(response, "UTF-8");
        System.out.println(result);
    }
}
