package com.wage.biz.mybatis;

import com.wage.model.EmpMonOtherSal;
import com.wage.model.EmpMonOtherSalKey;
import com.wage.model.ext.EmpMonOtherSalExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMonOtherSalMapper {
    /**
     *
     * @mbggenerated 2017-12-14
     */
    int deleteByPrimaryKey(EmpMonOtherSalKey key);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insert(EmpMonOtherSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insertSelective(EmpMonOtherSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    EmpMonOtherSal selectByPrimaryKey(EmpMonOtherSalKey key);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKeySelective(EmpMonOtherSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKey(EmpMonOtherSal record);

    /**
     * 根据员工编号查询
     * @param empId
     * @return
     * @author 何友池
     */
    List<EmpMonOtherSalExt> findOtherSal(@Param("empId") Integer empId,@Param("sort")String sort,@Param("order")String order);

    /**
     * 将福利表数据更新进去
     * @param empId
     * @return
     * @author 何友池
     */
    int updateWelfare(@Param("empId") Integer empId);

    /**
     * 将福利表数据插入
     * @param empId
     * @return
     * @author 何友池
     */
    int insertWelfare(@Param("empId") Integer empId);

    /**
     * 查询本月全部数据
     * @return
     * @author 何友池
     */
    List<EmpMonOtherSal> findAll();

    /**
     * 更新其他工资总额
     * @param empId
     * @return
     * @author 何友池
     */
    int updateOtherTotal(@Param("empId") Integer empId);

    /**
     * 批量更新其他工资总额
     * @param empId
     * @return
     * @author 何友池
     */
    int updateOtherTotalList(List<Integer> empId);

    /**
     * 将福利表数据批量更新进去
     * @param empId
     * @return
     * @author 何友池
     */
    int updateWelfareList(List<Integer> empId);

    /**
     * 将福利表批量数据插入
     * @param empId
     * @return
     * @author 何友池
     */
    int insertEmpWelfareList(List<Integer> empId);

    /**
     * 插入初始数据
     * @param yearMonth
     * @return
     * @author 何友池
     */
    int insertInitialData(@Param("yearMonth") Integer yearMonth);
}