package com.wage.biz.mybatis;

import com.wage.model.EmpMonDeductSal;
import com.wage.model.EmpMonDeductSalKey;
import org.apache.ibatis.annotations.Param;

public interface EmpMonDeductSalMapper {
    /**
     *
     * @mbggenerated 2017-12-14
     */
    int deleteByPrimaryKey(EmpMonDeductSalKey key);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insert(EmpMonDeductSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insertSelective(EmpMonDeductSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    EmpMonDeductSal selectByPrimaryKey(EmpMonDeductSalKey key);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKeySelective(EmpMonDeductSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKey(EmpMonDeductSal record);

    /**
     * 更新本月此员工扣除工资总额
     * @param empId
     * @return
     * @author 何友池
     */
    int updateTotal(@Param("empId") Integer empId);

    /**
     * 插入初始数据
     * @param yearMonth
     * @return
     * @author 何友池
     */
    int insertInitialData(@Param("yearMonth") Integer yearMonth);
}