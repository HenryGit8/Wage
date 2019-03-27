/**
 * CheckDayServiceImpl.java 2018/1/22 10:54
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wage.api.CheckDayService;
import com.wage.contract.CheckDayHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;

/**
 * File：CheckDayServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class CheckDayServiceImpl implements CheckDayService {

    @Autowired
    CheckDayHandler checkDayHandler;

    /**
     * 查询节假日(0:工作日,1:周休息日,2:节假日)
     * @return
     * @author 何友池
     */
    @Override
    public Integer getHoliday(Date day){
        return checkDayHandler.getHoliday(day);
    }

    /**
     * 判断是否为工作时间(0:工作时间,1:周休息日,2:节假日,3:日非工作时间)
     * @param time
     * @return
     * @author 何友池
     */
    @Override
    public Integer getWorkTime(Date time){
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
    @Override
    public Integer getWorkTimeQj(Date startTime,Date endTime){
        Integer result = 0;
        try {
            result =  checkDayHandler.getWorkTimeQj(startTime, endTime);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 判断一个区间是否包含非工作时间
     * 0：不包含，1：包含,2：开始时间不大于结束时间
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    @Override
    public Integer getWorkTimeQjN(Date startTime,Date endTime){
        Integer result = 0;
        try {
            result =  checkDayHandler.getWorkTimeQjN(startTime, endTime);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据开始结束时间计算工作总时间
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     */
    @Override
    public double getWorkHours(Date startTime,Date endTime){
        double result = 0;
        try {
            result =  checkDayHandler.getWorkHours(startTime, endTime);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return result;
    }
}
