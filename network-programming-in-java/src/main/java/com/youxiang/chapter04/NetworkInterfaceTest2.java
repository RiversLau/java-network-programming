package com.youxiang.chapter04;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class NetworkInterfaceTest2 {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        InetAddress local = InetAddress.getByName("127.0.0.1");
        NetworkInterface ni = NetworkInterface.getByInetAddress(local);
        if (ni == null) {
            System.out.println("That's weird. No local loopback address.");
        }
    }
}
