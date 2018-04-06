package com.youxiang.chapter05.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class FetchDataFromUrl {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com/");
            try (InputStream is = url.openStream()) {
                int c;
                while ((c = is.read()) != -1) {
                    System.out.write(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
