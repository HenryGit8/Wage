package com.wage.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Title:随机数工具类
 * Description:
 * Copyright: Copyright (c) 2016-04-12 下午5:25
 * Company: wondersgroup.com
 *
 * @author jerry.pan
 * @version 1.0
 */
public class RandomUtil {

    // 私有构造器，防止类的实例化
    private RandomUtil() {
        super();
    }

    /**
     * 创建UUID，去掉横杠”-“
     * @param
     * @return
     * @author jerry.pan
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return StringUtils.replace(uuid, "-", "").toUpperCase();
    }


    /**
     * 生成随机6位数字字串
     *
     * @return String 随机字串
     * @author jerry.pan
     */
    public static String getRandomCode() {
        return RandomStringUtils.randomNumeric(6);
    }
}

