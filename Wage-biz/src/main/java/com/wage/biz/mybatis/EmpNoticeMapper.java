package com.wage.biz.mybatis;

import com.wage.model.EmpNoticeKey;
import com.wage.model.ext.EmpNoticeKeyExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpNoticeMapper {
    /**
     *
     * @mbggenerated 2018-03-06
     */
    int deleteByPrimaryKey(EmpNoticeKey key);

    /**
     *
     * @mbggenerated 2018-03-06
     */
    int insert(EmpNoticeKey record);

    /**
     *
     * @mbggenerated 2018-03-06
     */
    int insertSelective(EmpNoticeKey record);

    /**
     * 删除此条公告数据
     * @param noticeId
     * @return
     * @author 何友池
     */
    int removeNoticeById(@Param("noticeId")Integer noticeId);

    /**
     * 保存多条员工查看公告权限数据
     * @param list
     * @return
     * @author 何友池
     */
    int saveEmpNoticList(List<EmpNoticeKey> list);

    /**
     * 查询此条公告的员工
     * @param noticeId
     * @return
     * @author 何友池
     */
    List<EmpNoticeKey> getByNoticeId(@Param("noticeId")Integer noticeId);

    /**
     * 查询此员工未读记录条数
     * @param empId
     * @return
     * @author 何友池
     */
    Integer findNoRead(@Param("empId")Integer empId);

    /**
     * 查询此员工公告
     * @param empId
     * @return
     * @author 何友池
     */
    List<EmpNoticeKeyExt> findNotice(@Param("empId")Integer empId);

    /**
     * 查询此员工的公告
     * @param empId
     * @return
     * @author 何友池
     */
    List<EmpNoticeKeyExt> findEmpNotice(@Param("empId")Integer empId);

    /**
     * 更新到已阅
     * @param empId
     * @return
     * @author 何友池
     */
    int updateIsRead(@Param("empId")Integer empId,@Param("noticeId")Integer noticeId);
}