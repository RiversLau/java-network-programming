package com.youxiang.chapter12;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class ReceiverThread extends Thread {

    private DatagramSocket socket;
    private volatile boolean stopped = false;

    public ReceiverThread(DatagramSocket socket) {
        this.socket = socket;
    }

    public void halt() {
        this.stopped = true;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[65507];
        while (true) {
            if (stopped) {
                return;
            }
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(dp);
                String s = new String(dp.getData(), dp.getOffset(), dp.getLength(), "UTF-8");
                System.out.println(s);
                Thread.yield();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
