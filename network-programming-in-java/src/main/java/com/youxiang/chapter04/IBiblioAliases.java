package com.youxiang.chapter04;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class IBiblioAliases {
    public static void main(String[] args) {
        try {
            InetAddress ibiblio = InetAddress.getByName("www.jd.com");
            InetAddress helios = InetAddress.getByName("www.360buy.com");
            if (ibiblio.equals(helios)) {
                System.out.println("The same site");
            } else {
                System.out.println("Not the same site");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
