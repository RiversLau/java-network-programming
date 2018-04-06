package com.youxiang.chapter05.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class DecoderTest {

    public static void main(String[] args) {
        try {
            String output = URLDecoder.decode("http://www.google.com/search?h1=en&as_q=Java&as_epq=I%2FO", "UTF-8");
            System.out.println(output);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
