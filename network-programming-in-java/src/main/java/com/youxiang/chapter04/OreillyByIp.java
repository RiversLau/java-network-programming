package com.youxiang.chapter04;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class OreillyByIp {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("23.58.250.42");
            System.out.println(address.getHostName());
            System.out.println(address.getCanonicalHostName());
        } catch (UnknownHostException e) {
            System.out.println("Could not find 23.58.250.42");
        }
    }
}
