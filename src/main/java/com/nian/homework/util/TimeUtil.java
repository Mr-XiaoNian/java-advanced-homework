package com.nian.homework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {



    public static String getStringByDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }


    public static Date getDateByString(String str, String pattern) {
        try {
            DateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取当前时间（线程安全）
     *
     * @param date
     * @return
     */
    public static String getLocalTimeByDefault(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }


    /**
     * 获取当前时间（线程安全）
     *
     * @param date
     * @return
     */
    public static String getLocalTimeByFormat(Date date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime now = date.toInstant().atZone(zoneId).toLocalDateTime();
        return now.format(formatter);
    }

}
