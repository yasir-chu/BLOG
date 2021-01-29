package com.chuyx.utils;

import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/
@Slf4j
public class DateUtils {

    /**
     * 得到系统时间戳
     */
    public static long currentTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 得到SQLDate
     */
    public static Date getSqlNowDate(){
        return new Date(System.currentTimeMillis());
    }

    /**
     * 得到一个yyyy-MM-dd HH:mm:ss 的时间字符串
     */
    public static String dateToString(java.util.Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 通过String 得到SQL Date
     * @param date String 的date
     * @return SQL Date
     */
    public static java.util.Date stringToSqlDate(String date){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            log.error("时间日期转化失败-{}", date);
            e.printStackTrace();
        }
        return new java.util.Date();
    }
}
