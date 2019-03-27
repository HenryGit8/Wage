package com.wage.biz.mybatis;

import com.wage.model.SysNotice;
import com.wage.model.ext.SysNoticeExt;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysNoticeMapper {
    /**
     *
     * @mbggenerated 2018-03-02
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    int insert(SysNotice record);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    int insertSelective(SysNotice record);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    SysNotice selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    int updateByPrimaryKeySelective(SysNotice record);

    /**
     *
     * @mbggenerated 2018-03-02
     */
    int updateByPrimaryKey(SysNotice record);

    /**
     * 查询所有此员工公告(0:不是此员工公告，1：是此员工公告)
     * @return
     * @author 何友池
     */
    List<SysNoticeExt> findAllByEmpId(@Param("empId") Integer empId,@Param("empName") String empName,@Param("releaseTimeStart") Date releaseTimeStart,@Param("releaseTimeEnd") Date releaseTimeEnd);

    /**
     * 查询所有公告非此员工
     * @return
     * @author 何友池
     */
    List<SysNotice> findAllByNotEmpId(@Param("empId") Integer empId);

    /**
     * 查询最大ID
     * @return
     * @author 何友池
     */
    Integer selectMaxId();

    /**
     * 查询一条公告
     * @param id
     * @return
     * @author 何友池
     */
    SysNoticeExt selectOneNotice(@Param("id") Integer id);
}