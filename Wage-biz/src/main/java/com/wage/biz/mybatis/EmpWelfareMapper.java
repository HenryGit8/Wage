package com.wage.biz.mybatis;

import com.wage.model.EmpWelfare;
import com.wage.model.ext.EmpWelfareExt;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface EmpWelfareMapper {
    /**
     *
     * @mbggenerated 2018-01-25
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-01-25
     */
    int insert(EmpWelfare record);

    /**
     *
     * @mbggenerated 2018-01-25
     */
    int insertSelective(EmpWelfare record);

    /**
     *
     * @mbggenerated 2018-01-25
     */
    EmpWelfare selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-01-25
     */
    int updateByPrimaryKeySelective(EmpWelfare record);

    /**
     *
     * @mbggenerated 2018-01-25
     */
    int updateByPrimaryKey(EmpWelfare record);

    /**
     * 插入一条员工福利记录
     * @param record
     * @return
     * @author 何友池
     */
    int insertEmpWelfare(EmpWelfare record);

    /**
     * 插入多条员工福利记录
     * @param list
     * @return
     * @author 何友池
     */
    int insertEmpWelfareList(List<EmpWelfare> list);

    /**
     * 根据条件查找员工福利记录
     * @param empId
     * @param type
     * @param effectiveDateMin
     * @param effectiveDateMax
     * @param moneyMin
     * @param moneyMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    List<EmpWelfareExt>findBySearch(@Param("empId") Integer empId, @Param("type") String type, @Param("effectiveDateMin") Date effectiveDateMin,
                                    @Param("effectiveDateMax") Date effectiveDateMax, @Param("moneyMin") BigDecimal moneyMin, @Param("moneyMax") BigDecimal moneyMax,
                                    @Param("sort") String sort, @Param("order") String order);
    /**
     * 查询生效时间在本月数据金额总数
     * @param empId
     * @return
     * @author 何友池
     */
    String findThisMonthData(Integer empId);

    /**
     * 根据员工编号删除此月数据（离职）
     * @param empId
     * @return
     * @author 何友池
     */
    int deleteByEmpId(@Param("empId") Integer empId);
}