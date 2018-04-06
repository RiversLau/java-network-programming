package com.youxiang.chapter05.url;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class FetchDataFromUrl3 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com/");
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.write(c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
