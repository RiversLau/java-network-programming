package com.youxiang.chapter04;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class MyAddress {
    public static void main(String[] args) {
        try {
            InetAddress me = InetAddress.getLocalHost();
            System.out.println(me);
            String dottedQuad = me.getHostAddress();
            System.out.println(dottedQuad);
        } catch (UnknownHostException e) {
            System.out.println("Could not find this computer's address");
        }
    }
}
