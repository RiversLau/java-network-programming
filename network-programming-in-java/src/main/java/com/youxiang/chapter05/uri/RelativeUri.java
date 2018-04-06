package com.youxiang.chapter05.uri;

import com.sun.jndi.toolkit.url.Uri;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class RelativeUri {
    public static void main(String[] args) {
        testOne();
        testTwo();
        testThree();
    }

    private static void testOne() {
        try {
            URI absolute = new URI("http://www.example.com/");
            URI relative = new URI("images/logo.png");
            URI resolved = absolute.resolve(relative);
            System.out.println(resolved);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void testTwo() {
        try {
            URI top = new URI("javafaq/books/");
            URI resolved = top.resolve("jnp3/examples/07/index.html");
            System.out.println(resolved);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void testThree() {
        try {
            URI absolute = new URI("http://www.example.com/images/logo.png");
            URI top = new URI("http://www.example.com/");
            URI relatived = top.relativize(absolute);
            System.out.println(relatived);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
