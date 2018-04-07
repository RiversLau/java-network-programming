package com.youxiang.chapter09;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class SingleFileHTTPServer {

    private static final Logger logger = Logger.getLogger("SingleFileHttpServer");

    private final byte[] content;
    private final byte[] header;
    private final int port;
    private final String encoding;

    public SingleFileHTTPServer(String data, String encoding, String mimeType, int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding), encoding, mimeType, port);
    }

    public SingleFileHTTPServer(byte[] data, String encoding, String mimeType, int port) {
        this.content = data;
        this.encoding = encoding;
        this.port = port;
        String header = "HTTP/1.0 200 OK\r\n" + "Server: OneFile 2.0\r\n"
                + "Content-length: " + this.content.length + "\r\n"
                + "Content-type: " + mimeType + "; charset=" + encoding + "\r\n";
        this.header = header.getBytes(Charset.forName("UTF-8"));
    }

    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(50);
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Data to be send: ");
            logger.info(new String(this.content, encoding));

            while (true) {
                try {
                    Socket connection = server.accept();
                    pool.submit(new HttpHandler(connection));
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Exception accepting connection", e);
                }
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not start server", ex);
        }
    }

    private class HttpHandler implements Callable<Void> {

        private Socket connection;

        public HttpHandler(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() throws Exception {
            try {
                OutputStream out = new BufferedOutputStream(connection.getOutputStream());
                InputStream in = new BufferedInputStream(connection.getInputStream());
                StringBuilder request = new StringBuilder(80);
                while (true) {
                    int c = in.read();
                    if (c == '\r' || c == '\n' || c == -1) {
                        break;
                    }
                    request.append((char) c);
                }
                if (request.toString().indexOf("HTTP/") != -1) {
                    out.write(header);
                }
                out.write(content);
                out.flush();
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error writing to client", e);
            } finally {
                connection.close();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        String filePath = "/Users/Rivers/Downloads/config.ini";
        try {
            Path path = Paths.get(filePath);
            byte[] data = Files.readAllBytes(path);
            String contentType = URLConnection.getFileNameMap().getContentTypeFor(filePath);
            SingleFileHTTPServer server = new SingleFileHTTPServer(data, "UTF-8", contentType, 8080);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
