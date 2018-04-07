package com.youxiang.chapter09;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class Redirector {

    private static final Logger logger = Logger.getLogger("Redictor");

    private final int port;
    private final String newSite;

    public Redirector(String newSite, int port) {
        this.port = port;
        this.newSite = newSite;
    }

    public void start() {
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Redirecting connections on port " + server.getLocalPort() + " to " + newSite);
            while (true) {
                Socket s = server.accept();
                Thread thread = new RedirectThread(s);
                thread.start();
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error opening server socket", ex);
        }
    }

    private class RedirectThread extends Thread {

        private final Socket connection;
        public RedirectThread(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                Writer out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                Reader in  = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder request = new StringBuilder(80);
                while (true) {
                    int c = in.read();
                    if (c == '\r' || c == '\n' || c == -1) {
                        break;
                    }
                    request.append((char) c);
                }

                String get = request.toString();
                String[] pieces = get.split("\\w*");
                String theFile = pieces[1];

                if (get.indexOf("HTTP") != -1) {
                    out.write("HTTP/1.0 302 FOUND\r\n");
                    Date now = new Date();
                    out.write("Date: " + now + "\r\n");
                    out.write("Server: Redirector 1.1\r\n");
                    out.write("Location: " + newSite + theFile + "\r\n");
                    out.write("Content-type: text/html\r\n\r\n");
                    out.flush();
                }

                out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
                out.write("<BODY><H1>" + theFile + " has moved to\r\n<A href=\"" + newSite + theFile + "\">" +
                        newSite + theFile + "</A>.\r\n Please update your bookmarks<P>");
                out.write("</BODY></HTML>\r\n");
                out.flush();
                logger.log(Level.INFO, "Redirected " + connection.getRemoteSocketAddress());
            } catch (IOException e) {
                logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), e);
            } finally {
                try {
                    connection.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    public static void main(String[] args) {
        Redirector redirector = new Redirector("http://www.baidu.com", 8080);
        redirector.start();
    }
}
