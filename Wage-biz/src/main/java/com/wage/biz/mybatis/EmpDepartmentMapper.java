package com.wage.biz.mybatis;

import com.wage.model.EmpDepartment;
import com.wage.model.EmpDepartmentKey;

import java.util.List;

public interface EmpDepartmentMapper {
    /**
     *
     * @mbggenerated 2018-01-26
     */
    int deleteByPrimaryKey(EmpDepartmentKey key);

    /**
     *
     * @mbggenerated 2018-01-26
     */
    int insert(EmpDepartment record);

    /**
     *
     * @mbggenerated 2018-01-26
     */
    int insertSelective(EmpDepartment record);

    /**
     *
     * @mbggenerated 2018-01-26
     */
    EmpDepartment selectByPrimaryKey(EmpDepartmentKey key);

    /**
     *
     * @mbggenerated 2018-01-26
     */
    int updateByPrimaryKeySelective(EmpDepartment record);

    /**
     *
     * @mbggenerated 2018-01-26
     */
    int updateByPrimaryKey(EmpDepartment record);

    /**
     * 查询所有部门数据
     * @return
     * @author 何友池
     */
    List<EmpDepartment> findDepartList();

    /**
     * 根据ID查询等级数据
     * @param departId
     * @return
     * @author 何友池
     */
    List<Short>findRandById(Integer departId);

    /**
     * 查询所有数据
     * @return
     * @author 何友池
     */
    List<EmpDepartment> findAllDepart();
}