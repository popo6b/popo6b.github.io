package com.itws.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {
    public static String DateConvertString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        return format;
    }
}
