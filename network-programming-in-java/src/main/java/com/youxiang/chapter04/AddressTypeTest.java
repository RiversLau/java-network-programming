package com.youxiang.chapter04;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/5
 */
public class AddressTypeTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("www.baidu.com");
        System.out.println(getVersion(address));
    }

    public static int getVersion(InetAddress ia) {
        byte[] address = ia.getAddress();
        if (address.length == 4) return 4;
        if (address.length == 16) return 16;
        return -1;
    }
}
