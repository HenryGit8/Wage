/**
 * LoginHandlerImpl.java 2017/12/15 10:40
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.biz.mybatis.*;
import com.wage.contract.LoginHandler;
import com.wage.model.*;
import com.wage.model.ext.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * File：LoginHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class LoginHandlerImpl implements LoginHandler {

    @Autowired
    EmpDicPasswordMapper empDicPasswordMapper;

    @Autowired
    EmpDepartmentMapper empDepartmentMapper;

    @Autowired
    EmpDicBasicInfoMapper empDicBasicInfoMapper;

    @Autowired
    HisLoginMapper hisLoginMapper;

    @Autowired
    ViewAddressMapper viewAddressMapper;

    @Autowired
    SysAuthMapper sysAuthMapper;

    @Autowired
    EmpImgMapper empImgMapper;

    @Autowired
    SysNoticeMapper sysNoticeMapper;

    @Autowired
    EmpNoticeMapper empNoticeMapper;


    /**
     * 判断登录者身份
     * @param empId
     * @param password
     * @return
     * @author 何友池
     */
    @Override
    public Short getLoginMan(Integer empId, String password){
        Short result = 2;
        if(empDicPasswordMapper.selectLoginMan(empId,  password) != null){
            result = empDicPasswordMapper.selectLoginMan(empId,  password).getIsAdmin();
        }
        return result;
    }

    /**
     * 根据ID获取用户信息
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public EmpDicBasicInfo getLoginInfo(Integer empId){
       return empDicBasicInfoMapper.selectByPrimaryKey(empId);
    }

    /**
     * 获取登录者账号密码管理员
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public EmpDicPassword getLoginPass(Integer empId){
        return empDicPasswordMapper.selectByPrimaryKey(empId);
    }

    /**
     * 插入注册信息
     * @param empDicPassword
     * @author 何友池
     */
    @Override
    public void saveRegister(EmpDicPassword empDicPassword){
        empDicPasswordMapper.insert(empDicPassword);
    }

    /**
     * 保存用户信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @Override
    public void saveUserInfo(EmpDicBasicInfo empDicBasicInfo){
        empDicBasicInfoMapper.insert(empDicBasicInfo);
    }

    /**
     * 插入一条数据
     * @param record
     * @author 何友池
     */
    @Override
    public void saveLogin(HisLogin record){
        hisLoginMapper.insert(record);
    }

    /**
     * 查询人员权限
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public List<ViewAddress> getAuthByEmpId(Integer empId){
        return viewAddressMapper.findAuthByEmpId(empId);
    }

    /**
     * 查询未匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<ViewAddress> getNoMatch(Integer empId){
        List<ViewAddress> list = viewAddressMapper.findNoMatch(empId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询已匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<ViewAddress> getMatch(Integer empId){
        List<ViewAddress> list = viewAddressMapper.findMatch(empId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询所有管理员
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicPasswordExt> getAdmin(){
        List<EmpDicPasswordExt> list = empDicPasswordMapper.findAdmin();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 查询所有非管理员
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicPasswordExt> getNotAdmin(){
        List<EmpDicPasswordExt> list = empDicPasswordMapper.findNotAdmin();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 新增此管理员一条权限
     * @param empId
     * @param id
     * @author 何友池
     */
    @Override
    public void updateAddAuth(Integer empId,Integer id){
        sysAuthMapper.updateAddAuth(empId, id);
    }

    /**
     * 删除此管理员一条权限
     * @param empId
     * @param id
     * @author 何友池
     */
    @Override
    public void updateDeleAuth(Integer empId,Integer id){
        sysAuthMapper.updateDeleAuth(empId, id);
    }

    /**
     * 设置头像
     * @param empId
     * @param empHeadImg
     * @author 何友池
     */
    @Override
    public void saveEmpImgMd(Integer empId,String empHeadImg){
        EmpImg record = new EmpImg();
        record.setEmpId(empId);
        record.setEmpHeadImg(empHeadImg);
        if(empImgMapper.selectByPrimaryKey(empId)!=null){
            empImgMapper.updateByPrimaryKeySelective(record);
        }else {
            empImgMapper.insert(record);
        }
    }

    /**
     * 获取头像md5
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public String getEmpImgMd(Integer empId){
        EmpImg empImg = empImgMapper.selectByPrimaryKey(empId);
        return empImg.getEmpHeadImg();
        /*if(empImg != null){
            return empImg.getEmpHeadImg();
        }else {
            return "37867cd54bfa4b82001110132a99d1e2";
        }*/
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
        if(empDicPasswordMapper.selectLoginMan(empId,  password) != null){
            return 1;
        }else {
            return 0;
        }
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
        if(type == 1){
            if(empDicBasicInfoMapper.findQuestionAns(value,empId,null) != null){
                return 1;
            }else {
                return 0;
            }
        }else{
            if(empDicBasicInfoMapper.findQuestionAns(null,empId,value) != null){
                return 1;
            }else {
                return 0;
            }
        }
    }

    /**
     * 更新密码
     * @param empId
     * @param password
     * @author 何友池
     */
    @Override
    public void updatePassword(Integer empId,String password){
        EmpDicPassword record = new EmpDicPassword();
        record.setEmpId(empId);
        record.setPassword(password);
        empDicPasswordMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 插入一条公告
     * @param record
     * @author 何友池
     */
    @Override
    public Integer saveNotice(SysNotice record){
        sysNoticeMapper.insert(record);
        return sysNoticeMapper.selectMaxId();
    }

    /**
     * 查询一条公告
     * @param id
     * @return
     * @author 何友池
     */
    @Override
    public SysNoticeExt getOneNotice(Integer id){
        return sysNoticeMapper.selectOneNotice(id);
    }

    /**
     * 更新一条公告
     * @param record
     * @author 何友池
     */
    @Override
    public void updateNotice(SysNotice record){
        sysNoticeMapper.updateByPrimaryKeySelective(record);
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
        PageHelper.startPage(page);
        List<SysNoticeExt> list = sysNoticeMapper.findAllByEmpId(empId, empName, releaseTimeStart, releaseTimeEnd);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 删除一条公告
     * @param id
     * @author 何友池
     */
    @Override
    @Transactional
    public void removeNotice(Integer id){
        sysNoticeMapper.deleteByPrimaryKey(id);
        empNoticeMapper.removeNoticeById(id);
    }

    /**
     * 保存多条员工查看公告权限数据
     * @param empNoticList
     * @author 何友池
     */
    @Override
    @Transactional
    public void saveEmpNoticList(List<EmpNoticeKey> empNoticList){
        Integer noticeId = empNoticList.get(0).getNoticeId();
        if(empNoticeMapper.getByNoticeId(noticeId).isEmpty()){
            empNoticeMapper.saveEmpNoticList(empNoticList);
        }else{
            empNoticeMapper.removeNoticeById(noticeId);
            empNoticeMapper.saveEmpNoticList(empNoticList);
        }
    }

    /**
     * 获取树形非管理员员工信息集合
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpDicBasicInfoTreeExt> getNotAdminTree(){
        List<EmpDepartment> departList = empDepartmentMapper.findDepartList();
        List<EmpDicBasicInfoExt> list = empDicBasicInfoMapper.getNotAdmin();
        List<EmpDicBasicInfoTreeExt> treeList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EmpDicBasicInfoTreeExt listt = new EmpDicBasicInfoTreeExt();
            listt.setEmpId(list.get(i).getEmpId());
            listt.setEmpName(list.get(i).getEmpName());
            listt.setDepartRand(list.get(i).getDepartRand());
            listt.set_parentId(list.get(i).getDepartId().toString());
            treeList.add(i,listt);
        }
        for (EmpDepartment empDepartment : departList) {
            EmpDicBasicInfoTreeExt listt = new EmpDicBasicInfoTreeExt();
            listt.setEmpId(empDepartment.getDepartId());
            listt.setEmpName(empDepartment.getDepartName());
            treeList.add(listt);
        }
        PageInfo pageInfo = new PageInfo(treeList);
        return pageInfo;
    }

    /**
     * 查询此员工未读记录条数
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Integer getNoRead(Integer empId){
        return empNoticeMapper.findNoRead(empId);
    }
    /**
     * 查询此员工公告
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public List<EmpNoticeKeyExt> getNotice(Integer empId){
        return empNoticeMapper.findNotice(empId);
    }
    /**
     * 查询此员工的公告
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<EmpNoticeKeyExt> getEmpNotice(PageInfo page,Integer empId){
        PageHelper.startPage(page);
        List<EmpNoticeKeyExt> list = empNoticeMapper.findEmpNotice(empId);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新一条员工公告记录到已阅
     * @param empId
     * @param noticeId
     * @author 何友池
     */
    @Override
    public void updateIsReadNotice(Integer empId,Integer noticeId){
        empNoticeMapper.updateIsRead(empId,noticeId);
    }

    /**
     * 获取登录次数
     * @param empId
     * @return
     * @author 何友池
     */
    @Override
    public Integer getLoginCount(Integer empId){
        return hisLoginMapper.findLoginCount(empId);
    }
}
