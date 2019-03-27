/**
 * WelfareHandler.java 2018/1/24 14:20
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.wage.api.WelfareService;
import com.wage.model.EmpWelfare;
import com.wage.model.HisWelfare;
import com.wage.model.PageRequest;
import com.wage.model.ext.EmpWelfareExt;
import com.wage.model.ext.HisWelfareExt;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.PageUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * File：WelfareHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class WelfareHandler {

    @Reference
    WelfareService welfareService;

    /**
     * 根据查询条件查询福利表
     * @param pageRequest
     * @param empId
     * @param applyTimeMin
     * @param applyTimeMax
     * @param isAllow
     * @param welfareType
     * @param welfareTotalMin
     * @param welfareTotalMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    public PageInfo<HisWelfareExt> getBySearch(PageRequest pageRequest, Integer empId, Date applyTimeMin, Date applyTimeMax, Short isAllow, Short welfareType,
                                               BigDecimal welfareTotalMin, BigDecimal welfareTotalMax, String sort, String order){
        PageInfo page = PageUtil.getPage(pageRequest);
        return welfareService.getBySearch(page,empId, applyTimeMin, applyTimeMax, isAllow,  welfareType, welfareTotalMin, welfareTotalMax, sort, order);
    }
    /**
     * 更新一条福利表数据
     * @param serialId
     * @param isAllow
     * @author 何友池
     */
    public void updateWelfare(Long serialId,Short isAllow){
        Integer empId = ContextHolder.getSessionEmp().getEmpId();
        welfareService.updateWelfare(serialId, isAllow,empId);
    }

    /**
     * 插入一条福利数据
     * @param record
     * @author 何友池
     */
    public void saveWelfare(HisWelfare record){
        record.setEmpId(ContextHolder.getSessionEmp().getEmpId());
        welfareService.saveWelfare(record);
    }

    /**
     * 插入多条福利数据
     * @param list
     * @return
     * @author 何友池
     */
    public void saveWelfareList(List<HisWelfare> list){
        welfareService.saveWelfareList(list);
    }

    /**
     * 根据条件查找员工福利记录
     * @param pageRequest
     * @param empId
     * @param type
     * @param effectiveDateMin
     * @param effectiveDateMax
     * @param moneyMin
     * @param moneyMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    public PageInfo<EmpWelfareExt> getEmpWelBySearch(PageRequest pageRequest, Integer empId, String type, Date effectiveDateMin, Date effectiveDateMax,
                                                     BigDecimal moneyMin, BigDecimal moneyMax, String sort, String order){
        PageInfo page = PageUtil.getPage(pageRequest);
        return welfareService.getEmpWelBySearch(page,empId, type, effectiveDateMin, effectiveDateMax,moneyMin, moneyMax, sort, order);
    }

    /**
     * 更新一条员工福利记录
     * @param record
     * @author 何友池
     */
    public void updateEmpWelfare(EmpWelfare record){
        welfareService.updateEmpWelfare(record);
    }

    /**
     * 插入一条员工福利记录
     * @param record
     * @return
     * @author 何友池
     */
    public void saveEmpWelfare(EmpWelfare record){
        welfareService.saveEmpWelfare(record);
    }

    /**
     * 插入多条员工福利记录
     * @param list
     * @return
     * @author 何友池
     */
    public void saveEmpWelfareList(List<EmpWelfare> list){
        welfareService.saveEmpWelfareList(list);
    }

    /**
     * 删除一条员工福利记录
     * @param id
     * @author 何友池
     */
    public void removeEmpWelfare(Long id){
        welfareService.removeEmpWelfare(id);
    }
    /**
     * 根据条件查找登录员工福利记录
     * @param pageRequest
     * @param type
     * @param effectiveDateMin
     * @param effectiveDateMax
     * @param moneyMin
     * @param moneyMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    public PageInfo<EmpWelfareExt> getLoginEmpWel(PageRequest pageRequest, String type, Date effectiveDateMin, Date effectiveDateMax,
                                                     BigDecimal moneyMin, BigDecimal moneyMax, String sort, String order){
        PageInfo page = PageUtil.getPage(pageRequest);
        return welfareService.getEmpWelBySearch(page,ContextHolder.getSessionEmp().getEmpId(), type, effectiveDateMin, effectiveDateMax,moneyMin, moneyMax, sort, order);
    }

    /**
     * 根据查询条件查询登录者福利表
     * @param pageRequest
     * @param applyTimeMin
     * @param applyTimeMax
     * @param isAllow
     * @param welfareType
     * @param welfareTotalMin
     * @param welfareTotalMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    public PageInfo<HisWelfareExt> getLoginBySearch(PageRequest pageRequest, Date applyTimeMin, Date applyTimeMax, Short isAllow, Short welfareType,
                                               BigDecimal welfareTotalMin, BigDecimal welfareTotalMax, String sort, String order){
        PageInfo page = PageUtil.getPage(pageRequest);
        return welfareService.getBySearch(page,ContextHolder.getSessionEmp().getEmpId(), applyTimeMin, applyTimeMax, isAllow,  welfareType, welfareTotalMin, welfareTotalMax, sort, order);
    }
}
