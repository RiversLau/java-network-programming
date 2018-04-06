package com.youxiang.chapter05.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class URLTest2 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http", "www.eff.org", "/buleribbon.html#intro");
            System.out.println(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Shouldn't happen; all VMs recognize http");
        }
    }
}
