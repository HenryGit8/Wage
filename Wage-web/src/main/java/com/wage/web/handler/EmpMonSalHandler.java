/**
 * EmpMonSalHandler.java 2017/12/29 14:45
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.wage.api.EmpMonSalService;
import com.wage.model.EmpMonOtherSal;
import com.wage.model.EmpMonSal;
import com.wage.model.PageRequest;
import com.wage.model.ext.EmpMonOtherSalExt;
import com.wage.model.ext.EmpMonSalAllExt;
import com.wage.model.ext.EmpMonSalExt;
import com.wage.model.ext.HisSalChangeExt;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.PageUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * File：EmpMonSalHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class EmpMonSalHandler {

    @Reference
    EmpMonSalService empMonSalService;

    /**
     * 根据员工编号查询
     * @param pageRequest
     * @param empId
     * @return
     * @author 何友池
     */
    public PageInfo<HisSalChangeExt> getByEmpId(PageRequest pageRequest, Integer empId){
        PageInfo page = PageUtil.getPage(pageRequest);
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return empMonSalService.getByEmpId(page, empId);
    }
    /**
     * 获得近6个月总额
     * @param empId
     * @return
     * @author 何友池
     */
    public Map<String,Object> getTotal(Integer empId,Integer changeTime){
        return empMonSalService.getTotal(empId, changeTime);
    }

    /**
     * 根据条件查询固定工资表
     * @param pageRequest
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
    public PageInfo<EmpMonSalExt> getBySearch(PageRequest pageRequest, String empName, BigDecimal basicPayStart, BigDecimal basicPayEnd,
                                              BigDecimal jobSalaryStart, BigDecimal jobSalaryEnd, BigDecimal totalStart, BigDecimal totalEnd,String sort,String order) {
        PageInfo page = PageUtil.getPage(pageRequest);
        return empMonSalService.getBySearch(page,empName,null, basicPayStart, basicPayEnd, jobSalaryStart, jobSalaryEnd, totalStart, totalEnd, sort, order);
    }

    /**
     * 更新固定工资表
     * @param empMonSal
     * @author 何友池
     */
    public void updateEmpMonSal(EmpMonSal empMonSal){
        empMonSalService.updateEmpMonSal(empMonSal);
    }

    /**
     * 查询额外工资
     * @param pageRequest
     * @param empId
     * @return
     * @author 何友池
     */
    public PageInfo<EmpMonOtherSalExt> getOtherSal(PageRequest pageRequest, Integer empId,String sort,String order){
        PageInfo page = PageUtil.getPage(pageRequest);
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return empMonSalService.getOtherSal(page, empId, sort, order);
    }

    /**
     * 更新一条额外工资表数据
     * @param empMonOtherSal
     * @author 何友池
     */
    public void updateOtherSal(EmpMonOtherSalExt empMonOtherSal){
        empMonSalService.updateOtherSal(empMonOtherSal);
    }

    /**
     * 插入所有月工资表的数据到本月固定工资表中
     * @return
     * @author 何友池
     */
    public void insertAllMonSal(){
        empMonSalService.insertAllMonSal();
    }

    /**
     * 获取一条所有类型工资数据
     * @param empId
     * @param yearMonth
     * @return
     * @author 何友池
     */
    public EmpMonSalAllExt getOtherSalOne(Integer empId,Integer yearMonth){
        return empMonSalService.getWageByEmpId(empId, yearMonth);
    }
    /**
     * 根据条件查询固定工资表
     * @param pageRequest
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
    public PageInfo<EmpMonSalExt> getLoginByEmp(PageRequest pageRequest, String empName, BigDecimal basicPayStart, BigDecimal basicPayEnd,
                                              BigDecimal jobSalaryStart, BigDecimal jobSalaryEnd, BigDecimal totalStart, BigDecimal totalEnd,String sort,String order) {
        PageInfo page = PageUtil.getPage(pageRequest);
        return empMonSalService.getBySearch(page, empName,ContextHolder.getSessionEmp().getEmpId(), basicPayStart, basicPayEnd, jobSalaryStart, jobSalaryEnd, totalStart, totalEnd, sort, order);
    }
}
