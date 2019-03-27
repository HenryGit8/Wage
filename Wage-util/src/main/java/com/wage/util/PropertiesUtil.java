package com.wage.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * Title: 属性工具类
 * Description:
 * Copyright: Copyright (c) 2017-03-30 下午3:01
 * Company: wondersgroup.com
 *
 * @author jerry.pan
 * @version 1.0
 */
public class PropertiesUtil {

    private PropertiesUtil() {
        super();
    }

    private static final String DEFAULT_PATH_PREFIX = "/";

    private static final String DEFAULT_PROPERTIES_FILENAME = "project.properties";

    /**
     * 到默认的属性文件中，查找属性名称对应的属性值
     * @param name 属性名称
     * @return
     * @author jerry.pan
     */
    public static String getProperty(String name){
        //如果没有指定属性文件名称，就读取默认的属性文件
        return getProperty(name,DEFAULT_PROPERTIES_FILENAME);
    }

    /**
     * 到指定的属性文件中，查找属性名称对应的属性值(要求文件都放在classes路径下)
     * @param
     * @return
     * @author jerry.pan
     */
    public static String getProperty(String name,String filename){
        String value = null;
        try {
            filename = DEFAULT_PATH_PREFIX + filename;
            InputStream inputStream = PropertiesUtil.class.getResourceAsStream(filename);
            Properties p = new Properties();
            p.load(inputStream);
            value = p.getProperty(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
