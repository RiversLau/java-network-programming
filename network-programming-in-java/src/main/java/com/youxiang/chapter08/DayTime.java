package com.youxiang.chapter08;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Rivers
 * @date: 2018/4/6
 */
public class DayTime {

    public Date getDateFromNetwork() throws IOException, ParseException {
        try (Socket socket = new Socket("time.nist.gov", 13)) {
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in);
            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char) c);
            }
            return parseDate(time.toString());
        }
    }

    private static Date parseDate(String time) throws ParseException {
        String[] pieces = time.split(" ");
        String dateTime = pieces[1] + " " + pieces[2] + " " + " UTC";
        DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
        return format.parse(dateTime);
    }

    public static void main(String[] args) throws IOException, ParseException {
        DayTime dt = new DayTime();
        Date result = dt.getDateFromNetwork();
        System.out.println(result);
    }
}
