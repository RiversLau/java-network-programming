package com.youxiang.chapter05.url;

import java.io.*;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class FetchDataFromUrl2 {
    public static void main(String[] args) {
        InputStream is = null;
        try {
            URL url = new URL("https://www.oreilly.com");
            is = url.openStream();
            is = new BufferedInputStream(is);
            Reader r = new InputStreamReader(is);
            int c;
            while ((c = r.read()) != -1) {
                System.out.write((char) c);
            }
        } catch (IOException e) {
            System.out.println("'https://www.oreilly.com' is not a parseable URL.");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
