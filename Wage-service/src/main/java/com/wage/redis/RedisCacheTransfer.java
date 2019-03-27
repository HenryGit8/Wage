/**
 * RedisCacheTransfer.java 2018/2/28 9:28
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * File：RedisCacheTransfer.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class RedisCacheTransfer {

    @Autowired
    public void setJedisConnectionFactory(
            JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

}
