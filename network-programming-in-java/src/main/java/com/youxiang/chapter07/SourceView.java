package com.youxiang.chapter07;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class SourceView {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection uc = url.openConnection();
            try (InputStream raw = uc.getInputStream()) {
                InputStream buffer = new BufferedInputStream(raw);
                Reader reader = new InputStreamReader(buffer);
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
