package com.wage.biz.mybatis;

import com.wage.model.SysEmpNewSet;
import com.wage.model.SysEmpNewSetKey;

import java.util.List;

public interface SysEmpNewSetMapper {
    /**
     *
     * @mbggenerated 2018-03-19
     */
    int deleteByPrimaryKey(SysEmpNewSetKey key);

    /**
     *
     * @mbggenerated 2018-03-19
     */
    int insert(SysEmpNewSet record);

    /**
     *
     * @mbggenerated 2018-03-19
     */
    int insertSelective(SysEmpNewSet record);

    /**
     *
     * @mbggenerated 2018-03-19
     */
    SysEmpNewSet selectByPrimaryKey(SysEmpNewSetKey key);

    /**
     *
     * @mbggenerated 2018-03-19
     */
    int updateByPrimaryKeySelective(SysEmpNewSet record);

    /**
     *
     * @mbggenerated 2018-03-19
     */
    int updateByPrimaryKey(SysEmpNewSet record);

    /**
     * 获取所有滚轮图数据
     * @return
     * @author 何友池
     */
    List<SysEmpNewSet> findAllNewImg();

    /**
     * 获取所有非滚轮图数据
     * @return
     * @author 何友池
     */
    List<SysEmpNewSet> findAllNewNoImg();

    /**
     * 删除所有推荐新闻
     * @return
     * @author 何友池
     */
    int removeAllNewNoImg();

    /**
     * 保存全部推荐新闻列表
     * @param list
     * @return
     * @author 何友池
     */
    int saveAllNewNoImg(List<SysEmpNewSet> list);

    /**
     * 删除所有新闻滚轮图
     * @return
     * @author 何友池
     */
    int removeAllNewImg();

    /**
     * 保存全部新闻滚轮图列表
     * @param list
     * @return
     * @author 何友池
     */
    int saveAllNewImg(List<SysEmpNewSet> list);
}