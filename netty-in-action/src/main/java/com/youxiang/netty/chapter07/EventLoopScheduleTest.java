package com.youxiang.netty.chapter07;

import io.netty.channel.ServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * @author: Rivers
 * @date: 2018/4/14
 */
public class EventLoopScheduleTest {

    public static void main(String[] args) throws InterruptedException {
        ServerChannel ch = new NioServerSocketChannel();
        ch.eventLoop().schedule(() -> System.out.println("60 seconds later"), 60, TimeUnit.SECONDS);

        Thread.sleep(100);
        ch.close();
    }
}
