package com.youxiang.chapter08;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class SocketInfo {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("baidu.com", 80);
            SocketAddress sa = socket.getRemoteSocketAddress();
            System.out.println("Remote address is " + sa);
            System.out.println("Connected to " + socket.getInetAddress() + " on port " +
                            socket.getPort() + " from port " + socket.getLocalPort() + " of " +
                            socket.getLocalAddress());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
