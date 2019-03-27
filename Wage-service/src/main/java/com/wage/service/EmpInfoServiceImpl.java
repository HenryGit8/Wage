/**
 * EmpInfoServiceImpl.java 2017/12/25 11:17
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.wage.api.EmpInfoService;
import com.wage.contract.EmpInfoHandler;
import com.wage.model.EmpDicBasicInfo;
import com.wage.model.ext.EmpDicBasicInfoExt;
import com.wage.model.ext.EmpDicBasicInfoSExt;
import com.wage.model.ext.EmpDicBasicInfoTreeExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Map;

/**
 * File：EmpInfoServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class EmpInfoServiceImpl implements EmpInfoService {

    @Autowired
    EmpInfoHandler empInfoHandler;

    /**
     * 根据查询条件分页查询员工信息表
     * @param page
     * @param empDicBasicInfo
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicBasicInfoExt> getBySearch(PageInfo page, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order){
        return empInfoHandler.getBySearch(page,empDicBasicInfo, sort, order);
    }

    /**
     * 删除一条数据
     * @param empId
     * @author 何友池
     */
    @Override
    public void removeInfo(Integer empId){
        empInfoHandler.removeInfo(empId);
    }
    /**
     * 保存一条消息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @Override
    public void saveInfo(EmpDicBasicInfo empDicBasicInfo, BigDecimal empBasicPay, BigDecimal empJobSalary,String headMd){
        empInfoHandler.saveInfo(empDicBasicInfo, empBasicPay, empJobSalary, headMd);
    }
    /**
     * 修改人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @Override
    public void updateInfo(EmpDicBasicInfo empDicBasicInfo){
        empInfoHandler.updateInfo(empDicBasicInfo);
    }

    /**
     * 获取部门信息集合
     * @param departId
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getDepartMap(Integer departId){
        return empInfoHandler.getDepartMap(departId);
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
        return empInfoHandler.getEmpTree(page, empDicBasicInfo, sort, order);
    }

    /**
     * 修改登录人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @Override
    public void updateLoginInfo(EmpDicBasicInfo empDicBasicInfo){
        empInfoHandler.updateLoginInfo(empDicBasicInfo);
    }

    /**
     * 查询一条员工信息
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public EmpDicBasicInfo getOneInfo(Integer empId){
        return empInfoHandler.getOneInfo(empId);
    }
}
