package com.youxiang.chapter08;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class LocalPortScanner {
    public static void main(String[] args) {
        for (int i = 1; i < 1024; i++) {
            try {
                Socket socket = new Socket("localhost", i);
                System.out.println("There is a server on port " + i);
                socket.close();
            } catch (UnknownHostException ex) {
                System.out.println("Unknown host");
            } catch (IOException e) {
//                System.out.println("Open socket failed");
            }
        }
    }
}
