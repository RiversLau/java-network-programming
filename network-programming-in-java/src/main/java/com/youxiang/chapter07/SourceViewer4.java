package com.youxiang.chapter07;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class SourceViewer4 {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.ibiblio.org/test.html");
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            try (InputStream raw = uc.getInputStream()) {
                printFromStream(raw);
            } catch (IOException ex) {
                printFromStream(uc.getErrorStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printFromStream(InputStream in) {
        try (InputStream buffer = new BufferedInputStream(in)) {
            Reader reader = new InputStreamReader(buffer);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
