package com.wage.biz.mybatis;

import com.wage.model.EmpMonSal;
import com.wage.model.ext.EmpMonSalAllExt;
import com.wage.model.ext.EmpMonSalExt;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmpMonSalMapper {
    /**
     *
     * @mbggenerated 2017-12-14
     */
    int deleteByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insert(EmpMonSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insertSelective(EmpMonSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    EmpMonSal selectByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKeySelective(EmpMonSal record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKey(EmpMonSal record);

    /**
     * 根据条件查询固定工资表
     * @param empName
     * @param basicPayStart
     * @param basicPayEnd
     * @param jobSalaryStart
     * @param jobSalaryEnd
     * @param totalStart
     * @param totalEnd
     * @return
     * @author 何友池
     */
    List<EmpMonSalExt>getBySearch(@Param("empName")String empName,@Param("empId")Integer empId,@Param("basicPayStart")BigDecimal basicPayStart,@Param("basicPayEnd")BigDecimal basicPayEnd,@Param("jobSalaryStart")BigDecimal jobSalaryStart,
                                  @Param("jobSalaryEnd")BigDecimal jobSalaryEnd,@Param("totalStart")BigDecimal totalStart,@Param("totalEnd")BigDecimal totalEnd,@Param("sort")String sort,@Param("order")String order);

    /**
     * 查询固定工资总额
     * @param empId
     * @return
     * @author 何友池
     */
    Integer selectTotal(Integer empId);

    /**
     * 根据员工编号查询各类型工资
     * @param empId
     * @param yearMonth
     * @return
     * @author 何友池
     */
    EmpMonSalAllExt findWageByEmpId(@Param("empId") Integer empId,@Param("yearMonth")Integer yearMonth);
}