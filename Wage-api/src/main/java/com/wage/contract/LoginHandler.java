/**
 * LoginHandler.java 2017/12/15 10:40
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.contract;

import com.github.pagehelper.PageInfo;
import com.wage.model.*;
import com.wage.model.ext.EmpDicBasicInfoTreeExt;
import com.wage.model.ext.EmpDicPasswordExt;
import com.wage.model.ext.EmpNoticeKeyExt;
import com.wage.model.ext.SysNoticeExt;

import java.util.Date;
import java.util.List;

/**
 * File：LoginHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public interface LoginHandler {

    /**
     * 判断登录者身份
     * @param empId
     * @param password
     * @return
     * @author 何友池
     */
    Short getLoginMan(Integer empId, String password);
    /**
     * 根据ID获取用户信息
     * @param empId
     * @return
     * @author 何友池
     */
    EmpDicBasicInfo getLoginInfo(Integer empId);

    /**
     * 获取登录者账号密码管理员
     * @param empId
     * @return
     * @author 何友池
     */
    EmpDicPassword getLoginPass(Integer empId);
    /**
     * 插入注册信息
     * @param empDicPassword
     * @author 何友池
     */
    void saveRegister(EmpDicPassword empDicPassword);

    /**
     * 保存用户信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    void saveUserInfo(EmpDicBasicInfo empDicBasicInfo);
    /**
     * 插入一条数据
     * @param record
     * @author 何友池
     */
    void saveLogin(HisLogin record);

    /**
     * 查询人员权限
     * @param empId
     * @return
     * @author 何友池
     */
    List<ViewAddress> getAuthByEmpId(Integer empId);
    /**
     * 查询未匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    PageInfo<ViewAddress> getNoMatch(Integer empId);
    /**
     * 查询已匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    PageInfo<ViewAddress> getMatch(Integer empId);

    /**
     * 查询所有管理员
     * @return
     * @author 何友池
     */
    PageInfo<EmpDicPasswordExt> getAdmin();
    /**
     * 查询所有非管理员
     * @return
     * @author 何友池
     */
    PageInfo<EmpDicPasswordExt> getNotAdmin();
    /**
     * 新增此管理员一条权限
     * @param empId
     * @param id
     * @author 何友池
     */
    void updateAddAuth(Integer empId,Integer id);
    /**
     * 删除此管理员一条权限
     * @param empId
     * @param id
     * @author 何友池
     */
    void updateDeleAuth(Integer empId,Integer id);
    /**
     * 设置头像
     * @param empId
     * @param empHeadImg
     * @author 何友池
     */
    void saveEmpImgMd(Integer empId,String empHeadImg);
    /**
     * 获取头像md5
     * @param empId
     * @return
     * @author 何友池
     */
    String getEmpImgMd(Integer empId);

    /**
     * 检查是否密码正确
     * @param empId
     * @param password
     * @return
     * @author 何友池
     */
    Integer checkUser(Integer empId,String password);
    /**
     * 根据员工工号问题类型与答案查询是否正确
     * @param empId
     * @param type
     * @param value
     * @return
     * @author 何友池
     */
    Integer checkQuestionAns(Integer empId,Integer type,String value );
    /**
     * 更新密码
     * @param empId
     * @param password
     * @author 何友池
     */
    void updatePassword(Integer empId,String password);
    /**
     * 插入一条公告
     * @param record
     * @author 何友池
     */
    Integer saveNotice(SysNotice record);

    /**
     * 查询一条公告
     * @param id
     * @return
     * @author 何友池
     */
    SysNoticeExt getOneNotice(Integer id);
    /**
     * 更新一条公告
     * @param record
     * @author 何友池
     */
    void updateNotice(SysNotice record);
    /**
     * 查询全部公告(0:不是此员工公告，1：是此员工公告)
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    PageInfo<SysNoticeExt> getAllNoticeByEmpId(PageInfo page, Integer empId, String empName, Date releaseTimeStart, Date releaseTimeEnd);
    /**
     * 删除一条公告
     * @param id
     * @author 何友池
     */
    void removeNotice(Integer id);
    /**
     * 保存多条员工查看公告权限数据
     * @param empNoticList
     * @author 何友池
     */
    void saveEmpNoticList(List<EmpNoticeKey> empNoticList);
    /**
     * 获取树形非管理员员工信息集合
     * @return
     * @author 何友池
     */
    PageInfo<EmpDicBasicInfoTreeExt> getNotAdminTree();
    /**
     * 查询此员工未读记录条数
     * @param empId
     * @return
     * @author 何友池
     */
    Integer getNoRead(Integer empId);
    /**
     * 查询此员工公告
     * @param empId
     * @return
     * @author 何友池
     */
    List<EmpNoticeKeyExt> getNotice(Integer empId);
    /**
     * 查询此员工的公告
     * @param empId
     * @return
     * @author 何友池
     */
    PageInfo<EmpNoticeKeyExt> getEmpNotice(PageInfo page,Integer empId);

    /**
     * 更新一条员工公告记录到已阅
     * @param empId
     * @param noticeId
     * @author 何友池
     */
    void updateIsReadNotice(Integer empId,Integer noticeId);
    /**
     * 获取登录次数
     * @param empId
     * @return
     * @author 何友池
     */
    Integer getLoginCount(Integer empId);
}
