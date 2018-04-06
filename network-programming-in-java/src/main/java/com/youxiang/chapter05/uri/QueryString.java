package com.youxiang.chapter05.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class QueryString {

    private static final String DEFAULT_CHARSET_NAME = "UTF-8";

    private StringBuilder query = new StringBuilder();

    public QueryString() {
    }

    public synchronized void add(String name, String value) {
        if (query.length() > 0) {
            query.append("&");
        }
        encode(name, value);
    }

    public synchronized void encode(String name, String value) {
        try {
            query.append(URLEncoder.encode(name, DEFAULT_CHARSET_NAME));
            query.append("=");
            query.append(URLEncoder.encode(value, DEFAULT_CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported charset UTF-8");
        }
    }

    public synchronized String getQuery() {
        return query.toString();
    }

    @Override
    public String toString() {
        return getQuery();
    }

    public static void main(String[] args) {
        QueryString queryString = new QueryString();
        queryString.add("h1", "en");
        queryString.add("as_q", "Java");
        queryString.add("as_epq", "I/O");
        String url = "http://www.google.com/search?" + queryString;
        System.out.println(url);
    }
}
