package com.wage.biz.mybatis;


import com.wage.model.HisRevokeApply;
import com.wage.model.ext.HisRevokeApplyExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HisRevokeApplyMapper {
    /**
     *
     * @mbggenerated 2018-01-15
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-01-15
     */
    int insert(HisRevokeApply record);

    /**
     *
     * @mbggenerated 2018-01-15
     */
    int insertSelective(HisRevokeApply record);

    /**
     *
     * @mbggenerated 2018-01-15
     */
    HisRevokeApply selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-01-15
     */
    int updateByPrimaryKeySelective(HisRevokeApply record);

    /**
     *
     * @mbggenerated 2018-01-15
     */
    int updateByPrimaryKey(HisRevokeApply record);

    /**
     * 查询全部撤回请假申请数据
     * @return
     * @author 何友池
     */
    List<HisRevokeApplyExt> findAll(@Param("isAllow") Short isAllow);
}