package com.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private static  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    public static boolean isExpired(Date maturityDate) {
        return maturityDate != null && maturityDate.before(new Date());
    }

    public static Date formatDate(String date) throws Exception{
        return formatter.parse(date);
    }

    public static String getDateAsString(Date date) {
        return formatter.format(date);
    }
}
