package com.youxiang.chapter09;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class MultiThreadDaytimeServer {

    private static final int PORT = 1313;

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket connection = ss.accept();
                    Thread thread = new DaytimeThread(connection);
                    thread.start();
                } catch (IOException ex) {
                    System.out.println("Accept failed");
                }
            }
        } catch (IOException ex) {
            System.out.println("Initialize ServerSocket failed");
        }
    }

    private static class DaytimeThread extends Thread {
        private Socket connection;

        DaytimeThread(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                Writer out = new OutputStreamWriter(connection.getOutputStream());
                Date now = new Date();
                out.write(now.toString() + "\r\n");
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (IOException e) {
                    //do nothing
                }
            }
        }
    }
}
