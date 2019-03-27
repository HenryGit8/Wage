package com.wage.biz.mybatis;

import com.wage.model.SysHoliday;

import java.util.Date;
import java.util.List;

public interface SysHolidayMapper {
    /**
     *
     * @mbggenerated 2018-01-22
     */
    int deleteByPrimaryKey(Date dateTime);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    int insert(SysHoliday record);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    int insertSelective(SysHoliday record);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    SysHoliday selectByPrimaryKey(Date dateTime);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    int updateByPrimaryKeySelective(SysHoliday record);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    int updateByPrimaryKey(SysHoliday record);

    /**
     * 查询假期
     * @return
     * @author 何友池
     */
    List<SysHoliday> findHoliday();
    /**
     * 查询公司额外工作日
     * @return
     * @author 何友池
     */
    List<SysHoliday> findWorkday();

    /**
     * 查询所有数据
     * @return
     * @author 何友池
     */
    List<SysHoliday> findAll();
}