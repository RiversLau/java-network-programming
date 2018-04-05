package com.youxiang.chapter04;

import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class NetworkInterfaceTest {
    public static void main(String[] args) throws SocketException {
        NetworkInterface ni = NetworkInterface.getByName("en0");
        if (ni == null) {
            System.out.println("No such interface:eth0");
        }
    }
}
