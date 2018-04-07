package com.youxiang.chapter08;

import java.net.UnknownHostException;

/**
 * @author: Rivers
 * @date: 2018/4/7
 */
public class WhoisTest {
    public static void main(String[] args) throws UnknownHostException {
        Whois whois = new Whois();
        String result = whois.lookUpNames("oreilly.com", Whois.SearchFor.ANY, Whois.SearchIn.MAILBOX, false);
        System.out.println(result);
    }
}
