package com.youxiang.chapter05.get;

import com.youxiang.chapter05.uri.QueryString;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class DMoz {
    public static void main(String[] args) {
        QueryString qs = new QueryString();
        qs.add("q", "java");
        try {
            URL url = new URL("http://www.dmoz.org/search?" + qs);
            System.out.println(url);
            try (InputStream in = new BufferedInputStream(url.openStream())) {
                InputStreamReader reader = new InputStreamReader(in);
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.write((char) c);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }
}
