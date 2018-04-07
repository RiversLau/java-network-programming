package com.youxiang.chapter11;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class ChargenClient {

    private static int DEFAULT_PORT = 19;

    public static void main(String[] args) {
        try {
            SocketAddress address = new InetSocketAddress("rama.poly.edu", DEFAULT_PORT);
            SocketChannel client = SocketChannel.open(address);
            client.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            WritableByteChannel out = Channels.newChannel(System.out);
            while (true) {
                int n = client.read(buffer);
                if (n > 0) {
                    buffer.flip();
                    out.write(buffer);
                    buffer.clear();
                } else if (n == -1) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
