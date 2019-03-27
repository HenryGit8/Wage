package com.wage.biz.mybatis;

import com.wage.model.HisSalary;
import com.wage.model.HisSalaryKey;
import com.wage.model.dto.HisSalaryChartDto;
import com.wage.model.ext.HisSalaryDataExt;
import com.wage.model.ext.HisSalaryExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HisSalaryMapper {
    /**
     *
     * @mbggenerated 2018-02-07
     */
    int deleteByPrimaryKey(HisSalaryKey key);

    /**
     *
     * @mbggenerated 2018-02-07
     */
    int insert(HisSalary record);

    /**
     *
     * @mbggenerated 2018-02-07
     */
    int insertSelective(HisSalary record);

    /**
     *
     * @mbggenerated 2018-02-07
     */
    HisSalary selectByPrimaryKey(HisSalaryKey key);

    /**
     *
     * @mbggenerated 2018-02-07
     */
    int updateByPrimaryKeySelective(HisSalary record);

    /**
     *
     * @mbggenerated 2018-02-07
     */
    int updateByPrimaryKey(HisSalary record);

    /**
     * 根据查询条件查询月工资明细
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    List<HisSalaryExt>findBySearch(HisSalaryDataExt hisSalaryDataExt);

    /**
     * 初始插入此月份的工资发放信息
     * @param yearMonth
     * @return
     * @author 何友池
     */
    int insertHisSalary(@Param("yearMonth") Integer yearMonth);

    /**
     * 批量更新工资发放信息表
     * @param list
     * @return
     * @author 何友池
     */
    int updateHisSalaryList(List<HisSalary> list);

    /**
     * 根据月份查询工资发放记录
     * @param yearMonth
     * @return
     * @author 何友池
     */
    List<HisSalary> findByMonth(@Param("yearMonth") Integer yearMonth);

    /**
     * 查询所有年月
     * @return
     * @author 何友池
     */
    List<Integer>findAllMonth();

    /**
     * 查询图表数据集
     * @return
     * @author 何友池
     */
    List<HisSalaryChartDto> findDataChart();

    /**
     * 查询本月饼图数据
     * @return
     * @author 何友池
     */
    List<HisSalaryChartDto> findDataPie();

    /**
     * 查询月统计数据
     * @return
     * @author 何友池
     */
    HisSalary findTotalData(@Param("yearMonth") Integer yearMonth);
}