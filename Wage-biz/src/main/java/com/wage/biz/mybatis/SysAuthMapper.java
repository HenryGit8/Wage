package com.wage.biz.mybatis;

import com.wage.model.SysAuth;
import org.apache.ibatis.annotations.Param;

public interface SysAuthMapper {
    /**
     *
     * @mbggenerated 2018-02-28
     */
    int deleteByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    int insert(SysAuth record);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    int insertSelective(SysAuth record);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    SysAuth selectByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    int updateByPrimaryKeySelective(SysAuth record);

    /**
     *
     * @mbggenerated 2018-02-28
     */
    int updateByPrimaryKey(SysAuth record);

    /**
     * 新增此管理员一条权限
     * @param empId
     * @param id
     * @return
     * @author 何友池
     */
    int updateAddAuth(@Param("empId") Integer empId,@Param("id") Integer id);

    /**
     * 删除此管理员一条权限
     * @param empId
     * @param id
     * @return
     * @author 何友池
     */
    int updateDeleAuth(@Param("empId") Integer empId,@Param("id") Integer id);
}