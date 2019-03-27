package com.wage.biz.mybatis;


import com.wage.model.HisWelfare;
import com.wage.model.ext.HisWelfareExt;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface HisWelfareMapper {
    /**
     *
     * @mbggenerated 2018-01-24
     */
    int deleteByPrimaryKey(Long serialId);

    /**
     *
     * @mbggenerated 2018-01-24
     */
    int insert(HisWelfare record);

    /**
     *
     * @mbggenerated 2018-01-24
     */
    int insertSelective(HisWelfare record);

    /**
     *
     * @mbggenerated 2018-01-24
     */
    HisWelfare selectByPrimaryKey(Long serialId);

    /**
     *
     * @mbggenerated 2018-01-24
     */
    int updateByPrimaryKeySelective(HisWelfare record);

    /**
     *
     * @mbggenerated 2018-01-24
     */
    int updateByPrimaryKey(HisWelfare record);

    /**
     * 根据查询条件查询福利表
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
    List<HisWelfareExt> findBySearch(@Param("empId") Integer empId, @Param("applyTimeMin") Date applyTimeMin, @Param("applyTimeMax") Date applyTimeMax,
                                     @Param("isAllow") Short isAllow, @Param("welfareType") Short welfareType, @Param("welfareTotalMin") BigDecimal welfareTotalMin,
                                     @Param("welfareTotalMax") BigDecimal welfareTotalMax,  @Param("sort") String sort, @Param("order") String order);

    /**
     * 插入一条福利数据
     * @param record
     * @return
     * @author 何友池
     */
    int insertWelfare(HisWelfare record);

    /**
     * 插入多条福利数据
     * @param list
     * @return
     * @author 何友池
     */
    int insertWelfareList(List<HisWelfare> list);

    /**
     * 删除此员工未审批信息（离职）
     * @param empId
     * @return
     * @author 何友池
     */
    int deleteByEmpId(@Param("empId") Integer empId);

}