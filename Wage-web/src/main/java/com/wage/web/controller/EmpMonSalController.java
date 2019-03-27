/**
 * EmpMonSalController.java 2017/12/29 14:44
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.wage.model.EmpDicBasicInfo;
import com.wage.model.EmpMonOtherSal;
import com.wage.model.EmpMonSal;
import com.wage.model.PageRequest;
import com.wage.model.ext.EmpMonOtherSalExt;
import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.handler.EmpMonSalHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * File：EmpMonSalController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/empMonSal")
public class EmpMonSalController {

    @Autowired
    EmpMonSalHandler empMonSalHandler;

    /**
     * 根据员工编号查询
     * @param pageRequest
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getByEmpId")
    @ResponseBody
    public Object getByEmpId(PageRequest pageRequest, Integer empId){
        return new JsonResult(DescribableEnum.SUCCESS,empMonSalHandler.getByEmpId(pageRequest,empId));
    }
    /**
     * 获得近6个月总额
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getTotal")
    @ResponseBody
    public Object getTotal(Integer empId,Integer changeTime){
        return empMonSalHandler.getTotal(empId, changeTime);
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
    @RequestMapping(value = "/getMonSalBySearch")
    @ResponseBody
    public Object getMonSalBySearch(PageRequest pageRequest, String empName, BigDecimal basicPayStart, BigDecimal basicPayEnd,
                                    BigDecimal jobSalaryStart, BigDecimal jobSalaryEnd, BigDecimal totalStart, BigDecimal totalEnd,String sort,String order){
        return new JsonResult(DescribableEnum.SUCCESS,empMonSalHandler.getBySearch(pageRequest,empName, basicPayStart, basicPayEnd, jobSalaryStart, jobSalaryEnd, totalStart, totalEnd, sort, order));
    }

    /**
     * 更新固定工资表
     * @param empMonSal
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateEmpMonSal")
    @ResponseBody
    public Object updateEmpMonSal(EmpMonSal empMonSal){
        empMonSalHandler.updateEmpMonSal(empMonSal);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 查询额外工资
     * @param pageRequest
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getOtherSal")
    @ResponseBody
    public Object getOtherSal(PageRequest pageRequest, Integer empId,String sort,String order){
        return new JsonResult(DescribableEnum.SUCCESS,empMonSalHandler.getOtherSal(pageRequest, empId, sort, order));
    }
    /**
     * 更新一条额外工资表数据
     * @param empMonOtherSal
     * @author 何友池
     */
    @RequestMapping(value = "/updateOtherSal")
    @ResponseBody
    public Object updateOtherSal(EmpMonOtherSalExt empMonOtherSal){
        empMonSalHandler.updateOtherSal(empMonOtherSal);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 插入所有月工资表的数据到本月固定工资表中
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveAllMonSal")
    @ResponseBody
    public Object saveAllMonSal(){
        empMonSalHandler.insertAllMonSal();
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 获取一条所有类型工资数据
     * @param empId
     * @param yearMonth
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getOtherSalOne")
    @ResponseBody
    public Object getOtherSalOne(Integer empId,Integer yearMonth){
        return empMonSalHandler.getOtherSalOne(empId, yearMonth);
    }

    /**
     * 根据条件查询登录员工固定工资表
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
    @RequestMapping(value = "/getLoginByEmp")
    @ResponseBody
    public Object getLoginByEmp(PageRequest pageRequest, String empName, BigDecimal basicPayStart, BigDecimal basicPayEnd,
                                    BigDecimal jobSalaryStart, BigDecimal jobSalaryEnd, BigDecimal totalStart, BigDecimal totalEnd,String sort,String order){
        return new JsonResult(DescribableEnum.SUCCESS,empMonSalHandler.getLoginByEmp(pageRequest,empName, basicPayStart, basicPayEnd, jobSalaryStart, jobSalaryEnd, totalStart, totalEnd, sort, order));
    }
}
