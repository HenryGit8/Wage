package com.wage.biz.mybatis;

import com.wage.model.ViewAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViewAddressMapper {
    /**
     *
     * @mbggenerated 2018-02-28
     */
    int deleteByPrimaryKey(Short id);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    int insert(ViewAddress record);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    int insertSelective(ViewAddress record);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    ViewAddress selectByPrimaryKey(Short id);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    int updateByPrimaryKeySelective(ViewAddress record);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    int updateByPrimaryKey(ViewAddress record);

    /**
     * 查询人员权限
     * @param empId
     * @return
     * @author 何友池
     */
    List<ViewAddress> findAuthByEmpId(@Param("empId") Integer empId);

    /**
     * 查询未匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    List<ViewAddress> findNoMatch(@Param("empId") Integer empId);

    /**
     * 查询已匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    List<ViewAddress> findMatch(@Param("empId") Integer empId);
}