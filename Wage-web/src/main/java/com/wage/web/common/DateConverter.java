/**
 * DateConverter.java 2018/1/26 10:08
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * File：DateConverter.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String stringDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                d = simpleDateFormat.parse(stringDate);
            }catch (ParseException e1){
                return null;
            }
        }
        return d;
    }
}