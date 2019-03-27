/**
 * PhoneFormatCheckUtils.java 2018/5/10 16:32
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * File：PhoneFormatCheckUtils.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class PhoneFormatCheckUtils {


    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "\bselect\b";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    public static String replaceSQLSpecialChar(String str) {
        str = str.toLowerCase();
        if (isEmpty(str))
            return "";
        str = str.trim();
        String inj_f = "and|exec|insert|select|delete|update|count|chr|mid|master|truncate|char|declare|or";
        String inj_str = "'|*|%|;|+|,";
        //这里的东西还可以自己添加
        String[] inj_stra = inj_str.split("\\|");
        for (int i = 0; i < inj_stra.length; i++) {
            if (str.indexOf(inj_stra[i]) >= 0) {
                str = str.replace(inj_stra[i], "");
            }
        }
        //关键字单词查询
        String[] inj_fa = inj_f.split("\\|");
        for (int i = 0; i < inj_fa.length; i++) {
            if (str.indexOf(inj_fa[i]) >= 0) {
                String re = "\\b"+inj_fa[i]+"\\b";
                str = str.replaceAll(re,"");
            }
        }
        return str;
    }
    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public static void main(String[] args) {
        String s = "select select1 selectselect Select select select android and\n" +
                "and ord or '";

        System.out.println(replaceSQLSpecialChar(s));
    }
}
