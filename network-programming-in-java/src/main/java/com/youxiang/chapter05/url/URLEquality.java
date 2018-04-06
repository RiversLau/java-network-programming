package com.youxiang.chapter05.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class URLEquality {
    public static void main(String[] args) {
        try {
            URL www = new URL("http://www.ibiblio.org/");
            URL ibiblio = new URL("http://ibiblio.org/");
            if (ibiblio.equals(www)) {
                System.out.println("Same URL");
            } else {
                System.out.println("Not same URL");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
