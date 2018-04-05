package com.youxiang.chapter04;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class NetworkInterfaceTest4 {
    public static void main(String[] args) throws SocketException {
        NetworkInterface en0 = NetworkInterface.getByName("en0");
        Enumeration<InetAddress> addresses = en0.getInetAddresses();
        while (addresses.hasMoreElements()) {
            InetAddress ia = addresses.nextElement();
            System.out.println(ia);
        }
    }
}
