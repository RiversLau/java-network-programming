package com.youxiang.chapter12;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/8
 */
public class DatagramExample {
    public static void main(String[] args) {
        String s = "This is a test.";
        try {
            byte[] data = s.getBytes("UTF-8");
            InetAddress ia = InetAddress.getByName("www.ibiblio.org");
            int port = 7;
            DatagramPacket packet = new DatagramPacket(data, data.length, ia, port);
            System.out.println("This packet is addressed to " + packet.getAddress() + " on port " + packet.getPort());
            System.out.println("There are " + packet.getLength() + " bytes of data in the packet.");
            System.out.println(new String(packet.getData(), packet.getOffset(), packet.getLength(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
