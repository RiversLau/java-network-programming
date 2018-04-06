package com.youxiang.chapter07;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class SourceViewer3 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com/");
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            int code = uc.getResponseCode();
            String response = uc.getResponseMessage();
            System.out.println("HTTP/1.x " + code + " " + response);
            for (int j = 1;; j++) {
                String header = uc.getHeaderField(j);
                String key = uc.getHeaderFieldKey(j);
                if (header == null || key == null) {
                    break;
                }
                System.out.println(key + ":" + header);
            }
            System.out.println();

            try (InputStream in = new BufferedInputStream(uc.getInputStream())) {
                Reader reader = new InputStreamReader(in);
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
