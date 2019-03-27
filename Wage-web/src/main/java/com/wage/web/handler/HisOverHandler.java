/**
 * HisOverHandler.java 2018/1/17 14:17
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.wage.api.HisOverService;
import com.wage.model.HisOver;
import com.wage.model.HisOverKey;
import com.wage.model.PageRequest;
import com.wage.model.ext.HisOverExt;
import com.wage.model.ext.HisOverOExt;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.PageUtil;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * File：HisOverHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class HisOverHandler {

    @Reference
    HisOverService hisOverService;
    /**
     * 根据员工编号查询加班记录表
     * @param pageRequest
     * @param empId
     * @param overTimeStartMin
     * @param overTimeStartMax
     * @return
     * @author 何友池
     */
    public PageInfo<HisOverExt> getByEmpid(PageRequest pageRequest, Integer empId, Date overTimeStartMin, Date overTimeStartMax){
        PageInfo page = PageUtil.getPage(pageRequest);
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return hisOverService.getByEmpid(page, empId, overTimeStartMin, overTimeStartMax);
    }
    /**
     * 更新加班记录表
     * @param record
     * @author 何友池
     */
    public void updateHisOver(HisOverOExt record){
        hisOverService.updateHisOver(record);
    }
    /**
     * 获取加班记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    public Map<String,Object> getOverByEmpid(Integer empId){
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return hisOverService.getOverByEmpid(empId);
    }
    /**
     * 保存一条加班信息
     * @param hisOver
     * @author 何友池
     */
    public void saveHisOver(HisOver hisOver){
        hisOverService.saveHisOver(hisOver);
    }
    /**
     * 删除一条加班信息
     * @param key
     * @author 何友池
     */
    public void removeHisOver(HisOverKey key){
        hisOverService.removeHisOver(key);
    }

    /**
     * 根据是否已审核查询
     * @param pageRequest
     * @param isCheck
     * @return
     * @author 何友池
     */
    public PageInfo<HisOverExt> getByEmpidC(PageRequest pageRequest, Short isCheck){
        PageInfo page = PageUtil.getPage(pageRequest);
        return hisOverService.getByEmpidC(page, isCheck);
    }

    /**
     * 更新加班审核结果
     * @param empId
     * @param overTimeStart
     * @param isCheck
     * @author 何友池
     */
    public void updateOverApply(Integer empId,Date overTimeStart,Short isCheck){
        hisOverService.updateOverApply(empId, overTimeStart, isCheck);
    }

    /**
     * 判断此员工的加班时间是否与数据库重叠
     * @param empId
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    public boolean checkIsOverLap(Integer empId,Date startTime,Date endTime){
        return hisOverService.checkIsOverLap(empId, startTime, endTime);
    }
}
