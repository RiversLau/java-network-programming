package com.youxiang.chapter04;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class OreillyByAllName {
    public static void main(String[] args) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName("www.oreilly.com");
            for (InetAddress adr : addresses) {
                System.out.println(adr);
            }
        } catch (UnknownHostException e) {
            System.out.println("Could not find www.oreilly.com");
        }
    }
}
