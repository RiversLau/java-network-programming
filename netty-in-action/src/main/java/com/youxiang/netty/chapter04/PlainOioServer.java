package com.youxiang.netty.chapter04;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author: Rivers
 * @date: 2018/4/10
 */
public class PlainOioServer {

    public void start(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        for (;;) {
            Socket client = socket.accept();
            System.out.println("Accepted connection from " + client);
            new Thread(() -> {
                OutputStream out;
                try {
                    out = client.getOutputStream();
                    out.write("Hi\r\n".getBytes(Charset.forName("UTF-8")));
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        client.close();
                    } catch (IOException e) {
                        //do nothing
                    }
                }
            }).start();
        }
    }
}
