package com.company.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeFormatter {
    public static String convertToTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(new Date(time));
    }
}
