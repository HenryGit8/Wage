/**
 * WelfareHandlerImpl.java 2018/1/24 14:21
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.biz.mybatis.EmpWelfareMapper;
import com.wage.biz.mybatis.HisWelfareMapper;
import com.wage.contract.WelfareHandler;
import com.wage.model.EmpWelfare;
import com.wage.model.HisWelfare;
import com.wage.model.ext.EmpWelfareExt;
import com.wage.model.ext.HisWelfareExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * File：WelfareHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class WelfareHandlerImpl implements WelfareHandler {

    @Autowired
    HisWelfareMapper hisWelfareMapper;

    @Autowired
    EmpWelfareMapper empWelfareMapper;

    /**
     * 根据查询条件查询福利表
     * @param page
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
    @Override
    public PageInfo<HisWelfareExt> getBySearch(PageInfo page, Integer empId, Date applyTimeMin, Date applyTimeMax,Short isAllow, Short welfareType,
                                                BigDecimal welfareTotalMin,BigDecimal welfareTotalMax, String sort, String order){
        PageHelper.startPage(page);
        List<HisWelfareExt> list = hisWelfareMapper.findBySearch(empId, applyTimeMin, applyTimeMax, isAllow,  welfareType, welfareTotalMin, welfareTotalMax, sort, order);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新一条福利表数据
     * @param serialId
     * @param isAllow
     * @author 何友池
     */
    @Override
    public Integer updateWelfare(Long serialId,Short isAllow,Integer empId){
        HisWelfare hisWelfare = hisWelfareMapper.selectByPrimaryKey(serialId);
        if(isAllow == 1){
            EmpWelfare record = new EmpWelfare();
            record.setEmpId(hisWelfare.getEmpId());
            record.setMoney(hisWelfare.getWelfareTotal());
            record.setReason(hisWelfare.getApplyReason());
            record.setType(hisWelfare.getWelfareType());
            empWelfareMapper.insertEmpWelfare(record);
        }
        HisWelfare record = new HisWelfare();
        record.setSerialId(serialId);
        record.setIsAllow(isAllow);
        record.setApproverEmpid(empId);
        hisWelfareMapper.updateByPrimaryKeySelective(record);
        return hisWelfare.getEmpId();
    }

    /**
     * 插入一条福利数据
     * @param record
     * @author 何友池
     */
    @Override
    public void saveWelfare(HisWelfare record){
        hisWelfareMapper.insertWelfare(record);
    }

    /**
     * 插入多条福利数据
     * @param list
     * @return
     * @author 何友池
     */
    @Override
    public void saveWelfareList(List<HisWelfare> list){
        hisWelfareMapper.insertWelfareList(list);
    }

    /**
     * 根据条件查找员工福利记录
     * @param page
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
    @Override
    public PageInfo<EmpWelfareExt> getEmpWelBySearch(PageInfo page, Integer empId, String type, Date effectiveDateMin, Date effectiveDateMax,
                                               BigDecimal moneyMin, BigDecimal moneyMax, String sort, String order){
        PageHelper.startPage(page);
        List<EmpWelfareExt> list = empWelfareMapper.findBySearch(empId, type, effectiveDateMin, effectiveDateMax,moneyMin, moneyMax, sort, order);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新一条员工福利记录
     * @param record
     * @author 何友池
     */
    @Override
    public void updateEmpWelfare(EmpWelfare record){
        empWelfareMapper.updateByPrimaryKey(record);
    }

    /**
     * 插入一条员工福利记录
     * @param record
     * @return
     * @author 何友池
     */
    @Override
    public void saveEmpWelfare(EmpWelfare record){
        empWelfareMapper.insertEmpWelfare(record);
    }

    /**
     * 插入多条员工福利记录
     * @param list
     * @return
     * @author 何友池
     */
    @Override
    public void saveEmpWelfareList(List<EmpWelfare> list){
        empWelfareMapper.insertEmpWelfareList(list);
    }

    /**
     * 删除一条员工福利记录
     * @param id
     * @author 何友池
     */
    @Override
    public void removeEmpWelfare(Long id){
        empWelfareMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据流水号查询员工福利数据
     * @param id
     * @return
     * @author 何友池
     */
    @Override
    public EmpWelfare getById(Long id){
        return empWelfareMapper.selectByPrimaryKey(id);
    }
}
