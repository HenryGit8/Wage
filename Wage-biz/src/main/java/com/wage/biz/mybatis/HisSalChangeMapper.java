package com.wage.biz.mybatis;

import com.wage.model.HisSalChange;
import com.wage.model.HisSalChangeKey;
import com.wage.model.ext.HisSalChangeExt;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface HisSalChangeMapper {
    /**
     *
     * @mbggenerated 2017-12-29
     */
    int deleteByPrimaryKey(HisSalChangeKey key);

    /**
     *
     * @mbggenerated 2017-12-29
     */
    int insert(HisSalChange record);

    /**
     *
     * @mbggenerated 2017-12-29
     */
    int insertSelective(HisSalChange record);

    /**
     *
     * @mbggenerated 2017-12-29
     */
    HisSalChange selectByPrimaryKey(HisSalChangeKey key);

    /**
     *
     * @mbggenerated 2017-12-29
     */
    int updateByPrimaryKeySelective(HisSalChange record);

    /**
     *
     * @mbggenerated 2017-12-29
     */
    int updateByPrimaryKey(HisSalChange record);

    /**
     * 根据员工编号查询
     * @param empId
     * @return
     * @author 何友池
     */
    List<HisSalChangeExt> getByEmpId(@Param("empId")Integer empId);

    /**
     * 根据员工编号查询总额
     * @param empId
     * @return
     * @author 何友池
     */
    List<BigDecimal> getTotal (@Param("empId")Integer empId);

    /**
     * 获得最后6个月数据
     * @param empId
     * @return
     * @author 何友池
     */
    List<Integer> getMonth(@Param("empId")Integer empId);

    /**
     * 获得半年内固定工资变化
     * @param empId
     * @return
     * @author 何友池
     */
    List<BigDecimal> getBasicSal(@Param("empId")Integer empId);

    /**
     * 获得半年内额外工资变化
     * @param empId
     * @return
     * @author 何友池
     */
    List<BigDecimal> getOther(@Param("empId")Integer empId);

    /**
     * 获得半年内扣除工资变化
     * @param empId
     * @return
     * @author 何友池
     */
    List<BigDecimal> getDeduct(@Param("empId")Integer empId);

    /**
     * 获取固定工资总额
     * @param empId
     * @return
     * @author 何友池
     */
    BigDecimal getTotalBasic(@Param("empId")Integer empId);
    /**
     * 获取其他工资总额
     * @param empId
     * @return
     * @author 何友池
     */
    BigDecimal getTotalOther(@Param("empId")Integer empId);
    /**
     * 获取扣除工资总额
     * @param empId
     * @return
     * @author 何友池
     */
    BigDecimal getTotalDeduct(@Param("empId")Integer empId);
    /**
     * 获取6个月总工资总额
     * @param empId
     * @return
     * @author 何友池
     */
    BigDecimal getTotalAll(@Param("empId")Integer empId);
    /**
     * 获得半年内固定工资变化
     * @param empId
     * @return
     * @author 何友池
     */
    BigDecimal getBasicSalOne(@Param("empId")Integer empId,@Param("changeTime")Integer changeTime);

    /**
     * 获得半年内额外工资变化
     * @param empId
     * @return
     * @author 何友池
     */
    BigDecimal getOtherOne(@Param("empId")Integer empId,@Param("changeTime")Integer changeTime);

    /**
     * 获得半年内扣除工资变化
     * @param empId
     * @return
     * @author 何友池
     */
    BigDecimal getDeductOne(@Param("empId")Integer empId,@Param("changeTime")Integer changeTime);

    /**
     * 插入所有月工资表的数据到本月固定工资表中
     * @return
     * @author 何友池
     */
    int insertAll();

    /**
     * 根据员工编号与日期更新一条数据
     * @param empId
     * @param changeTime
     * @return
     * @author 何友池
     */
    int updateMonSalChange(@Param("empId")Integer empId,@Param("changeTime")Integer changeTime);

    /**
     * 插入此月数据
     * @param yearMonth
     * @return
     * @author 何友池
     */
    int insertByMonth(@Param("yearMonth") Integer yearMonth);
}