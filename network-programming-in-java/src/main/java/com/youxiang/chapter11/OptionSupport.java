package com.youxiang.chapter11;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.*;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class OptionSupport {
    public static void main(String[] args) throws IOException {
        printOptions(SocketChannel.open());
        printOptions(ServerSocketChannel.open());
        printOptions(AsynchronousServerSocketChannel.open());
        printOptions(AsynchronousSocketChannel.open());
        printOptions(DatagramChannel.open());
    }

    private static void printOptions(NetworkChannel channel) throws IOException {
        System.out.println(channel.getClass().getSimpleName() + " supports：");
        for (SocketOption<?> option : channel.supportedOptions()) {
            System.out.println(option.name());
        }
        System.out.println();
        channel.close();
    }
}
