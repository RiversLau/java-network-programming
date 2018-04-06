package com.youxiang.chapter05.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class EncoderTest {

    private static final String DEFAULT_CHARSET_NAME = "UTF-8";

    public static void main(String[] args) {
        try {
            System.out.println(URLEncoder.encode("This string has spaces", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This*string*has*asterisks", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This%string%has%percent%signs", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This+string+has+plus", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This/string/has/slashes", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This\"string\"has\"quote\"marks", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This:string:has:colons", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This~string~has~tildes", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This(string)has(parentheses)", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This.string.has.periods", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This=string=has=equal=signs", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("This&string&has&ampersands", DEFAULT_CHARSET_NAME));
            System.out.println(URLEncoder.encode("Thiséstringéhasénon-ASCII characters", DEFAULT_CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
