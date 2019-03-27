/**
 * CheckDayController.java 2018/1/22 10:58
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.wage.model.PageRequest;
import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.handler.CheckDayHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * File：CheckDayController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/checkDay")
public class CheckDayController {

    @Autowired
    CheckDayHandler checkDayHandler;

    /**
     * 查询节假日(0:工作日,1:周休息日,2:节假日)
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getHoliday")
    @ResponseBody
    public Object getHoliday(Date day){
        return checkDayHandler.getHoliday(day);
    }

    /**
     * 判断是否为工作时间(0:工作时间,1:周休息日,2:节假日,3:日非工作时间)
     * @param time
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getWorkTime")
    @ResponseBody
    public Object getWorkTime(Date time){
        return checkDayHandler.getWorkTime(time);
    }

    /**
     * 判断一个区间是否包含工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getWorkTimeQj")
    @ResponseBody
    public Object getWorkTimeQj(Date startTime,Date endTime){
        return checkDayHandler.getWorkTimeQj(startTime, endTime);
    }

    /**
     * 判断一个区间是否包含非工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getWorkTimeQjN")
    @ResponseBody
    public Object getWorkTimeQjN(Date startTime,Date endTime){
        return checkDayHandler.getWorkTimeQjN(startTime, endTime);
    }

    /**
     * 根据开始结束时间计算工作总时间
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/getWorkHours")
    @ResponseBody
    public Object getWorkHours(Date startTime,Date endTime){
        System.out.println(endTime);
        return checkDayHandler.getWorkHours(startTime, endTime);
    }
}
