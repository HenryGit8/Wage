/**
 * WelfareHandler.java 2018/1/24 14:21
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.contract;

import com.github.pagehelper.PageInfo;
import com.wage.model.EmpWelfare;
import com.wage.model.HisWelfare;
import com.wage.model.ext.EmpWelfareExt;
import com.wage.model.ext.HisWelfareExt;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * File：WelfareHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public interface WelfareHandler {
    /**
     * 根据查询条件查询福利表
     * @param page
     * @param empId
     * @param applyTimeMin
     * @param applyTimeMax
     * @param isAllow
     * @param welfareType
     * @param welfareTotalMin
     * @param welfareTotalMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    PageInfo<HisWelfareExt> getBySearch(PageInfo page, Integer empId, Date applyTimeMin, Date applyTimeMax, Short isAllow, Short welfareType,
                                               BigDecimal welfareTotalMin, BigDecimal welfareTotalMax, String sort, String order);

    /**
     * 更新一条福利表数据
     * @param serialId
     * @param isAllow
     * @author 何友池
     */
    Integer updateWelfare(Long serialId,Short isAllow,Integer empId);
    /**
     * 插入一条福利数据
     * @param record
     * @author 何友池
     */
    void saveWelfare(HisWelfare record);
    /**
     * 插入多条福利数据
     * @param list
     * @return
     * @author 何友池
     */
    void saveWelfareList(List<HisWelfare> list);
    /**
     * 根据条件查找员工福利记录
     * @param page
     * @param empId
     * @param type
     * @param effectiveDateMin
     * @param effectiveDateMax
     * @param moneyMin
     * @param moneyMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    PageInfo<EmpWelfareExt> getEmpWelBySearch(PageInfo page, Integer empId, String type, Date effectiveDateMin, Date effectiveDateMax,
                                               BigDecimal moneyMin, BigDecimal moneyMax, String sort, String order);
    /**
     * 更新一条员工福利记录
     * @param record
     * @author 何友池
     */
    void updateEmpWelfare(EmpWelfare record);

    /**
     * 插入一条员工福利记录
     * @param record
     * @return
     * @author 何友池
     */
    void saveEmpWelfare(EmpWelfare record);

    /**
     * 插入多条员工福利记录
     * @param list
     * @return
     * @author 何友池
     */
    void saveEmpWelfareList(List<EmpWelfare> list);
    /**
     * 删除一条员工福利记录
     * @param id
     * @author 何友池
     */
    void removeEmpWelfare(Long id);

    /**
     * 根据流水号查询员工福利数据
     * @param id
     * @return
     * @author 何友池
     */
    EmpWelfare getById(Long id);
}
