package com.youxiang.chapter09;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class DaytimeClient {

    public String getDateFromNetwork() throws IOException {
        try (Socket socket = new Socket("localhost", 1313)) {
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in);
            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char) c);
            }
            return time.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        DaytimeClient dt = new DaytimeClient();
        System.out.println(dt.getDateFromNetwork());
    }
}
