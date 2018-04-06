package com.youxiang.chapter07;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class EncodingAwareSourceViewer {

    public static void main(String[] args) {

        String defaultEncoding = "UTF-8";
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection uc = url.openConnection();
            String contentType = uc.getContentType();
            int encodingIndex = contentType.indexOf("charset=");
            if (encodingIndex > 0) {
                defaultEncoding = contentType.substring(encodingIndex + 8);
            }
            System.out.println("www.baidu.com charset name is " + defaultEncoding);
            System.out.println("www.baidu.com content encoding is " + uc.getContentEncoding());
            System.out.println("www.baidu.com response time is " + new Date(uc.getDate()));
            System.out.println("www.baidu.com response content " + (uc.getExpiration() == 0 ? " never expire" : "will expire after " + new Date(uc.getExpiration())));
            System.out.println("www.baidu.com last modified time is " + new Date(uc.getLastModified()));
            InputStream buffer = new BufferedInputStream(uc.getInputStream());
            Reader reader = new InputStreamReader(buffer, defaultEncoding);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
