/**
 * EmpInfoHandlerImpl.java 2017/12/25 11:18
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.biz.mybatis.*;
import com.wage.contract.EmpInfoHandler;
import com.wage.model.*;
import com.wage.model.ext.EmpDepartmentExt;
import com.wage.model.ext.EmpDicBasicInfoExt;
import com.wage.model.ext.EmpDicBasicInfoSExt;
import com.wage.model.ext.EmpDicBasicInfoTreeExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * File：EmpInfoHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class EmpInfoHandlerImpl implements EmpInfoHandler {

    @Autowired
    EmpDicBasicInfoMapper empDicBasicInfoMapper;

    @Autowired
    EmpMonSalMapper empMonSalMapper;

    @Autowired
    EmpDepartmentMapper empDepartmentMapper;

    @Autowired
    EmpDicPasswordMapper empDicPasswordMapper;

    @Autowired
    EmpMonDeductSalMapper empMonDeductSalMapper;

    @Autowired
    EmpMonOtherSalMapper empMonOtherSalMapper;

    @Autowired
    HisSalaryMapper hisSalaryMapper;

    @Autowired
    HisWelfareMapper hisWelfareMapper;

    @Autowired
    EmpWelfareMapper empWelfareMapper;

    @Autowired
    HisSalChangeMapper hisSalChangeMapper;

    @Autowired
    EmpImgMapper empImgMapper;

    /**
     * 根据查询条件分页查询员工信息表
     * @param page
     * @param empDicBasicInfo
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicBasicInfoExt> getBySearch(PageInfo page, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order){
        PageHelper.startPage(page);
        List<EmpDicBasicInfoExt> list = empDicBasicInfoMapper.getBySearch(empDicBasicInfo, sort, order);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 删除一条数据
     * @param empId
     * @author 何友池
     */
    @Override
    @Transactional
    public void removeInfo(Integer empId){
        empDicBasicInfoMapper.deleteByPrimaryKey(empId);
        empDicPasswordMapper.deleteByPrimaryKey(empId);
        EmpMonDeductSalKey key = new EmpMonDeductSalKey();
        key.setEmpId(empId);
        empMonDeductSalMapper.deleteByPrimaryKey(key);
        EmpMonOtherSalKey key1 = new EmpMonOtherSalKey();
        key1.setEmpId(empId);
        empMonOtherSalMapper.deleteByPrimaryKey(key1);
        empMonSalMapper.deleteByPrimaryKey(empId);
        HisSalaryKey key2 = new HisSalaryKey();
        key2.setEmpId(empId);
        hisSalaryMapper.deleteByPrimaryKey(key2);
        hisWelfareMapper.deleteByEmpId(empId);
        empWelfareMapper.deleteByEmpId(empId);
        HisSalChangeKey key3 = new HisSalChangeKey();
        key3.setEmpId(empId);
        hisSalChangeMapper.deleteByPrimaryKey(key3);
        empImgMapper.deleteByPrimaryKey(empId);
    }

    /**
     * 保存一条消息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @Override
    @Transactional
    public void saveInfo(EmpDicBasicInfo empDicBasicInfo, BigDecimal empBasicPay, BigDecimal empJobSalary,String headMd){
        empDicBasicInfoMapper.insert(empDicBasicInfo);
        EmpDepartmentKey key = new EmpDepartmentKey();
        key.setDepartId(empDicBasicInfo.getDepartId());
        key.setDepartRand(empDicBasicInfo.getDepartRand());
        EmpDepartment empDepartment = empDepartmentMapper.selectByPrimaryKey(key);
        EmpMonSal record = new EmpMonSal();
        record.setEmpId(empDicBasicInfoMapper.selectMaxId());
        record.setEmpBasicPay(empBasicPay);
        record.setEmpJobSalary(empDepartment.getDepartWage());
        record.setMonSal(empBasicPay.add(empDepartment.getDepartWage()));
        empMonSalMapper.insert(record);
        if(headMd != null){
            Integer empId = empDicBasicInfoMapper.findMaxId();
            EmpImg record1 = new EmpImg();
            record1.setEmpId(empId);
            record1.setEmpHeadImg(headMd);
            empImgMapper.insert(record1);
        }
    }

    /**
     * 修改人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @Override
    public void updateInfo(EmpDicBasicInfo empDicBasicInfo){
        empDicBasicInfoMapper.updateByPrimaryKey(empDicBasicInfo);
    }

    /**
     * 获取部门信息集合
     * @param departId
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getDepartMap(Integer departId){
        Map<String,Object> result = new HashMap<>();
        List<EmpDepartment> departList = empDepartmentMapper.findDepartList();
        List<Short> randIdList = empDepartmentMapper.findRandById(departId);
        List<String> departNameList = new ArrayList<>();
        List<Integer> departIdList = new ArrayList<>();
        for (int i = 0; i < departList.size(); i++) {
            String s =  departList.get(i).getDepartName();
            Integer in = departList.get(i).getDepartId();
            departNameList.add(i,s);
            departIdList.add(i,in);
        }
        result.put("randIdList",randIdList);
        result.put("departNameList",departNameList);
        result.put("departIdList",departIdList);
        return result;
    }

    /**
     * 获取树形员工信息集合
     * @param page
     * @param empDicBasicInfo
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicBasicInfoTreeExt> getEmpTree(PageInfo page, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order){
        List<EmpDepartment> departList = empDepartmentMapper.findDepartList();
        List<EmpDicBasicInfoExt> list = empDicBasicInfoMapper.getBySearch(empDicBasicInfo, sort, order);
        List<EmpDicBasicInfoTreeExt> treeList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EmpDicBasicInfoTreeExt listt = new EmpDicBasicInfoTreeExt();
            listt.setEmpId(list.get(i).getEmpId());
            listt.setEmpName(list.get(i).getEmpName());
            listt.setDepartRand(list.get(i).getDepartRand());
            listt.set_parentId(list.get(i).getDepartId().toString());
            treeList.add(i,listt);
        }
        for (EmpDepartment empDepartment : departList) {
            EmpDicBasicInfoTreeExt listt = new EmpDicBasicInfoTreeExt();
            listt.setEmpId(empDepartment.getDepartId());
            listt.setEmpName(empDepartment.getDepartName());
            treeList.add(listt);
        }
        PageInfo pageInfo = new PageInfo(treeList);
        return pageInfo;
    }

    /**
     * 修改登录人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @Override
    public void updateLoginInfo(EmpDicBasicInfo empDicBasicInfo){
        empDicBasicInfoMapper.updateByPrimaryKeySelective(empDicBasicInfo);
    }

    /**
     * 查询一条员工信息
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public EmpDicBasicInfo getOneInfo(Integer empId){
        return empDicBasicInfoMapper.selectByPrimaryKey(empId);
    }
}
