package com.wage.biz.mybatis;

import com.wage.model.SysSetDefault;

public interface SysSetDefaultMapper {
    /**
     *
     * @mbggenerated 2018-02-26
     */
    int deleteByPrimaryKey(String setName);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    int insert(SysSetDefault record);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    int insertSelective(SysSetDefault record);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    SysSetDefault selectByPrimaryKey(String setName);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    int updateByPrimaryKeySelective(SysSetDefault record);

    /**
     *
     * @mbggenerated 2018-02-26
     */
    int updateByPrimaryKey(SysSetDefault record);
}