package com.wage.biz.mybatis;


import com.wage.model.HisRest;
import com.wage.model.HisRestKey;
import com.wage.model.ext.HisRestNExt;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HisRestMapper {
    /**
     *
     * @mbggenerated 2018-01-10
     */
    int deleteByPrimaryKey(HisRestKey key);

    /**
     *
     * @mbggenerated 2018-01-10
     */
    int insert(HisRest record);

    /**
     *
     * @mbggenerated 2018-01-10
     */
    int insertSelective(HisRest record);

    /**
     *
     * @mbggenerated 2018-01-10
     */
    HisRest selectByPrimaryKey(HisRestKey key);

    /**
     *
     * @mbggenerated 2018-01-10
     */
    int updateByPrimaryKeySelective(HisRest record);

    /**
     *
     * @mbggenerated 2018-01-10
     */
    int updateByPrimaryKey(HisRest record);

    /**
     * 根据员工编号查询
     * @param empId
     * @return
     * @author 何友池
     */
    List<HisRestNExt> getByEmpid(@Param("empId") Integer empId,@Param("restStartTimeMin") Date restStartTimeMin,@Param("restStartTimeMax") Date restStartTimeMax);

    /**
     * 更新一条信息
     * @param record
     * @return
     * @author 何友池
     */
    int updateHisRest(HisRest record);

    /**
     * 查询开始或结束时间在本月数据
     * @return
     * @author 何友池
     */
    List<HisRest>findThisMonthData(Integer empId);
}