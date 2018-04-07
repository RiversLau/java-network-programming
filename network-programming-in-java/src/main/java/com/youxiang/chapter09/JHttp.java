package com.youxiang.chapter09;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class JHttp {

    private static final Logger logger = Logger.getLogger(JHttp.class.getCanonicalName());

    private static final int NUM_THREADS = 50;
    private static final String INDEX_FILE = "index.html";

    private final File rootDirectory;
    private final int port;

    public JHttp(File rootDirectory, int port) throws IOException {
        if (!rootDirectory.isDirectory()) {
            throw new IOException(rootDirectory + " does not exist as a directory.");
        }
        this.rootDirectory = rootDirectory;
        this.port = port;
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Document root: " + rootDirectory);

            while (true) {
                Socket request = server.accept();
                Runnable r = new RequestProcessor(rootDirectory, INDEX_FILE, request);
                pool.submit(r);
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error excepting connection", ex);
        }
    }

    public static void main(String[] args) {

        File rootDirectory = new File("/Users/Rivers/Rivers/test");
        try {
            JHttp server = new JHttp(rootDirectory, 8080);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
