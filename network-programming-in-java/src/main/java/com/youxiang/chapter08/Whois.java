package com.youxiang.chapter08;

import java.io.*;
import java.net.*;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class Whois {

    public final static int DEFAULT_PORT = 43;
    public final static String DEFAULT_HOST = "whois.internic.net";

    private int port = DEFAULT_PORT;
    private InetAddress host;

    public Whois(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public Whois(InetAddress host) {
        this(host, DEFAULT_PORT);
    }

    public Whois(String hostName, int port) throws UnknownHostException {
        this(InetAddress.getByName(hostName), port);
    }

    public Whois(String hostName) throws UnknownHostException {
        this(InetAddress.getByName(hostName), DEFAULT_PORT);
    }

    public Whois() throws UnknownHostException {
        this(DEFAULT_HOST, DEFAULT_PORT);
    }

    public enum SearchFor {

        ANY("Any"),
        NETWORK("Network"),
        PERSON("Person"),
        HOST("Host"),
        DOMAIN("Domain"),
        ORGANIZATION("Organization"),
        GROUP("Group"),
        GATEWAY("Gateway"),
        ASN("Asn");

        private String label;

        private SearchFor(String label) {
            this.label = label;
        }
    }

    public enum SearchIn {
        ALL(""),
        NAME("Name"),
        MAILBOX("Mailbox"),
        HANDLE("!");

        private String label;
        private SearchIn(String label) {
            this.label = label;
        }
    }

    public String lookUpNames(String target, SearchFor category, SearchIn group, boolean exactMatch) {

        String suffix = "";
        if (!exactMatch) {
            suffix = ".";
        }

        String prefix = category.label + " " + group.label;
        String query = prefix + target + suffix;

        Socket socket = new Socket();
        try {
            SocketAddress address = new InetSocketAddress(host, port);
            socket.connect(address);
            Writer out = new OutputStreamWriter(socket.getOutputStream(), "ASCII");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "ASCII"));
            out.write(query + "\r\n");
            out.flush();

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
                response.append("\r\n");
            }
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
