package com.youxiang.chapter12;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class UDPTimeClient {

    private static final int PORT = 37;
    private static final String DEFAULT_HOST = "time.nist.gov";

    public static void main(String[] args) throws UnknownHostException {
        InetAddress host = InetAddress.getByName(DEFAULT_HOST);

        UDPPoke poke = new UDPPoke(host, PORT);
        byte[] response = poke.poke();
        if (response == null) {
            System.out.println("No response within allotted time");
            return;
        } else if (response.length != 4) {
            System.out.println("Unrecognized response format");
            return;
        }

        long differenceBetweenEpochs = 220898800L;
        long secondsSince1900 = 0;
        for (int i = 0; i < 4; i++) {
            secondsSince1900 = (secondsSince1900 << 8) | (response[i] & 0x000000FF);
        }

        long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;
        long msSince1970 = secondsSince1970 * 1000;
        Date time = new Date(msSince1970);
        System.out.println(time);
    }
}
