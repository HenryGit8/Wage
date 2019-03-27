/**
 * RedisTestServiceImpl.java 2018/2/27 16:53
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wage.api.RedisTestService;

/**
 * File：RedisTestServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class RedisTestServiceImpl implements RedisTestService {

    @Override
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        System.out.println("*****"+timestamp);
        return timestamp.toString();
    }
}
