package com.youxiang.chapter05.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class FetchDataFromUrl4 {
    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("http://www.baidu.com/");
            Object obj = url.getContent();
            System.out.println("I got a " + obj.getClass().getName());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
