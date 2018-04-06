package com.youxiang.chapter05.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class URLTest4 {
    public static void main(String[] args) {
        try {
            URL base = new URL("http://www.ibiblio.org/javafaq/index.html");
            URL u2 = new URL(base, "mailinglists.html");
            System.out.println(u2);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
