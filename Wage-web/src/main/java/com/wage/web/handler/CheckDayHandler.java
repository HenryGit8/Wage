/**
 * CheckDayHandler.java 2018/1/22 10:57
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wage.api.CheckDayService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * File：CheckDayHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class CheckDayHandler {

    @Reference
    CheckDayService checkDayService;

    /**
     * 查询节假日(0:工作日,1:周休息日,2:节假日)
     * @return
     * @author 何友池
     */
    public Integer getHoliday(Date day){
        return checkDayService.getHoliday(day);
    }

    /**
     * 判断是否为工作时间(0:工作时间,1:周休息日,2:节假日,3:日非工作时间)
     * @param time
     * @return
     * @author 何友池
     */
    public Integer getWorkTime(Date time){
        return checkDayService.getWorkTime(time);
    }

    /**
     * 判断一个区间是否包含工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    public Integer getWorkTimeQj(Date startTime,Date endTime){
        return checkDayService.getWorkTimeQj(startTime, endTime);
    }
    /**
     * 判断一个区间是否包含非工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    public Integer getWorkTimeQjN(Date startTime,Date endTime){
        return checkDayService.getWorkTimeQjN(startTime, endTime);
    }
    /**
     * 根据开始结束时间计算工作总时间
     * @param startTime
     * @param endTime
     * @return
     */
    public double getWorkHours(Date startTime,Date endTime){
        return checkDayService.getWorkHours(startTime, endTime);
    }
}
