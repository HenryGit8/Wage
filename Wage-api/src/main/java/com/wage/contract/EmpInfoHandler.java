/**
 * EmpInfoHandler.java 2017/12/25 11:18
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.contract;

import com.github.pagehelper.PageInfo;
import com.wage.model.EmpDicBasicInfo;
import com.wage.model.ext.EmpDicBasicInfoExt;
import com.wage.model.ext.EmpDicBasicInfoSExt;
import com.wage.model.ext.EmpDicBasicInfoTreeExt;

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
public interface EmpInfoHandler {

    /**
     * 根据查询条件分页查询员工信息表
     * @param page
     * @param empDicBasicInfo
     * @return
     * @author 何友池
     */
    PageInfo<EmpDicBasicInfoExt> getBySearch(PageInfo page, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order);
    /**
     * 删除一条数据
     * @param empId
     * @author 何友池
     */
    void removeInfo(Integer empId);
    /**
     * 保存一条消息
     * @param empDicBasicInfo
     * @author 何友池
     */
    void saveInfo(EmpDicBasicInfo empDicBasicInfo, BigDecimal empBasicPay, BigDecimal empJobSalary,String headMd);
    /**
     * 修改人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    void updateInfo(EmpDicBasicInfo empDicBasicInfo);
    /**
     * 获取部门信息集合
     * @param departId
     * @return
     * @author 何友池
     */
    Map<String,Object> getDepartMap(Integer departId);
    /**
     * 获取树形员工信息集合
     * @param page
     * @param empDicBasicInfo
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    PageInfo<EmpDicBasicInfoTreeExt> getEmpTree(PageInfo page, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order);

    /**
     * 修改登录人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    void updateLoginInfo(EmpDicBasicInfo empDicBasicInfo);
    /**
     * 查询一条员工信息
     * @param empId
     * @return
     * @author 何友池
     */
    EmpDicBasicInfo getOneInfo(Integer empId);
}
