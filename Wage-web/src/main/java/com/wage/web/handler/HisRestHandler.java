/**
 * HisRestHandler.java 2018/1/10 10:10
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.wage.api.HisRestService;
import com.wage.model.HisRest;
import com.wage.model.HisRestKey;
import com.wage.model.HisRevokeApply;
import com.wage.model.PageRequest;
import com.wage.model.ext.HisRestExt;
import com.wage.model.ext.HisRestNExt;
import com.wage.model.ext.HisRevokeApplyExt;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.PageUtil;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * File：HisRestHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class HisRestHandler {

    @Reference
    HisRestService hisRestService;

    /**
     * 根据员工编号查询请假记录
     * @param pageRequest
     * @param empId
     * @return
     * @author 何友池
     */
    public PageInfo<HisRestNExt> getByEmpid(PageRequest pageRequest, Integer empId, Date restStartTimeMin, Date restStartTimeMax){
        PageInfo page = PageUtil.getPage(pageRequest);
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return hisRestService.getByEmpid(page, empId, restStartTimeMin, restStartTimeMax);
    }

    /**
     * 获取请假记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    public Map<String,Object> getRestByEmpid(Integer empId){
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return hisRestService.getRestByEmpid(empId);
    }
    /**
     * 更新一条请假信息数据
     * @param record
     * @author 何友池
     */
    public void updateHisRest(HisRestExt record){
        hisRestService.updateHisRest(record);
    }

    /**
     * 删除一条请假记录
     * @param key
     * @author 何友池
     */
    public void removeHisRest(HisRestKey key){
        hisRestService.removeHisRest(key);
    }
    /**
     * 新增一套请假记录
     * @param record
     * @author 何友池
     */
    public void saveHisRest(HisRest record){
        hisRestService.saveHisRest(record);
    }

    /**
     * 查询全部撤回请假申请数据
     * @return
     * @author 何友池
     */
    public PageInfo<HisRevokeApplyExt> getRevokeApply(PageRequest pageRequest,Short isAllow){
        PageInfo page = PageUtil.getPage(pageRequest);
        return hisRestService.getRevokeApply(page, isAllow);
    }

    /**
     * 更新审批结果
     * @param isAllow
     * @param id
     * @author 何友池
     */
    public void updateRevokeApply(Short isAllow,Integer id){
        hisRestService.updateRevokeApply(isAllow, id);
    }
    /**
     * 判断此员工的请假时间是否与数据库重叠
     * @param empId
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    public boolean checkIsOverLap(Integer empId,Date startTime,Date endTime){
        return hisRestService.checkIsOverLap(empId, startTime, endTime);
    }

    /**
     * 提交撤回请假申请
     * @param record
     * @author 何友池
     */
    public void saveRestApply(HisRevokeApply record){
        record.setEmpId(ContextHolder.getSessionEmp().getEmpId());
        hisRestService.saveRestApply(record);
    }
}
