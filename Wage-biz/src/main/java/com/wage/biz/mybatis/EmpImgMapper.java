package com.wage.biz.mybatis;

import com.wage.model.EmpImg;

public interface EmpImgMapper {
    /**
     *
     * @mbggenerated 2018-03-02
     */
    int deleteByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    int insert(EmpImg record);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    int insertSelective(EmpImg record);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    EmpImg selectByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    int updateByPrimaryKeySelective(EmpImg record);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    int updateByPrimaryKey(EmpImg record);
}