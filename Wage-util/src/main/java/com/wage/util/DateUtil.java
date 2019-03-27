package com.wage.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2016-04-13 下午7:03
 * Company: wondersgroup.com
 *
 * @author jerry.pan
 * @version 1.0
 */
public class DateUtil {

    // 私有构造器，防止类的实例化
    private DateUtil() {
        super();
    }

    /**
     * 根据长整型时间戳返回指定格式的日期字符串
     *
     * @param date   日期
     * @param format 日期格式
     * @return String 指定格式的日期字符串
     */
    public static String getDateString(Date date, String format) {
        if (null == date || null == format) {//因为默认当参数为空的时候，DateTime的方法不会报错，时间会变成当前时间
            throw new NullPointerException("日期参数为null");
        }
        DateTime dt = new DateTime(date);
        return dt.toString(format);
    }

    /**
     * 根据长整型时间戳返回指定格式的日期字符串
     *
     * @param milliseconds 日期
     * @param format       日期格式
     * @return String 指定格式的日期字符串
     */
    public static String getDateString(Long milliseconds, String format) {
        if (null == milliseconds || null == format) {
            throw new NullPointerException("日期参数为null");
        }
        DateTime dt = new DateTime(milliseconds);
        return dt.toString(format);
    }

    /**
     * 根据日期格式的字符串取得长整数时间戳
     * @param date 日期格式的字符串
     * @return Date 日期对象
     */
    public static Date getDate(String date, String format) {
        if (StringUtils.isBlank(date) || null == format) {
            throw new NullPointerException("日期参数为空");
        }
        DateTime dt = DateTime.parse(date, DateTimeFormat.forPattern(format));
        return dt.toDate();
    }

    /**
     * 距离当前时间多久，例如：”3 小时 前“
     * @param date 日期
     * @return String
     */
    public static String getDateStringFromNow(Date date) {
        if (null == date) {
            throw new NullPointerException("日期参数为null");
        }
        PrettyTime p = new PrettyTime();
        return p.format(date);
    }
}
