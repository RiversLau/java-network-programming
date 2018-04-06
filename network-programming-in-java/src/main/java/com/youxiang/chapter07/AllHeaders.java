package com.youxiang.chapter07;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class AllHeaders {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection uc = url.openConnection();
            for (int j = 1; ;j++) {
                String header = uc.getHeaderField(j);
                if (header == null) break;
                System.out.println(uc.getHeaderFieldKey(j) + ":" + header);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
