package com.youxiang.chapter12;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.channels.DatagramChannel;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class DefaultSocketOptionValues {

    public static void main(String[] args) {
        try (DatagramChannel channel = DatagramChannel.open()) {
            for (SocketOption<?> option : channel.supportedOptions()) {
                System.out.println(option.name() + ":" + channel.getOption(option));
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
