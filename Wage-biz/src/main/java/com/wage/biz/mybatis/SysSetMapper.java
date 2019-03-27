package com.wage.biz.mybatis;

import com.wage.model.SysSet;

import java.util.List;

public interface SysSetMapper {
    /**
     *
     * @mbggenerated 2018-01-22
     */
    int deleteByPrimaryKey(String setName);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    int insert(SysSet record);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    int insertSelective(SysSet record);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    SysSet selectByPrimaryKey(String setName);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    int updateByPrimaryKeySelective(SysSet record);

    /**
     *
     * @mbggenerated 2018-01-22
     */
    int updateByPrimaryKey(SysSet record);

    /**
     * 查询所有系统表数据
     * @return
     * @author 何友池
     */
    List<SysSet> findAll();

    /**
     * 批量更新系统设置表
     * @param list
     * @return
     * @author 何友池
     */
    int updateSet(List<SysSet> list);

    /**
     * 更新至默认值
     * @return
     * @author 何友池
     */
    int updateToDefault();
}