package com.youxiang.chapter04;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class OreillyByName {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("www.oreilly.com");
        System.out.println(address);

        InetAddress address2 = InetAddress.getByName("208.201.239.100");
        System.out.println(address2);
    }
}
