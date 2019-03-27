/**
 * SystemSetHandler.java 2018/2/26 13:56
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.wage.api.SystemSetService;
import com.wage.model.*;
import com.wage.model.ext.EmpDicPasswordExt;
import com.wage.model.ext.HisLoginExt;
import com.wage.util.MD5;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.PageUtil;
import com.wage.web.constant.ParamConstants;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * File：SystemSetHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class SystemSetHandler {

    @Reference
    SystemSetService systemSetService;

    /**
     * 获取系统设置
     * @return
     * @author 何友池
     */
    public Map<String,Object> getAllSysSet(){
        return systemSetService.getAllSysSet();
    }

    /**
     * 批量更新系统设置
     * @param list
     * @author 何友池
     */
    public void updateSet(List<SysSet> list){
        systemSetService.updateSet(list);
    }

    /**
     * 更新至默认值
     * @return
     * @author 何友池
     */
    public void updateToDefault(){
        systemSetService.updateToDefault();
    }

    /**
     * 查询所有登录记录
     * @param pageRequest
     * @param empName
     * @param loginTimeStart
     * @param loginTimeEnd
     * @return
     * @author 何友池
     */
    public PageInfo<HisLoginExt> getAllLoginHis(PageRequest pageRequest, String empName, Date loginTimeStart, Date loginTimeEnd){
        PageInfo page = PageUtil.getPage(pageRequest);
        return systemSetService.getAllLoginHis(page, empName, loginTimeStart, loginTimeEnd);
    }
    /**
     * 查询所有额外假期数据
     * @param pageRequest
     * @return
     * @author 何友池
     */
    public PageInfo<SysHoliday> getAllHoliday(PageRequest pageRequest){
        PageInfo page = PageUtil.getPage(pageRequest);
        return systemSetService.getAllHoliday(page);
    }
    /**
     * 删除一条假期
     * @param dateTime
     * @author 何友池
     */
    public void deleteHoliday(Date dateTime){
        systemSetService.deleteHoliday(dateTime);
    }

    /**
     * 插入一条假期信息
     * @param record
     * @author 何友池
     */
    public void saveHoliday(SysHoliday record){
        systemSetService.saveHoliday(record);
    }

    /**
     * 获取所有部门数据
     * @return
     * @author 何友池
     */
    public PageInfo<EmpDepartment> getAllDepart(){
        return systemSetService.getAllDepart();
    }

    /**
     * 删除一条部门信息
     * @param key
     * @author 何友池
     */
    public void deleteEmpDepart(EmpDepartmentKey key){
        systemSetService.deleteEmpDepart(key);
    }

    /**
     * 保存一条部门数据
     * @param record
     * @return
     * @author 何友池
     */
    public Integer saveEmpDepart(EmpDepartment record){
        return systemSetService.saveEmpDepart(record);
    }

    /**
     * 更新部门薪资
     * @param record
     * @author 何友池
     */
    public void updateDepart(EmpDepartment record){
        systemSetService.updateDepart(record);
    }

    /**
     * 任命为管理员
     * @param empId
     * @author 何友池
     */
    public void updateAddAdmin(Integer empId){
        systemSetService.updateAddAdmin(empId);
    }

    /**
     * 撤销任命管理员
     * @param empId
     * @author 何友池
     */
    public void updateRemoveAdmin(Integer empId){
        systemSetService.updateRemoveAdmin(empId);
    }
    /**
     * 获取登录者登录记录
     * @param pageRequest
     * @param empId
     * @return
     * @author 何友池
     */
    public PageInfo<HisLoginExt> getLoginHisByEmpId(PageRequest pageRequest, Integer empId){
        PageInfo page = PageUtil.getPage(pageRequest);
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return systemSetService.getLoginHisByEmpId(page, empId);
    }

    /**
     * 获取所有滚轮图数据
     * @return
     * @author 何友池
     */
    public List<SysEmpNewSet> getAllNewImg(){
        return systemSetService.getAllNewImg();
    }

    /**
     * 获取所有非滚轮图数据
     * @return
     * @author 何友池
     */
    public List<SysEmpNewSet> getAllNewNoImg(){
        return systemSetService.getAllNewNoImg();
    }

    /**
     * 重置员工密码默认123456
     * @param empId
     * @author 何友池
     */
    public String updateEmpPass(Integer empId){
        String pass ="mm"+(int)((Math.random()*9+1)*1000);
        systemSetService.updateEmpPass(empId, MD5.getMD5(pass));
        return pass;
    }

    /**
     * 查询已注册用户
     * @param pageRequest
     * @return
     * @author 何友池
     */
    public PageInfo<EmpDicPasswordExt> getIsRegister(PageRequest pageRequest,String empName){
        PageInfo page = PageUtil.getPage(pageRequest);
        return systemSetService.getIsRegister(page, ContextHolder.getSessionEmp().getEmpId(), empName);
    }

    /**
     * 获取所有非图片新闻列表
     * @return
     * @author 何友池
     */
    public PageInfo<SysEmpNewSet> getAllNewsList(){
        PageInfo pageInfo = new PageInfo(systemSetService.getAllNewNoImg());
        return pageInfo;
    }

    /**
     * 获取所有图片新闻列表
     * @return
     * @author 何友池
     */
    public PageInfo<SysEmpNewSet> getAllNewsImgList(){
        PageInfo pageInfo = new PageInfo(systemSetService.getAllNewImg());
        return pageInfo;
    }
    /**
     * 保存所有推荐新闻
     * @param list
     * @author 何友池
     */
    public void saveAllNewNoImg(List<SysEmpNewSet> list){
        systemSetService.saveAllNewNoImg(list);
    }
    /**
     * 保存全部新闻滚轮图列表
     * @param list
     * @author 何友池
     */
    public void saveAllNewImg(List<SysEmpNewSet> list){
        systemSetService.saveAllNewImg(list);
    }
}
