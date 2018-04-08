package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UdpDiscardServerWithChannels {

    private static final int PORT = 1919;
    private static final int MAX_PACKET_SIZE = 65507;

    public static void main(String[] args) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();
            SocketAddress address = new InetSocketAddress(PORT);
            socket.bind(address);
            ByteBuffer buffer = ByteBuffer.allocate(MAX_PACKET_SIZE);
            while (true) {
                SocketAddress client = channel.receive(buffer);
                buffer.flip();
                System.out.print(client + " says ");
                while (buffer.hasRemaining()) {
                    System.out.write(buffer.get());
                }
                System.out.println();
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
