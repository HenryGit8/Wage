package com.wage.biz.mybatis;

import com.wage.model.EmpDicBasicInfo;
import com.wage.model.ext.EmpDicBasicInfoExt;
import com.wage.model.ext.EmpDicBasicInfoSExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpDicBasicInfoMapper {
    /**
     *
     * @mbggenerated 2017-12-14
     */
    int deleteByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insert(EmpDicBasicInfo record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int insertSelective(EmpDicBasicInfo record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    EmpDicBasicInfo selectByPrimaryKey(Integer empId);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKeySelective(EmpDicBasicInfo record);

    /**
     *
     * @mbggenerated 2017-12-14
     */
    int updateByPrimaryKey(EmpDicBasicInfo record);

    /**
     * 根据查询条件返回结果
     * @param record
     * @return
     * @author 何友池
     */
    List<EmpDicBasicInfoExt> getBySearch(@Param("record") EmpDicBasicInfoSExt record, @Param("sort") String sort, @Param("order") String order);

    /**
     * 获取最大ID
     * @return
     * @author 何友池
     */
    Integer selectMaxId();

    /**
     * 查询最大ID
     * @return
     * @author 何友池
     */
    Integer findMaxId();

    /**
     * 查询问题答案
     * @param empBankCard
     * @param empId
     * @param empIdnum
     * @return
     * @author 何友池
     */
    EmpDicBasicInfo findQuestionAns(@Param("empBankCard") String empBankCard,@Param("empId") Integer empId,@Param("empIdnum") String empIdnum);

    /**
     * 获得非管理员数据
     * @return
     * @author 何友池
     */
    List<EmpDicBasicInfoExt> getNotAdmin();
}