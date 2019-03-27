/**
 * LoginHandler.java 2017/12/15 10:32
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.wage.api.EmpInfoService;
import com.wage.api.LoginService;
import com.wage.model.*;
import com.wage.model.ext.EmpDicBasicInfoTreeExt;
import com.wage.model.ext.EmpDicPasswordExt;
import com.wage.model.ext.EmpNoticeKeyExt;
import com.wage.model.ext.SysNoticeExt;
import com.wage.util.AddressUtils;
import com.wage.util.IpUtils;
import com.wage.util.MD5;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.PageUtil;
import com.wage.web.constant.ParamConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
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
@Component
public class LoginHandler {

    @Reference
    LoginService loginService;

    @Reference
    EmpInfoService empInfoService;

    /**
     * 判断登录者身份,并把登录者信息放进session
     * @param empId
     * @param password
     * @return（1:管理员，0:员工，2:无记录，3：验证码错误）
     */
    public Short getLoginMan(Integer empId, String password,String checkCode,HttpSession session,String ip,HttpServletRequest request){
        checkCode = checkCode.toUpperCase();
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        Short result =  loginService.getLoginMan(empId, MD5.getMD5(password));
        EmpDicPassword empDicPassword;
        EmpDicBasicInfo empDicBasicInfo;
        if(result != 2){
            empDicBasicInfo = loginService.getLoginInfo(empId);
            ContextHolder.getSession().setAttribute(ParamConstants.LOCAL_CLINET_EMP, empDicBasicInfo);
            empDicPassword = loginService.getLoginPass(empId);
            ContextHolder.getSession().setAttribute(ParamConstants.LOCAL_CLINET_USER, empDicPassword);
            if(result == 1){
                ContextHolder.getSession().setAttribute(ParamConstants.EMP_AUTHORITY, loginService.getAuthByEmpId(empId));
            }
            if(result == 0){
                ContextHolder.getSession().setAttribute(ParamConstants.EMP_NOTICE, loginService.getNotice(empId));
            }
            if(!random.equals(checkCode)){
                result = 3;//验证码错误
            }
        }
        return result;
    }

    /**
     * 插入注册信息(0:不存在此用户,1:用户名已存在,2:员工表中无此员工信息;3:验证码错误)
     * @param empId
     * @param password
     * @author 何友池
     */
    public Integer saveRegister(Integer empId,String password,String checkCode,HttpSession session){
        checkCode = checkCode.toUpperCase();
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        Integer result = 0;
        EmpDicPassword u = loginService.getLoginPass(empId);
        if (null != u) {// 用户已经存在
            result = 1;
        } else if(empInfoService.getOneInfo(empId) == null){
            result = 2;
        } else if(!random.equals(checkCode)){
            result = 3;//验证码错误
        }else {
            EmpDicPassword empDicPassword = new EmpDicPassword();
            empDicPassword.setEmpId(empId);
            empDicPassword.setPassword(MD5.getMD5(password));
            empDicPassword.setIsAdmin((short)0);
            loginService.saveRegister(empDicPassword);
        }
        return result;
    }

    /**
     * 保存用户信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    public void saveUserInfo(EmpDicBasicInfo empDicBasicInfo){
        loginService.saveUserInfo(empDicBasicInfo);
    }

    /**
     * 检查用户名是否存在
     * @param empId
     * @return(0:不存在此用户,1:用户名已存在,2:员工表中无此员工信息)
     * @author 何友池
     */
    public Integer remoteUsername(Integer empId){
        Integer result = 0;
        EmpDicPassword u = loginService.getLoginPass(empId);
        if (null != u) {// 用户已经存在
            result = 1;
        } else if(empInfoService.getOneInfo(empId) == null){
            result = 2;
        }
        return result;
    }


    /**
     * 登出
     * @author 何友池
     */
    public void doLogout() {
        HttpSession session = ContextHolder.getSession();
        if (session != null) {
            session.removeAttribute(ParamConstants.LOCAL_CLINET_USER);
            session.removeAttribute(ParamConstants.LOCAL_CLINET_EMP);
            session.invalidate();
        }
    }

    /**
     * 查询人员权限
     * @param empId
     * @return
     * @author 何友池
     */
    public List<ViewAddress> getAuthByEmpId(Integer empId){
        return loginService.getAuthByEmpId(empId);
    }

    /**
     * 查询未匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    public PageInfo<ViewAddress> getNoMatch(Integer empId){
        return loginService.getNoMatch(empId);
    }

    /**
     * 查询已匹配数据
     * @param empId
     * @return
     * @author 何友池
     */
    public PageInfo<ViewAddress> getMatch(Integer empId){
        return loginService.getMatch(empId);
    }

    /**
     * 查询所有管理员
     * @return
     * @author 何友池
     */
    public PageInfo<EmpDicPasswordExt> getAdmin(){
        return loginService.getAdmin();
    }

    /**
     * 查询所有非管理员
     * @return
     * @author 何友池
     */
    public PageInfo<EmpDicPasswordExt> getNotAdmin(){
        return loginService.getNotAdmin();
    }

    /**
     * 新增此管理员一条权限
     * @param empId
     * @param id
     * @author 何友池
     */
    public void updateAddAuth(Integer empId,Integer id){
        loginService.updateAddAuth(empId, id);
    }

    /**
     * 删除此管理员一条权限
     * @param empId
     * @param id
     * @author 何友池
     */
    public void updateDeleAuth(Integer empId,Integer id){
        loginService.updateDeleAuth(empId, id);
    }
    /**
     * 设置头像
     * @param empHeadImg
     * @author 何友池
     */
    public void saveEmpImgMd(String empHeadImg,Integer empId){
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        loginService.saveEmpImgMd(empId, empHeadImg);
    }

    /**
     * 获取头像md5
     * @return
     * @author 何友池
     */
    public String getEmpImgMd(Integer empId){
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        String empImg =  loginService.getEmpImgMd(empId);
        if(empImg != null){
            return empImg;
        }else {
            return ParamConstants.NO_HEAD_MD;
        }
    }

    /**
     * 获取登录者信息
     * @return
     * @author 何友池
     */
    public EmpDicBasicInfo getLoginInfo(){
        return loginService.getLoginInfo(ContextHolder.getSessionEmp().getEmpId());
    }

    /**
     * 检查问题是否正确及修改密码
     * @param oldPassword
     * @param newPassword1
     * @param questionType
     * @param questionValue
     * @param checkCode
     * @return result(0:更改成功,1:原密码错误,2:问题答案错误,3:验证码错误)
     * @author 何友池
     */
    public Integer updatePassword(String oldPassword,String newPassword1,Integer questionType,String questionValue,String checkCode,HttpSession session){
        checkCode = checkCode.toUpperCase();
        Integer result = 0;
        Integer empId = ContextHolder.getSessionEmp().getEmpId();
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if(loginService.checkUser(empId,MD5.getMD5(oldPassword)) != 1){
            result = 1;
        }else {
            if(questionType == 2 &&  !empId.equals(Integer.parseInt(questionValue))){
                result = 2;
            }else if(questionType != 2 && loginService.checkQuestionAns(empId,questionType,questionValue) == 0){
                result = 2;
            }else if(!random.equals(checkCode)){
                result = 3;//验证码错误
            }else{
                loginService.updatePassword(empId,MD5.getMD5(newPassword1));
            }
        }
        return result;
    }
    /**
     * 插入一条公告
     * @param record
     * @author 何友池
     */
    public Integer saveNotice(SysNotice record){
        record.setEmpId(ContextHolder.getSessionEmp().getEmpId());
        return loginService.saveNotice(record);
    }

    /**
     * 查询一条公告
     * @param id
     * @return
     * @author 何友池
     */
    public SysNoticeExt getOneNotice(Integer id){
        return loginService.getOneNotice(id);
    }

    /**
     * 更新一条公告
     * @param record
     * @author 何友池
     */
    public void updateNotice(SysNotice record){
        loginService.updateNotice(record);
    }

    /**
     * 查询全部公告(0:不是此员工公告，1：是此员工公告)
     * @param pageRequest
     * @return
     * @author 何友池
     */
    public PageInfo<SysNoticeExt> getAllNoticeByEmpId(PageRequest pageRequest, String empName, Date releaseTimeStart, Date releaseTimeEnd){
        PageInfo page = PageUtil.getPage(pageRequest);
        return loginService.getAllNoticeByEmpId(page, ContextHolder.getSessionEmp().getEmpId(), empName, releaseTimeStart, releaseTimeEnd);
    }

    /**
     * 删除一条公告
     * @param id
     * @author 何友池
     */
    public void removeNotice(Integer id){
        loginService.removeNotice(id);
    }

    /**
     * 保存多条员工查看公告权限数据
     * @param empNoticList
     * @author 何友池
     */
    public void saveEmpNoticList(List<EmpNoticeKey> empNoticList){
        loginService.saveEmpNoticList(empNoticList);
    }

    /**
     * 获取树形非管理员员工信息集合
     * @return
     * @author 何友池
     */
    public PageInfo<EmpDicBasicInfoTreeExt> getNotAdminTree(){
        return loginService.getNotAdminTree();
    }

    /**
     * 查询此员工未读记录条数
     * @param empId
     * @return
     * @author 何友池
     */
    public Integer getNoRead(Integer empId){
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return loginService.getNoRead(empId);
    }
    /**
     * 查询此员工公告
     * @param empId
     * @return
     * @author 何友池
     */
    public List<EmpNoticeKeyExt> getNotice(Integer empId){
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return loginService.getNotice(empId);
    }
    /**
     * 查询此员工的公告
     * @return
     * @author 何友池
     */
    public PageInfo<EmpNoticeKeyExt> getEmpNotice(PageRequest pageRequest){
        PageInfo page = PageUtil.getPage(pageRequest);
        return loginService.getEmpNotice(page,ContextHolder.getSessionEmp().getEmpId());
    }

    /**
     * 更新一条员工公告记录到已阅
     * @param empId
     * @param noticeId
     * @author 何友池
     */
    public void updateIsReadNotice(Integer empId,Integer noticeId){
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        loginService.updateIsReadNotice(empId,noticeId);
    }

    /**
     * 获取登录次数
     * @param empId
     * @return
     * @author 何友池
     */
    public Integer getLoginCount(Integer empId){
        if(empId == null){
            empId = ContextHolder.getSessionEmp().getEmpId();
        }
        return loginService.getLoginCount(empId);
    }

    /**
     * 记录登录事件
     * @param ip
     * @param request
     * @author 何友池
     */
    public void recordLogin(String ip,HttpServletRequest request){
        HisLogin record = new HisLogin();
        ip = IpUtils.getIpAddr(request);
        record.setIp(ip);
        String address ;
        try {
            AddressUtils addressUtils = new AddressUtils();
            address = addressUtils.getAddresses("ip="+ip, "utf-8");
            record.setPlace(address);
        } catch (UnsupportedEncodingException e) {
            address = "远程ip识别无响应";
            record.setPlace(address);
        }
        record.setEmpId(ContextHolder.getSessionEmp().getEmpId());
        loginService.saveLogin(record);//记录登录时间人员ID
    }

    /**
     * 获取登录者账号密码管理员
     * @param empId
     * @return
     * @author 何友池
     */
    public EmpDicPassword getLoginPass(Integer empId){
        return loginService.getLoginPass(empId);
    }
}
