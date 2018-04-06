package com.youxiang.chapter05.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class URLTest3 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http", "fourier.dur.ac.uk", 8000, "/~dm3mjh/jsci/");
            System.out.println(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Shouldn't happen; all VMs recognize http");
        }
    }
}
