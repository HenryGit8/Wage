/**
 * EmpMonSalServiceImpl.java 2017/12/29 14:46
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.wage.api.EmpMonSalService;
import com.wage.contract.EmpMonSalHandler;
import com.wage.model.EmpMonOtherSal;
import com.wage.model.EmpMonSal;
import com.wage.model.ext.EmpMonOtherSalExt;
import com.wage.model.ext.EmpMonSalAllExt;
import com.wage.model.ext.EmpMonSalExt;
import com.wage.model.ext.HisSalChangeExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Map;

/**
 * File：EmpMonSalServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class EmpMonSalServiceImpl implements EmpMonSalService {

    @Autowired
    EmpMonSalHandler empMonSalHandler;

    /**
     * 根据员工编号查询
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisSalChangeExt> getByEmpId(PageInfo page, Integer empId){
        return empMonSalHandler.getByEmpId(page, empId);
    }
    /**
     * 获得近6个月总额
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getTotal(Integer empId,Integer changeTime){
        return empMonSalHandler.getTotal(empId,changeTime);
    }

    /**
     * 根据条件查询固定工资表
     * @param page
     * @param empName
     * @param basicPayStart
     * @param basicPayEnd
     * @param jobSalaryStart
     * @param jobSalaryEnd
     * @param totalStart
     * @param totalEnd
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpMonSalExt> getBySearch(PageInfo page, String empName,Integer empId, BigDecimal basicPayStart, BigDecimal basicPayEnd,
                                              BigDecimal jobSalaryStart, BigDecimal jobSalaryEnd, BigDecimal totalStart, BigDecimal totalEnd,String sort,String order) {
        return empMonSalHandler.getBySearch(page,empName, empId, basicPayStart, basicPayEnd, jobSalaryStart, jobSalaryEnd, totalStart, totalEnd, sort, order);
    }

    /**
     * 更新固定工资表
     * @param empMonSal
     * @author 何友池
     */
    @Override
    public void updateEmpMonSal(EmpMonSal empMonSal){
        empMonSalHandler.updateEmpMonSal(empMonSal);
    }
    /**
     * 查询额外工资
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpMonOtherSalExt> getOtherSal(PageInfo page, Integer empId,String sort,String order){
        return empMonSalHandler.getOtherSal(page, empId, sort, order);
    }

    /**
     * 更新一条额外工资表数据
     * @param empMonOtherSal
     * @author 何友池
     */
    @Override
    public void updateOtherSal(EmpMonOtherSalExt empMonOtherSal){
        empMonSalHandler.updateOtherSal(empMonOtherSal);
    }

    /**
     * 插入所有月工资表的数据到本月固定工资表中
     * @return
     * @author 何友池
     */
    @Override
    public void insertAllMonSal(){
        empMonSalHandler.insertAllMonSal();
    }
    /**
     * 根据员工编号查询各类型工资
     * @param empId
     * @param yearMonth
     * @return
     * @author 何友池
     */
    @Override
    public EmpMonSalAllExt getWageByEmpId(Integer empId, Integer yearMonth){
        return empMonSalHandler.getWageByEmpId(empId, yearMonth);
    }
}
