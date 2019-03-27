/**
 * WelfareServiceImpl.java 2018/1/24 14:20
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.api.WelfareService;
import com.wage.contract.EmpMonSalHandler;
import com.wage.contract.WelfareHandler;
import com.wage.model.EmpWelfare;
import com.wage.model.HisWelfare;
import com.wage.model.ext.EmpWelfareExt;
import com.wage.model.ext.HisWelfareExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * File：WelfareServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class WelfareServiceImpl implements WelfareService {

    @Autowired
    WelfareHandler welfareHandler;

    @Autowired
    EmpMonSalHandler empMonSalHandler;

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
    public PageInfo<HisWelfareExt> getBySearch(PageInfo page, Integer empId, Date applyTimeMin, Date applyTimeMax, Short isAllow, Short welfareType,
                                        BigDecimal welfareTotalMin, BigDecimal welfareTotalMax, String sort, String order){
        return welfareHandler.getBySearch(page,empId, applyTimeMin, applyTimeMax, isAllow,  welfareType, welfareTotalMin, welfareTotalMax, sort, order);
    }
    /**
     * 更新一条福利表数据
     * @param serialId
     * @param isAllow
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateWelfare(Long serialId,Short isAllow,Integer empId){
        Integer emp = welfareHandler.updateWelfare(serialId, isAllow, empId);
        empMonSalHandler.updateWelfareData(emp);
    }

    /**
     * 插入一条福利数据
     * @param record
     * @author 何友池
     */
    @Override
    public void saveWelfare(HisWelfare record){
        welfareHandler.saveWelfare(record);
    }

    /**
     * 插入多条福利数据
     * @param list
     * @return
     * @author 何友池
     */
    @Override
    public void saveWelfareList(List<HisWelfare> list){
        welfareHandler.saveWelfareList(list);
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
        return welfareHandler.getEmpWelBySearch(page,empId, type, effectiveDateMin, effectiveDateMax,moneyMin, moneyMax, sort, order);
    }

    /**
     * 更新一条员工福利记录
     * @param record
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateEmpWelfare(EmpWelfare record){
        welfareHandler.updateEmpWelfare(record);
        empMonSalHandler.updateWelfareData(record.getEmpId());
    }

    /**
     * 插入一条员工福利记录
     * @param record
     * @return
     * @author 何友池
     */
    @Override
    @Transactional
    public void saveEmpWelfare(EmpWelfare record){
        welfareHandler.saveEmpWelfare(record);
        empMonSalHandler.updateWelfareData(record.getEmpId());
    }

    /**
     * 插入多条员工福利记录
     * @param list
     * @return
     * @author 何友池
     */
    @Override
    @Transactional
    public void saveEmpWelfareList(List<EmpWelfare> list){
        welfareHandler.saveEmpWelfareList(list);
        List<Integer> empIdList = new ArrayList<>();
        for (EmpWelfare empWelfare : list) {
            empIdList.add(empWelfare.getEmpId());
        }
        empMonSalHandler.updateWelfareDataList(empIdList);
    }

    /**
     * 删除一条员工福利记录
     * @param id
     * @author 何友池
     */
    @Override
    @Transactional
    public void removeEmpWelfare(Long id){
        Integer empId = welfareHandler.getById(id).getEmpId();
        welfareHandler.removeEmpWelfare(id);
        empMonSalHandler.updateWelfareData(empId);
    }

}
