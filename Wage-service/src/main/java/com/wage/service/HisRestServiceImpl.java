/**
 * HisRestServiceImpl.java 2018/1/10 10:10
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.wage.api.HisRestService;
import com.wage.contract.CheckDayHandler;
import com.wage.contract.HisRestHandler;
import com.wage.model.HisRest;
import com.wage.model.HisRestKey;
import com.wage.model.HisRevokeApply;
import com.wage.model.ext.HisRestExt;
import com.wage.model.ext.HisRestNExt;
import com.wage.model.ext.HisRevokeApplyExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * File：HisRestServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class HisRestServiceImpl implements HisRestService {

    @Autowired
    HisRestHandler hisRestHandler;

    @Autowired
    CheckDayHandler checkDayHandler;

    /**
     * 根据员工编号查询请假记录
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisRestNExt> getByEmpid(PageInfo page, Integer empId, Date restStartTimeMin, Date restStartTimeMax){
        return hisRestHandler.getByEmpid(page, empId, restStartTimeMin, restStartTimeMax);
    }

    /**
     * 获取请假记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getRestByEmpid(Integer empId){
        return hisRestHandler.getRestByEmpid(empId);
    }

    /**
     * 更新一条请假信息数据
     * @param record
     * @author 何友池
     */
    @Override
    public void updateHisRest(HisRestExt record){
        hisRestHandler.updateHisRest(record);
        try {
            checkDayHandler.updateRestWage(record.getEmpId());
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 删除一条请假记录
     * @param key
     * @author 何友池
     */
    @Override
    public void removeHisRest(HisRestKey key){
        hisRestHandler.removeHisRest(key);
        try {
            checkDayHandler.updateRestWage(key.getEmpId());
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 新增一条请假记录
     * @param record
     * @author 何友池
     */
    @Override
    public void saveHisRest(HisRest record){
        hisRestHandler.saveHisRest(record);
        try {
            checkDayHandler.updateRestWage(record.getEmpId());
        }catch (ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * 查询全部撤回请假申请数据
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisRevokeApplyExt> getRevokeApply(PageInfo page,Short isAllow){
        return hisRestHandler.getRevokeApply(page, isAllow);
    }

    /**
     * 更新审批结果
     * @param isAllow
     * @param id
     * @author 何友池
     */
    @Override
    public void updateRevokeApply(Short isAllow,Integer id){
        hisRestHandler.updateRevokeApply(isAllow, id);
        try {
            checkDayHandler.updateRestWage(hisRestHandler.getEmpId(id));
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
    /**
     * 判断此员工的请假时间是否与数据库重叠
     * @param empId
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    @Override
    public boolean checkIsOverLap(Integer empId,Date startTime,Date endTime){
        try {
            return hisRestHandler.checkIsOverLap(empId, startTime, endTime);
        }catch (ParseException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 提交撤回请假申请
     * @param record
     * @author 何友池
     */
    @Override
    public void saveRestApply(HisRevokeApply record){
        hisRestHandler.saveRestApply(record);
    }
}
