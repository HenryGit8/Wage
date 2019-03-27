/**
 * EmpMonSalHandlerImpl.java 2017/12/29 14:47
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.biz.mybatis.EmpMonDeductSalMapper;
import com.wage.biz.mybatis.EmpMonOtherSalMapper;
import com.wage.biz.mybatis.EmpMonSalMapper;
import com.wage.biz.mybatis.HisSalChangeMapper;
import com.wage.contract.EmpMonSalHandler;
import com.wage.model.*;
import com.wage.model.dto.PerModleDto;
import com.wage.model.ext.EmpMonOtherSalExt;
import com.wage.model.ext.EmpMonSalAllExt;
import com.wage.model.ext.EmpMonSalExt;
import com.wage.model.ext.HisSalChangeExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * File：EmpMonSalHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class EmpMonSalHandlerImpl implements EmpMonSalHandler {

    @Autowired
    HisSalChangeMapper hisSalChangeMapper;

    @Autowired
    EmpMonSalMapper empMonSalMapper;

    @Autowired
    EmpMonOtherSalMapper empMonOtherSalMapper;

    @Autowired
    EmpMonDeductSalMapper empMonDeductSalMapper;

    /**
     * 根据员工编号查询
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisSalChangeExt> getByEmpId(PageInfo page,Integer empId){
        PageHelper.startPage(page);
        List<HisSalChangeExt> list = hisSalChangeMapper.getByEmpId(empId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获得近6个月总额
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getTotal(Integer empId,Integer changeTime){
        Map<String,Object> result = new HashMap<>();
        result.put("month",hisSalChangeMapper.getMonth(empId));
        result.put("total",hisSalChangeMapper.getTotal(empId));
        result.put("other",hisSalChangeMapper.getOther(empId));
        result.put("basic",hisSalChangeMapper.getBasicSal(empId));
        result.put("deduct",hisSalChangeMapper.getDeduct(empId));
        //获得6个月内三种工资占比
        List<PerModleDto> list = new ArrayList<>();
        PerModleDto basic = new PerModleDto();
        PerModleDto other = new PerModleDto();
        PerModleDto deduct = new PerModleDto();
        BigDecimal totalBasic = hisSalChangeMapper.getTotalBasic(empId);
        totalBasic = totalBasic== null ? new BigDecimal(0) : totalBasic;
        BigDecimal totalDeduct = hisSalChangeMapper.getTotalDeduct(empId);
        totalDeduct = totalDeduct== null ? new BigDecimal(0) : totalDeduct;
        BigDecimal totalOther = hisSalChangeMapper.getTotalOther(empId);
        totalOther = totalOther== null ? new BigDecimal(0) : totalOther;
        BigDecimal total = totalBasic.add(totalDeduct.add(totalOther)).setScale(1);
        total = total.compareTo(new BigDecimal(0))==0 ? new BigDecimal(1) : total;
        BigDecimal hun = new BigDecimal(100);
        basic.setName("固定工资");
        basic.setY(totalBasic.divide(total,3,ROUND_HALF_DOWN).multiply(hun).setScale(1));
        other.setName("额外工资");
        other.setY(totalOther.divide(total,3,ROUND_HALF_DOWN).multiply(hun).setScale(1));
        deduct.setName("扣除工资");
        deduct.setY(totalDeduct.divide(total,3,ROUND_HALF_DOWN).multiply(hun).setScale(1));
        list.add(basic);
        list.add(other);
        list.add(deduct);
        result.put("totalAll",list);
        if(changeTime != null){
            //获得1个月内三种工资占比
            List<PerModleDto> list1 = new ArrayList<>();
            PerModleDto basic1 = new PerModleDto();
            PerModleDto other1 = new PerModleDto();
            PerModleDto deduct1 = new PerModleDto();
            BigDecimal totalBasic1 = hisSalChangeMapper.getBasicSalOne(empId,changeTime);
            totalBasic1 = totalBasic1 == null ? new BigDecimal(0) : totalBasic1;
            BigDecimal totalDeduct1 = hisSalChangeMapper.getDeductOne(empId,changeTime);
            totalDeduct1 = totalDeduct1 == null ? new BigDecimal(0) : totalDeduct1;
            BigDecimal totalOther1 = hisSalChangeMapper.getOtherOne(empId,changeTime);
            totalOther1 = totalOther1 == null ? new BigDecimal(0) : totalOther1;
            BigDecimal total1 = totalBasic1.add(totalDeduct1.add(totalOther1)).setScale(1);
            basic1.setName("固定工资");
            basic1.setY(totalBasic1.divide(total1,3,ROUND_HALF_DOWN).multiply(hun).setScale(1));
            other1.setName("额外工资");
            other1.setY(totalOther1.divide(total1,3,ROUND_HALF_DOWN).multiply(hun).setScale(1));
            deduct1.setName("扣除工资");
            deduct1.setY(totalDeduct1.divide(total1,3,ROUND_HALF_DOWN).multiply(hun).setScale(1));
            list1.add(basic1);
            list1.add(other1);
            list1.add(deduct1);
            result.put("totalOneAll",list1);
        }
        return result;
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
        PageHelper.startPage(page);
        List<EmpMonSalExt> list = empMonSalMapper.getBySearch(empName, empId, basicPayStart, basicPayEnd, jobSalaryStart, jobSalaryEnd, totalStart, totalEnd, sort, order);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新固定工资表
     * @param empMonSal
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateEmpMonSal(EmpMonSal empMonSal){
        empMonSalMapper.updateByPrimaryKey(empMonSal);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        hisSalChangeMapper.updateMonSalChange(empMonSal.getEmpId(),Integer.parseInt(sdf.format(new Date())));
    }

    /**
     * 查询额外工资
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpMonOtherSalExt> getOtherSal(PageInfo page,Integer empId,String sort,String order){
        PageHelper.startPage(page);
        List<EmpMonOtherSalExt> list = empMonOtherSalMapper.findOtherSal(empId, sort, order);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新一条额外工资表数据
     * @param empMonOtherSal
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateOtherSal(EmpMonOtherSalExt empMonOtherSal){
        empMonOtherSalMapper.updateByPrimaryKeySelective(empMonOtherSal);
        EmpMonDeductSal record = new EmpMonDeductSal();
        record.setDeductSal(empMonOtherSal.getDeductSal());
        record.setFineSalary(empMonOtherSal.getFineSalary());
        record.setRestSalary(empMonOtherSal.getRestSalary());
        record.setEmpId(empMonOtherSal.getEmpId());
        record.setYearMonth(empMonOtherSal.getYearMonth());
        empMonDeductSalMapper.updateByPrimaryKey(record);
    }

    /**
     * 更新其他工资表中的福利数据
     * @param empId
     * @author 何友池
     */
    @Override
    public void updateWelfareData(Integer empId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Integer yearMonth = Integer.parseInt(sdf.format(new Date()));
        EmpMonOtherSalKey key = new EmpMonOtherSalKey();
        key.setEmpId(empId);
        key.setYearMonth(yearMonth);
        if(empMonOtherSalMapper.selectByPrimaryKey(key) != null){
            empMonOtherSalMapper.updateWelfare(empId);
            empMonOtherSalMapper.updateOtherTotal(empId);
        }else {
            empMonOtherSalMapper.insertWelfare(empId);
        }
    }

    /**
     * 批量更新福利表数据到其他工资表中
     * @param list
     * @author 何友池
     */
    @Override
    public void updateWelfareDataList(List<Integer> list){
        empMonOtherSalMapper.insertEmpWelfareList(list);
        empMonOtherSalMapper.updateWelfareList(list);
        empMonOtherSalMapper.updateOtherTotalList(list);
    }

    /**
     * 更新所有当月福利表数据到其他工资表中
     * @author 何友池
     */
    @Override
    public void updateAllDataList(){
        List<EmpMonOtherSal> list = empMonOtherSalMapper.findAll();
        List<Integer> intList = new ArrayList<>();
        for (EmpMonOtherSal empMonOtherSal : list) {
            intList.add(empMonOtherSal.getEmpId());
        }
        empMonOtherSalMapper.insertEmpWelfareList(intList);
        empMonOtherSalMapper.updateWelfareList(intList);
        empMonOtherSalMapper.updateOtherTotalList(intList);
    }
    /**
     * 插入所有月工资表的数据到本月固定工资表中
     * @return
     * @author 何友池
     */
    @Override
    public void insertAllMonSal(){
        hisSalChangeMapper.insertAll();
    }

    /**
     * 根据员工编号查询各类型工资
     * @param empId
     * @param yearMonth
     * @return
     * @author 何友池
     */
    @Override
    public EmpMonSalAllExt getWageByEmpId(Integer empId,Integer yearMonth){
        System.out.println(empMonSalMapper.findWageByEmpId(empId, yearMonth));
        return empMonSalMapper.findWageByEmpId(empId, yearMonth);
    }
}
