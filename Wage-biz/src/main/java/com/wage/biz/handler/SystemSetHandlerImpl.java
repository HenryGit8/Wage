/**
 * SystemSetHandlerImpl.java 2018/2/26 13:57
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.biz.mybatis.*;
import com.wage.contract.SystemSetHandler;
import com.wage.model.*;
import com.wage.model.ext.EmpDicPasswordExt;
import com.wage.model.ext.HisLoginExt;
import com.wage.model.ext.HisSalaryExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File：SystemSetHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class SystemSetHandlerImpl implements SystemSetHandler {

    @Autowired
    SysSetMapper sysSetMapper;

    @Autowired
    HisLoginMapper hisLoginMapper;

    @Autowired
    SysHolidayMapper sysHolidayMapper;

    @Autowired
    EmpDepartmentMapper empDepartmentMapper;

    @Autowired
    EmpDicPasswordMapper empDicPasswordMapper;

    @Autowired
    SysAuthMapper sysAuthMapper;

    @Autowired
    SysEmpNewSetMapper sysEmpNewSetMapper;

    /**
     * 获取系统设置
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getAllSysSet(){
        Map<String,Object> result = new HashMap<>();
        List<SysSet> list = sysSetMapper.findAll();
        for (SysSet sysSet : list) {
            result.put(sysSet.getSetName(),sysSet.getSetValue());
        }
        return result;
    }

    /**
     * 批量更新系统设置
     * @param list
     * @author 何友池
     */
    @Override
    public void updateSet(List<SysSet> list){
        sysSetMapper.updateSet(list);
    }

    /**
     * 更新至默认值
     * @return
     * @author 何友池
     */
    @Override
    public void updateToDefault(){
        sysSetMapper.updateToDefault();
    }

    /**
     * 查询所有登录记录
     * @param page
     * @param empName
     * @param loginTimeStart
     * @param loginTimeEnd
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisLoginExt> getAllLoginHis(PageInfo page, String empName, Date loginTimeStart, Date loginTimeEnd){
        PageHelper.startPage(page);
        List<HisLoginExt> list = hisLoginMapper.findAllLoginHis(empName, loginTimeStart, loginTimeEnd);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询所有额外假期数据
     * @param page
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<SysHoliday> getAllHoliday(PageInfo page){
        PageHelper.startPage(page);
        List<SysHoliday> list = sysHolidayMapper.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 删除一条假期
     * @param dateTime
     * @author 何友池
     */
    @Override
    public void deleteHoliday(Date dateTime){
        sysHolidayMapper.deleteByPrimaryKey(dateTime);
    }

    /**
     * 插入一条假期信息
     * @param record
     * @author 何友池
     */
    @Override
    public void saveHoliday(SysHoliday record){
        sysHolidayMapper.insert(record);
    }

    /**
     * 获取所有部门数据
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDepartment> getAllDepart(){
        List<EmpDepartment> list = empDepartmentMapper.findAllDepart();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 删除一条部门信息
     * @param key
     * @author 何友池
     */
    @Override
    public void deleteEmpDepart(EmpDepartmentKey key){
        empDepartmentMapper.deleteByPrimaryKey(key);
    }

    /**
     * 保存一条部门数据
     * @param record
     * @return
     * @author 何友池
     */
    @Override
    public Integer saveEmpDepart(EmpDepartment record){
        Integer result = 0;
        if(empDepartmentMapper.selectByPrimaryKey(record) != null){
            result = 1;
        }else{
            empDepartmentMapper.insert(record);
        }
        return result;
    }

    /**
     * 更新部门薪资
     * @param record
     * @author 何友池
     */
    @Override
    public void updateDepart(EmpDepartment record){
        empDepartmentMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 任命为管理员
     * @param empId
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateAddAdmin(Integer empId){
        EmpDicPassword record = new EmpDicPassword();
        record.setEmpId(empId);
        record.setIsAdmin((short)1);
        empDicPasswordMapper.updateByPrimaryKeySelective(record);
        SysAuth record1 = new SysAuth();
        record1.setEmpId(empId);
        sysAuthMapper.insert(record1);
    }

    /**
     * 撤销任命管理员
     * @param empId
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateRemoveAdmin(Integer empId){
        EmpDicPassword record = new EmpDicPassword();
        record.setEmpId(empId);
        record.setIsAdmin((short)0);
        empDicPasswordMapper.updateByPrimaryKeySelective(record);
        sysAuthMapper.deleteByPrimaryKey(empId);
    }

    /**
     * 获取登录者登录记录
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisLoginExt> getLoginHisByEmpId(PageInfo page, Integer empId){
        PageHelper.startPage(page);
        List<HisLoginExt> list = hisLoginMapper.findLoginHisByEmpId(empId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取所有滚轮图数据
     * @return
     * @author 何友池
     */
    @Override
    public List<SysEmpNewSet> getAllNewImg(){
        return sysEmpNewSetMapper.findAllNewImg();
    }

    /**
     * 获取所有非滚轮图数据
     * @return
     * @author 何友池
     */
    @Override
    public List<SysEmpNewSet> getAllNewNoImg(){
        return sysEmpNewSetMapper.findAllNewNoImg();
    }

    /**
     * 更新员工密码
     * @param empId
     * @param password
     * @author 何友池
     */
    @Override
    public void updateEmpPass(Integer empId,String password){
        EmpDicPassword record = new EmpDicPassword();
        record.setEmpId(empId);
        record.setPassword(password);
        empDicPasswordMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询已注册用户
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicPasswordExt> getIsRegister(PageInfo page,Integer empId,String empName){
        PageHelper.startPage(page);
        List<EmpDicPasswordExt> list = empDicPasswordMapper.findIsRegister(empId,empName);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 保存所有推荐新闻
     * @param list
     * @author 何友池
     */
    @Override
    @Transactional
    public void saveAllNewNoImg(List<SysEmpNewSet> list){
        sysEmpNewSetMapper.removeAllNewNoImg();
        sysEmpNewSetMapper.saveAllNewNoImg(list);
    }

    /**
     * 保存全部新闻滚轮图列表
     * @param list
     * @author 何友池
     */
    @Override
    @Transactional
    public void saveAllNewImg(List<SysEmpNewSet> list){
        sysEmpNewSetMapper.removeAllNewImg();
        sysEmpNewSetMapper.saveAllNewImg(list);
    }
}
