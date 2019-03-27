/**
 * BeanDefineConfigue.java 2018/2/5 10:30
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * File：BeanDefineConfigue.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component("BeanDefineConfigue")
public class BeanDefineConfigue implements
        ApplicationListener<ContextRefreshedEvent> {//ContextRefreshedEvent为初始化完毕事件，spring还有很多事件可以利用

// @Autowired
// private IRoleDao roleDao;


    /**
     * 当一个ApplicationContext被初始化或刷新触发
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {//root application context 没有parent，他就是老大.
            System.out.println("spring容器初始化完毕================================================");
        }
// roleDao.getUserList();//spring容器初始化完毕加载用户列表到内存
    }
}