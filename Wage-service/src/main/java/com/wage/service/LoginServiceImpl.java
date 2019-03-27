/**
 * LoginServiceImpl.java 2017/12/15 10:35
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.api.LoginService;
import com.wage.contract.LoginHandler;
import com.wage.model.*;
import com.wage.model.ext.EmpDicBasicInfoTreeExt;
import com.wage.model.ext.EmpDicPasswordExt;
import com.wage.model.ext.EmpNoticeKeyExt;
import com.wage.model.ext.SysNoticeExt;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.zk.TcpDiscoveryZookeeperIpFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * File：LoginServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginHandler loginHandler;

    /**
     * 判断登录者身份
     * @param empId
     * @param password
     * @return
     */
    @Override
    public Short getLoginMan(Integer empId, String password){
        return loginHandler.getLoginMan(empId, password);
    }

    /**
     * 根据ID获取用户信息
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public EmpDicBasicInfo getLoginInfo(Integer empId){
        return loginHandler.getLoginInfo(empId);
    }

    /**
     * 获取登录者账号密码管理员
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public EmpDicPassword getLoginPass(Integer empId){
        return loginHandler.getLoginPass(empId);
    }
    /**
     * 插入注册信息
     * @param empDicPassword
     * @author 何友池
     */
    @Override
    public void saveRegister(EmpDicPassword empDicPassword){
        loginHandler.saveRegister(empDicPassword);
    }
    /**
     * 保存用户信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @Override
    public void saveUserInfo(EmpDicBasicInfo empDicBasicInfo){
        loginHandler.saveUserInfo(empDicBasicInfo);
    }

    /**
     * 插入一条数据
     * @param record
     * @author 何友池
     */
    @Override
    public void saveLogin(HisLogin record){
        loginHandler.saveLogin(record);
    }

    /**
     * 查询人员权限
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public List<ViewAddress> getAuthByEmpId(Integer empId){
        return loginHandler.getAuthByEmpId(empId);
    }
    /**
     * 查询未匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<ViewAddress> getNoMatch(Integer empId){
        return loginHandler.getNoMatch(empId);
    }

    /**
     * 查询已匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<ViewAddress> getMatch(Integer empId){
        return loginHandler.getMatch(empId);
    }

    /**
     * 查询所有管理员
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicPasswordExt> getAdmin(){
        return loginHandler.getAdmin();
    }

    /**
     * 查询所有非管理员
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicPasswordExt> getNotAdmin(){
        return loginHandler.getNotAdmin();
    }

    /**
     * 新增此管理员一条权限
     * @param empId
     * @param id
     * @author 何友池
     */
    @Override
    public void updateAddAuth(Integer empId,Integer id){
        loginHandler.updateAddAuth(empId, id);
    }

    /**
     * 删除此管理员一条权限
     * @param empId
     * @param id
     * @author 何友池
     */
    @Override
    public void updateDeleAuth(Integer empId,Integer id){
        loginHandler.updateDeleAuth(empId, id);
    }

    /**
     * 设置头像
     * @param empId
     * @param empHeadImg
     * @author 何友池
     */
    @Override
    public void saveEmpImgMd(Integer empId,String empHeadImg){
        loginHandler.saveEmpImgMd(empId, empHeadImg);
    }

    /**
     * 获取头像md5
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public String getEmpImgMd(Integer empId){
        return loginHandler.getEmpImgMd(empId);
    }

    /**
     * 检查是否密码正确
     * @param empId
     * @param password
     * @return
     * @author 何友池
     */
    @Override
    public Integer checkUser(Integer empId,String password){
        return loginHandler.checkUser(empId, password);
    }
    /**
     * 根据员工工号问题类型与答案查询是否正确
     * @param empId
     * @param type
     * @param value
     * @return
     * @author 何友池
     */
    @Override
    public Integer checkQuestionAns(Integer empId,Integer type,String value ){
        return loginHandler.checkQuestionAns(empId, type, value);
    }
    /**
     * 更新密码
     * @param empId
     * @param password
     * @author 何友池
     */
    @Override
    public void updatePassword(Integer empId,String password){
        loginHandler.updatePassword(empId, password);
    }

    /**
     * 插入一条公告
     * @param record
     * @author 何友池
     */
    @Override
    public Integer saveNotice(SysNotice record){
        return loginHandler.saveNotice(record);
    }

    /**
     * 查询一条公告
     * @param id
     * @return
     * @author 何友池
     */
    @Override
    public SysNoticeExt getOneNotice(Integer id){
        return loginHandler.getOneNotice(id);
    }

    /**
     * 更新一条公告
     * @param record
     * @author 何友池
     */
    @Override
    public void updateNotice(SysNotice record){
        loginHandler.updateNotice(record);
    }

    /**
     * 查询全部公告(0:不是此员工公告，1：是此员工公告)
     * @param page
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<SysNoticeExt> getAllNoticeByEmpId(PageInfo page,Integer empId, String empName, Date releaseTimeStart, Date releaseTimeEnd){
        return loginHandler.getAllNoticeByEmpId(page, empId, empName, releaseTimeStart, releaseTimeEnd);
    }

    /**
     * 删除一条公告
     * @param id
     * @author 何友池
     */
    @Override
    public void removeNotice(Integer id){
        loginHandler.removeNotice(id);
    }

    /**
     * 保存多条员工查看公告权限数据
     * @param empNoticList
     * @author 何友池
     */
    @Override
    public void saveEmpNoticList(List<EmpNoticeKey> empNoticList){
        loginHandler.saveEmpNoticList(empNoticList);
    }

    /**
     * 获取树形非管理员员工信息集合
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicBasicInfoTreeExt> getNotAdminTree(){
        return loginHandler.getNotAdminTree();
    }

    /**
     * 查询此员工未读记录条数
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Integer getNoRead(Integer empId){
        return loginHandler.getNoRead(empId);
    }
    /**
     * 查询此员工公告
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public List<EmpNoticeKeyExt> getNotice(Integer empId){
        return loginHandler.getNotice(empId);
    }
    /**
     * 查询此员工的公告
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpNoticeKeyExt> getEmpNotice(PageInfo page,Integer empId){
        return loginHandler.getEmpNotice(page,empId);
    }

    /**
     * 更新一条员工公告记录到已阅
     * @param empId
     * @param noticeId
     * @author 何友池
     */
    @Override
    public void updateIsReadNotice(Integer empId,Integer noticeId){
        loginHandler.updateIsReadNotice(empId,noticeId);
    }

    /**
     * 获取登录次数
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Integer getLoginCount(Integer empId){
        return loginHandler.getLoginCount(empId);
    }
}

