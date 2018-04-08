package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UdpEchoClientWithChannels {

    private static final int PORT = 1919;
    private static final int LIMIT = 100;

    public static void main(String[] args) {
        SocketAddress remote = new InetSocketAddress("localhost", PORT);
        try (DatagramChannel channel = DatagramChannel.open()) {
            channel.configureBlocking(false);
            channel.connect(remote);

            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            ByteBuffer buffer = ByteBuffer.allocate(4);
            int n = 0;
            int numbersRead = 0;
            while (true) {
                if (numbersRead == LIMIT) {
                    break;
                }
                selector.select(60000);
                Set<SelectionKey> keySet = selector.selectedKeys();
                if (keySet.isEmpty() && n == LIMIT) {
                    break;
                } else {
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isReadable()) {
                            buffer.clear();
                            channel.read(buffer);
                            buffer.flip();
                            int echo = buffer.getInt();
                            System.out.println("Read: " + echo);
                            numbersRead++;
                        }
                        if (key.isWritable()) {
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            channel.write(buffer);
                            System.out.println("Wrote: " + n);
                            n++;
                            if (n == LIMIT) {
                                key.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            }
            System.out.println("Echoed " + numbersRead + " out of " + LIMIT + " sent");
            System.out.println("Success rate: " + 100.0 * numbersRead / LIMIT + "%");
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
