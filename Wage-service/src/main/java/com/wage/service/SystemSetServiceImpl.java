/**
 * SystemSetServiceImpl.java 2018/2/26 13:57
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.api.SystemSetService;
import com.wage.contract.SystemSetHandler;
import com.wage.model.*;
import com.wage.model.ext.EmpDicPasswordExt;
import com.wage.model.ext.HisLoginExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * File：SystemSetServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class SystemSetServiceImpl implements SystemSetService {

    @Autowired
    SystemSetHandler systemSetHandler;

    /**
     * 获取系统设置
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getAllSysSet(){
        return systemSetHandler.getAllSysSet();
    }

    /**
     * 批量更新系统设置
     * @param list
     * @author 何友池
     */
    @Override
    public void updateSet(List<SysSet> list){
        systemSetHandler.updateSet(list);
    }

    /**
     * 更新至默认值
     * @return
     * @author 何友池
     */
    @Override
    public void updateToDefault(){
        systemSetHandler.updateToDefault();
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
        return systemSetHandler.getAllLoginHis(page, empName, loginTimeStart, loginTimeEnd);
    }
    /**
     * 查询所有额外假期数据
     * @param page
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<SysHoliday> getAllHoliday(PageInfo page){
        return systemSetHandler.getAllHoliday(page);
    }

    /**
     * 删除一条假期
     * @param dateTime
     * @author 何友池
     */
    @Override
    public void deleteHoliday(Date dateTime){
        systemSetHandler.deleteHoliday(dateTime);
    }

    /**
     * 插入一条假期信息
     * @param record
     * @author 何友池
     */
    @Override
    public void saveHoliday(SysHoliday record){
        systemSetHandler.saveHoliday(record);
    }

    /**
     * 获取所有部门数据
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDepartment> getAllDepart(){
        return systemSetHandler.getAllDepart();
    }

    /**
     * 删除一条部门信息
     * @param key
     * @author 何友池
     */
    @Override
    public void deleteEmpDepart(EmpDepartmentKey key){
        systemSetHandler.deleteEmpDepart(key);
    }

    /**
     * 保存一条部门数据
     * @param record
     * @return
     * @author 何友池
     */
    @Override
    public Integer saveEmpDepart(EmpDepartment record){
        return systemSetHandler.saveEmpDepart(record);
    }

    /**
     * 更新部门薪资
     * @param record
     * @author 何友池
     */
    @Override
    public void updateDepart(EmpDepartment record){
        systemSetHandler.updateDepart(record);
    }

    /**
     * 任命为管理员
     * @param empId
     * @author 何友池
     */
    @Override
    public void updateAddAdmin(Integer empId){
        systemSetHandler.updateAddAdmin(empId);
    }

    /**
     * 撤销任命管理员
     * @param empId
     * @author 何友池
     */
    @Override
    public void updateRemoveAdmin(Integer empId){
        systemSetHandler.updateRemoveAdmin(empId);
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
        return systemSetHandler.getLoginHisByEmpId(page, empId);
    }

    /**
     * 获取所有滚轮图数据
     * @return
     * @author 何友池
     */
    @Override
    public List<SysEmpNewSet> getAllNewImg(){
        return systemSetHandler.getAllNewImg();
    }

    /**
     * 获取所有非滚轮图数据
     * @return
     * @author 何友池
     */
    @Override
    public List<SysEmpNewSet> getAllNewNoImg(){
        return systemSetHandler.getAllNewNoImg();
    }

    /**
     * 更新员工密码
     * @param empId
     * @param password
     * @author 何友池
     */
    @Override
    public void updateEmpPass(Integer empId,String password){
        systemSetHandler.updateEmpPass(empId, password);
    }

    /**
     * 查询已注册用户
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicPasswordExt> getIsRegister(PageInfo page, Integer empId,String empName){
        return systemSetHandler.getIsRegister(page, empId, empName);
    }
    /**
     * 保存所有推荐新闻
     * @param list
     * @author 何友池
     */
    @Override
    public void saveAllNewNoImg(List<SysEmpNewSet> list){
        systemSetHandler.saveAllNewNoImg(list);
    }
    /**
     * 保存全部新闻滚轮图列表
     * @param list
     * @author 何友池
     */
    @Override
    public void saveAllNewImg(List<SysEmpNewSet> list){
        systemSetHandler.saveAllNewImg(list);
    }
}
