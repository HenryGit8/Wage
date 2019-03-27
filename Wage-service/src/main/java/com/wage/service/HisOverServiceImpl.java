/**
 * HisOverServiceImpl.java 2018/1/17 14:17
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.wage.api.HisOverService;
import com.wage.contract.CheckDayHandler;
import com.wage.contract.HisOverHandler;
import com.wage.model.HisOver;
import com.wage.model.HisOverKey;
import com.wage.model.ext.HisOverExt;
import com.wage.model.ext.HisOverOExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * File：HisOverServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class HisOverServiceImpl implements HisOverService {

    @Autowired
    HisOverHandler hisOverHandler;

    @Autowired
    CheckDayHandler checkDayHandler;

    /**
     * 根据员工编号查询加班记录表
     * @param page
     * @param empId
     * @param overTimeStartMin
     * @param overTimeStartMax
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisOverExt> getByEmpid(PageInfo page, Integer empId, Date overTimeStartMin, Date overTimeStartMax){
        return hisOverHandler.getByEmpid(page, empId, overTimeStartMin, overTimeStartMax);
    }
    /**
     * 更新加班记录表
     * @param record
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateHisOver(HisOverOExt record){
        hisOverHandler.updateHisOver(record);
        try {
            checkDayHandler.updateOverWage(record.getEmpId());
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    /**
     * 获取加班记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getOverByEmpid(Integer empId){
        return hisOverHandler.getOverByEmpid(empId);
    }
    /**
     * 保存一条加班信息
     * @param hisOver
     * @author 何友池
     */
    @Override
    @Transactional
    public void saveHisOver(HisOver hisOver){
        hisOverHandler.saveHisOver(hisOver);
        try {
            checkDayHandler.updateOverWage(hisOver.getEmpId());
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 删除一条加班信息
     * @param key
     * @author 何友池
     */
    @Override
    @Transactional
    public void removeHisOver(HisOverKey key){
        hisOverHandler.removeHisOver(key);
        try {
            checkDayHandler.updateOverWage(key.getEmpId());
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 根据是否已审核查询
     * @param page
     * @param isCheck
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisOverExt> getByEmpidC(PageInfo page, Short isCheck){
        return hisOverHandler.getByEmpidC(page, isCheck);
    }

    /**
     * 更新加班审核结果
     * @param empId
     * @param overTimeStart
     * @param isCheck
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateOverApply(Integer empId,Date overTimeStart,Short isCheck){
        hisOverHandler.updateOverApply(empId, overTimeStart, isCheck);
        try {
            checkDayHandler.updateOverWage(empId);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 判断此员工的加班时间是否与数据库重叠
     * @param empId
     * @param startTime
     * @param endTime
     * @return
     * @throws ParseException
     * @author 何友池
     */
    @Override
    public boolean checkIsOverLap(Integer empId,Date startTime,Date endTime){
        try {
            return hisOverHandler.checkIsOverLap(empId, startTime, endTime);
        }catch (ParseException e){
            e.printStackTrace();
            return false;
        }
    }
}
