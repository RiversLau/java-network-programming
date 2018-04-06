package com.youxiang.chapter05.uri;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class UriSplitter {
    public static void main(String[] args) {
//        String arg = "tel:+1-800-9988-9938";
        String arg = "http://www.xml.com/pub/a/2003/09/17/stax.html#id=_hbc";
//        String arg = "tel:+1-800-9988-9938";
        try {
            URI uri = new URI(arg);
            System.out.println("The URI is " + uri);
            if (uri.isOpaque()) {
                System.out.println("This is an opaque uri.");
                System.out.println("The scheme is " + uri.getScheme());
                System.out.println("The scheme specific part is " + uri.getSchemeSpecificPart());
                System.out.println("The fragment is is " + uri.getFragment());
            } else {
                System.out.println("This is a hierarchical uri.");
                System.out.println("The scheme is " + uri.getScheme());
                try {
                    uri = uri.parseServerAuthority();
                    System.out.println("The host is " + uri.getHost());
                    System.out.println("The user info is " + uri.getUserInfo());
                    System.out.println("The port is " + uri.getPort());
                } catch (URISyntaxException e) {
                    System.out.println("The authority is " + uri.getAuthority());
                }
                System.out.println("The path is " + uri.getPath());
                System.out.println("The query string is " + uri.getQuery());
                System.out.println("The fragment id is " + uri.getFragment());
            }
        } catch (URISyntaxException e) {
            System.out.println(arg + " does not seem to be a URI.");
        }
    }
}
