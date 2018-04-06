package com.youxiang.chapter07;

import com.youxiang.chapter05.uri.QueryString;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class FormPoster {

    private URL url;
    private QueryString qs = new QueryString();

    public FormPoster(URL url) {
        if (!url.getProtocol().toLowerCase().startsWith("http")) {
            throw new IllegalArgumentException("Posting only works for http URLs");
        }
        this.url = url;
    }

    public void add(String name, String value) {
        qs.add(name, value);
    }

    public URL getUrl() {
        return this.url;
    }

    public InputStream post() throws IOException {
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        try (OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), "UTF-8")) {
            out.write(qs.toString());
            out.write("\r\n");
            out.flush();
            out.close();
        }
        return uc.getInputStream();
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
        FormPoster poster = new FormPoster(url);
        poster.add("name", "Elliotte Rusty Harold");
        poster.add("email", "elharo@ibiblio.org");

        try (InputStream in = poster.post()) {
            Reader r = new InputStreamReader(in);
            int c;
            while ((c = r.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
