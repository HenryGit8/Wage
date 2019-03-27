/**
 * EmpInfoHandler.java 2017/12/25 11:16
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.wage.api.EmpInfoService;
import com.wage.model.EmpDicBasicInfo;
import com.wage.model.PageRequest;
import com.wage.model.ext.EmpDicBasicInfoExt;
import com.wage.model.ext.EmpDicBasicInfoSExt;
import com.wage.model.ext.EmpDicBasicInfoTreeExt;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.PageUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * File：EmpInfoHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class EmpInfoHandler {

    @Reference
    EmpInfoService empInfoService;

    /**
     * 根据查询条件分页查询员工信息表
     * @param pageRequest
     * @param empDicBasicInfo
     * @return
     * @author 何友池
     */
    public PageInfo<EmpDicBasicInfoExt> getBySearch(PageRequest pageRequest, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order){
        PageInfo page = PageUtil.getPage(pageRequest);
        return empInfoService.getBySearch(page,empDicBasicInfo,sort,order);
    }

    /**
     * 删除一条数据
     * @param empId
     * @author 何友池
     */
    public void removeInfo(Integer empId){
        empInfoService.removeInfo(empId);
    }
    /**
     * 保存一条消息
     * @param empDicBasicInfo
     * @author 何友池
     */
    public void saveInfo(EmpDicBasicInfo empDicBasicInfo, BigDecimal empBasicPay, BigDecimal empJobSalary,String headMd){
        empDicBasicInfo.setCreateEmpid(ContextHolder.getSessionEmp().getEmpId());
        empDicBasicInfo.setModifyEmpid(ContextHolder.getSessionEmp().getEmpId());
        empInfoService.saveInfo(empDicBasicInfo, empBasicPay, empJobSalary, headMd);
    }
    /**
     * 修改人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    public void updateInfo(EmpDicBasicInfo empDicBasicInfo){
        empDicBasicInfo.setModifyEmpid(ContextHolder.getSessionEmp().getEmpId());
        empInfoService.updateInfo(empDicBasicInfo);
    }
    /**
     * 获取部门信息集合
     * @param departId
     * @return
     * @author 何友池
     */
    public Map<String,Object> getDepartMap(Integer departId){
        return empInfoService.getDepartMap(departId);
    }
    /**
     * 获取树形员工信息集合
     * @param pageRequest
     * @param empDicBasicInfo
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    public PageInfo<EmpDicBasicInfoTreeExt> getEmpTree(PageRequest pageRequest, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order){
        PageInfo page = PageUtil.getPage(pageRequest);
        return empInfoService.getEmpTree(page, empDicBasicInfo, sort, order);
    }
    /**
     * 修改当前登录人员人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    public void updateLoginInfo(EmpDicBasicInfo empDicBasicInfo){
        empDicBasicInfo.setModifyEmpid(ContextHolder.getSessionEmp().getEmpId());
        empDicBasicInfo.setEmpId(ContextHolder.getSessionEmp().getEmpId());
        empInfoService.updateLoginInfo(empDicBasicInfo);
    }

    /**
     * 获取登录者信息
     * @param empId
     * @return
     * @author 何友池
     */
    public EmpDicBasicInfo getLoginInfo(Integer empId){
        return empInfoService.getOneInfo(ContextHolder.getSessionEmp().getEmpId());
    }

}
