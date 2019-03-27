package com.wage.biz.mybatis;

import com.wage.model.EmpDicPassword;
import com.wage.model.ext.EmpDicPasswordExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpDicPasswordMapper {
    /**
     *
     * @mbggenerated 2017-12-14
     */
    int deleteByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insert(EmpDicPassword record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insertSelective(EmpDicPassword record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    EmpDicPassword selectByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKeySelective(EmpDicPassword record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKey(EmpDicPassword record);

    /**
     * 获取登录者
     * @param empId
     * @param password
     * @return
     * @author 何友池
     */
    EmpDicPassword selectLoginMan(@Param("empId")Integer empId, @Param("password")String password);

    /**
     * 查询所有管理员
     * @return
     * @author 何友池
     */
    List<EmpDicPasswordExt> findAdmin();

    /**
     * 查询所有管理员
     * @return
     * @author 何友池
     */
    List<EmpDicPasswordExt> findNotAdmin();

    /**
     * 查询已注册用户
     * @param empId
     * @return
     * @author 何友池
     */
    List<EmpDicPasswordExt> findIsRegister(@Param("empId")Integer empId, @Param("empName")String empName);
}