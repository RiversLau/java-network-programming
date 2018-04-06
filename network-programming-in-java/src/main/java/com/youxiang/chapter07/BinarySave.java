package com.youxiang.chapter07;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class BinarySave {
    public static void main(String[] args) {
        String url = "https://github.com/RiversLau/java-network-programming/archive/master.zip";
        try {
            URL root = new URL(url);
            saveBinaryFile(root);
        } catch (MalformedURLException e) {
            System.out.println(url + " is not URL i understand.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveBinaryFile(URL url) throws IOException {
        URLConnection uc = url.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file");
        }

        try (InputStream raw = uc.getInputStream()) {
            InputStream in = new BufferedInputStream(raw);
            byte[] data = new byte[contentLength];
            int offset = 0;
            while (offset < contentLength) {
                int bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1)
                    break;
                offset += bytesRead;
            }
            if (offset != contentLength) {
                throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes.");
            }
            try (FileOutputStream fos = new FileOutputStream("/Users/Rivers/test")) {
                fos.write(data);
                fos.flush();
                fos.close();
            }
        }
    }
}
