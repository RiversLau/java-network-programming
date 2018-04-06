package com.youxiang.chapter05.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class URlSplitter {
    public static void main(String[] args) {
        String url = "http://www.baidu.com/";
        try {
            URL u = new URL(url);
            System.out.println("The url is " + url);
            System.out.println("The scheme is " + u.getProtocol());
            System.out.println("The user info is " + u.getUserInfo());

            String host = u.getHost();
            if (host != null) {
                int atSign = host.indexOf('@');
                if (atSign != -1) {
                    host = host.substring(atSign + 1);
                }
                System.out.println("The host is " + host);
            } else {
                System.out.println("The host is null.");
            }

            System.out.println("The port is " + u.getPort());
            System.out.println("The default port of protocol " + u.getProtocol() + " is " + u.getDefaultPort());
            System.out.println("The path is " + u.getPath());
            System.out.println("The ref is " + u.getRef());
            System.out.println("The query string is " + u.getQuery());
        } catch (MalformedURLException e) {
            System.out.println(url + " is not a URL i understand.");
        }
    }
}
