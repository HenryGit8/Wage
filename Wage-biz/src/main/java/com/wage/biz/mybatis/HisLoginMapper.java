package com.wage.biz.mybatis;

import com.wage.model.HisLogin;
import com.wage.model.ext.HisLoginExt;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface HisLoginMapper {
    /**
     *
     * @mbggenerated 2018-02-26
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    int insert(HisLogin record);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    int insertSelective(HisLogin record);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    HisLogin selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    int updateByPrimaryKeySelective(HisLogin record);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    int updateByPrimaryKey(HisLogin record);

    /**
     * 查询所有登录记录
     * @param empName
     * @param loginTimeStart
     * @param loginTimeEnd
     * @return
     * @author 何友池
     */
    List<HisLoginExt> findAllLoginHis(@Param("empName")String empName, @Param("loginTimeStart")Date loginTimeStart, @Param("loginTimeEnd")Date loginTimeEnd);

    /**
     * 获取登录者登录记录
     * @param empId
     * @return
     * @author 何友池
     */
    List<HisLoginExt> findLoginHisByEmpId(@Param("empId")Integer empId);

    /**
     * 获取登录次数
     * @param empId
     * @return
     * @author 何友池
     */
    Integer findLoginCount(@Param("empId")Integer empId);
}