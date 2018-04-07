package com.youxiang.chapter09;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class RandomTest {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(0);
            System.out.println(ss);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
