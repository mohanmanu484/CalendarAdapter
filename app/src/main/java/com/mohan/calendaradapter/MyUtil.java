package com.mohan.calendaradapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mohang on 28/9/17.
 */

public class MyUtil {

    public static String getDateString(Date date){
        SimpleDateFormat fromSDF = new SimpleDateFormat("yyyy-MM-dd");
        return fromSDF.format(date);

    }

    public static String getWeekDayString(Date date){
        SimpleDateFormat fromSDF = new SimpleDateFormat("EEE", Locale.US);;

        return fromSDF.format(date);
    }
    public static String getDateAndMonthString(Date date){
        SimpleDateFormat fromSDF = new SimpleDateFormat("dd MMM", Locale.US);
        return fromSDF.format(date);
    }
}
