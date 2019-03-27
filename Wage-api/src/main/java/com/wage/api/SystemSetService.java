/**
 * SystemSetService.java 2018/2/26 13:57
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.api;

import com.github.pagehelper.PageInfo;
import com.wage.model.*;
import com.wage.model.ext.EmpDicPasswordExt;
import com.wage.model.ext.HisLoginExt;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * File：SystemSetService.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public interface SystemSetService {
    /**
     * 获取系统设置
     * @return
     * @author 何友池
     */
    Map<String,Object> getAllSysSet();

    /**
     * 批量更新系统设置
     * @param list
     * @author 何友池
     */
    void updateSet(List<SysSet> list);
    /**
     * 更新至默认值
     * @return
     * @author 何友池
     */
    void updateToDefault();

    /**
     * 查询所有登录记录
     * @param page
     * @param empName
     * @param loginTimeStart
     * @param loginTimeEnd
     * @return
     * @author 何友池
     */
    PageInfo<HisLoginExt> getAllLoginHis(PageInfo page, String empName, Date loginTimeStart, Date loginTimeEnd);
    /**
     * 查询所有额外假期数据
     * @param page
     * @return
     * @author 何友池
     */
    PageInfo<SysHoliday> getAllHoliday(PageInfo page);
    /**
     * 删除一条假期
     * @param dateTime
     * @author 何友池
     */
    void deleteHoliday(Date dateTime);

    /**
     * 插入一条假期信息
     * @param record
     * @author 何友池
     */
    void saveHoliday(SysHoliday record);

    /**
     * 获取所有部门数据
     * @return
     * @author 何友池
     */
    PageInfo<EmpDepartment> getAllDepart();
    /**
     * 删除一条部门信息
     * @param key
     * @author 何友池
     */
    void deleteEmpDepart(EmpDepartmentKey key);
    /**
     * 保存一条部门数据
     * @param record
     * @return
     * @author 何友池
     */
    Integer saveEmpDepart(EmpDepartment record);
    /**
     * 更新部门薪资
     * @param record
     * @author 何友池
     */
    void updateDepart(EmpDepartment record);
    /**
     * 任命为管理员
     * @param empId
     * @author 何友池
     */
    void updateAddAdmin(Integer empId);

    /**
     * 撤销任命
     * @param empId
     * @author 何友池
     */
    void updateRemoveAdmin(Integer empId);
    /**
     * 获取登录者登录记录
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    PageInfo<HisLoginExt> getLoginHisByEmpId(PageInfo page, Integer empId);
    /**
     * 获取所有滚轮图数据
     * @return
     * @author 何友池
     */
    List<SysEmpNewSet> getAllNewImg();

    /**
     * 获取所有非滚轮图数据
     * @return
     * @author 何友池
     */
    List<SysEmpNewSet> getAllNewNoImg();

    /**
     * 更新员工密码
     * @param empId
     * @param password
     * @author 何友池
     */
    void updateEmpPass(Integer empId,String password);
    /**
     * 查询已注册用户
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    PageInfo<EmpDicPasswordExt> getIsRegister(PageInfo page, Integer empId,String empName);
    /**
     * 保存所有推荐新闻
     * @param list
     * @author 何友池
     */
    void saveAllNewNoImg(List<SysEmpNewSet> list);

    /**
     * 保存全部新闻滚轮图列表
     * @param list
     * @author 何友池
     */
    void saveAllNewImg(List<SysEmpNewSet> list);
}
