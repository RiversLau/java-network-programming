package com.youxiang.chapter07;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class LastModified {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api-rehab.rrdkf.com/areas");
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("HEAD");
            Map<String, List<String>> headers = huc.getHeaderFields();
            for (Map.Entry entry : headers.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
            System.out.println(url + " was last modified at " + new Date(huc.getLastModified()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
