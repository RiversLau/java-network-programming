package com.youxiang.chapter09;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class LoggingDaytimeServer {

    private static final Logger auditLogger = Logger.getLogger("Request");
    private static final Logger errorLogger = Logger.getLogger("Error");

    private static final int PORT = 1313;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(50);
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while (true) {
                try {
                    Socket connection = ss.accept();
                    DaytimeTask task = new DaytimeTask(connection);
                    pool.submit(task);
                } catch (IOException ex) {
                    errorLogger.log(Level.SEVERE, "accept error", ex);
                } catch (RuntimeException re) {
                    errorLogger.log(Level.SEVERE, "unexpected error " + re.getMessage(), re);
                }
            }
        } catch (IOException ex) {
            errorLogger.log(Level.SEVERE, "couldn't start server", ex);
        } catch (RuntimeException re) {
            errorLogger.log(Level.SEVERE, "Couldn't start server:" + re.getMessage(), re);
        }
    }

    private static class DaytimeTask implements Callable<Void> {

        private Socket connection;

        DaytimeTask(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() throws Exception {
            try {
                Writer out = new OutputStreamWriter(connection.getOutputStream());
                Date now = new Date();
                auditLogger.info(now + " " + connection.getRemoteSocketAddress());
                out.write(now.toString() + "\r\n");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            } finally {
                try {
                    connection.close();
                } catch (IOException e) {
                    //do nothing
                }
            }
            return null;
        }
    }
}
