package com.chuyx.utils;

import java.sql.Date;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/

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
}
