package com.youxiang.chapter05.url;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class ProtocolTest {

    public static void main(String[] args) {
        testProtocol("http://www.adc.org");

        testProtocol("https://www.amazon.com/exec/obidos/order2/");

        testProtocol("ftp://ibiblio.org/pub/languages/java/javafaq/");

        testProtocol("mailto:zhaoxiang0805@hotmail.com");

        testProtocol("telnet://www.baidu.com/");

        testProtocol("file:///Users/Rivers/Develop");

        testProtocol("gopher://gopher.anc.org.za/");

        testProtocol("ldap://ldap.itd.umich.edu/o=University%20of%20Michigan,c=US?postalAddress");

        testProtocol("jar://http://cafeaulait.org/books/javaio/ioexamples/javaio.jar!/com/macfaq/io/StreamCopier.class");

        testProtocol("nfs://utopia.poly.edu/usr/tmp/");

        testProtocol("jdbc:mysql://luna.ibiblio.org:3306/NEWS");

        testProtocol("rmi://ibiblio.org/RenderEngine");

        testProtocol("doc:/UsersGuide/release.html");
        testProtocol("netdoc:/UsersGuide/release.html");
        testProtocol("systemresource://www.adc.org/+/index.html");
        testProtocol("verbatim:http://www.adc.org/");
    }

    private static void testProtocol(String url) {
        try {
            URL link = new URL(url);
            System.out.println(link.getProtocol() + " is supported!");
        } catch (MalformedURLException e) {
            String protocol = url.substring(0, url.indexOf(":"));
            System.out.println(protocol + " is not supported!");
        }
    }
}
