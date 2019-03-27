package com.wage.biz.mybatis;


import com.wage.model.HisOver;
import com.wage.model.HisOverKey;
import com.wage.model.ext.HisOverExt;
import com.wage.model.ext.HisOverOExt;
import com.wage.model.ext.HisRestNExt;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface HisOverMapper {
    /**
     *
     * @mbggenerated 2018-01-17
     */
    int deleteByPrimaryKey(HisOverKey key);

    /**
     *
     * @mbggenerated 2018-01-17
     */
    int insert(HisOver record);

    /**
     *
     * @mbggenerated 2018-01-17
     */
    int insertSelective(HisOver record);

    /**
     *
     * @mbggenerated 2018-01-17
     */
    HisOver selectByPrimaryKey(HisOverKey key);

    /**
     *
     * @mbggenerated 2018-01-17
     */
    int updateByPrimaryKeySelective(HisOver record);

    /**
     *
     * @mbggenerated 2018-01-17
     */
    int updateByPrimaryKey(HisOver record);

    /**
     * 根据员工编号查询加班记录表
     * @param empId
     * @param overTimeStartMin
     * @param overTimeStartMax
     * @return
     * @author 何友池
     */
    List<HisOverExt> getByEmpid(@Param("empId") Integer empId, @Param("overTimeStartMin") Date overTimeStartMin, @Param("overTimeStartMax") Date overTimeStartMax);

    /**
     * 更新加班信息表
     * @param record
     * @return
     * @author 何友池
     */
    int updateHisOver(HisOverOExt record);

    /**
     * 根据是否已审核查询
     * @param isCheck
     * @return
     * @author 何友池
     */
    List<HisOverExt> getByEmpidC(@Param("isCheck") Short isCheck);

    /**
     * 查询开始或结束时间在本月数据
     * @param empId
     * @return
     * @author 何友池
     */
    List<HisOver>findThisMonthData(Integer empId);
}