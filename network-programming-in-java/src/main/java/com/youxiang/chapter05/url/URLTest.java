package com.youxiang.chapter05.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class URLTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com/");
            System.out.println(url);
        } catch (MalformedURLException e) {
            System.out.println("Malformed Url");
        }
    }
}
