/**
 * SpringQtz.java 2018/2/2 14:22
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.common;

import com.wage.model.ScheduleJob;
import com.wage.web.handler.HisSalaryHandler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;


/**
 * File：SpringQtz.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class SpringQtz {

    @Autowired
    HisSalaryHandler hisSalaryHandler;

    private static int counter = 0;

    protected void execute()  {
        hisSalaryHandler.updateNewDeductOther(null);
        System.out.println("上月数据生成完毕，本月初始化数据完毕！");
    }
}
