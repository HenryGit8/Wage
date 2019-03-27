/**
 * LoginController.java 2017/12/15 10:32
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.wage.model.*;
import com.wage.model.ext.EmpDicBasicInfoSExt;
import com.wage.web.common.ContextHolder;
import com.wage.web.constant.ParamConstants;
import com.wage.web.handler.LoginHandler;
import com.wage.web.common.JsonResult ;
import com.wage.web.constant.DescribableEnum ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * File：LoginController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginHandler loginHandler;

    @RequestMapping(value = "/saveS")
    @ResponseBody
    public Object saveS(Integer s) {
        ContextHolder.getSession().setAttribute("1", s);
        return "sus";
    }

    @RequestMapping(value = "/getS")
    @ResponseBody
    public Object getS() {
        return ContextHolder.getSession().getAttribute("1");
    }
    /**
     * 判断登录者身份
     * @param empId
     * @param password
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getLoginMan")
    @ResponseBody
    public Object getLoginMan(Integer empId, String password,String checkCode,HttpSession session,String ip,HttpServletRequest request,@RequestParam(value = "error", required = false) String error){
        System.out.println(error);
        return loginHandler.getLoginMan(empId, password, checkCode, session, ip,request);
        /*if(result == 1){
            return new ModelAndView("redirect:/gotoMain");
        }else if(result == 0){
            return result;
            *//*return "redirect:/gotoMain";*//*
        }else {
            return result;
        }*/
    }

    @RequestMapping(value = "/checkLoginCode")
    @ResponseBody
    public Object checkLogin(String checkCode,HttpSession session){
        checkCode = checkCode.toUpperCase();
        //从session中获取随机数
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        Integer result = 2;
        if(!random.equals(checkCode)){
            result = 3;//验证码错误
        }/*else {
            if (role.equals(ParamConstants.ADMINP)){
                result = 1;
            }else if(role.equals(ParamConstants.USERP)){
                result = 0;
            }
        }*/
        return result;
    }

    @RequestMapping(value = "/rediectLogin")
    @ResponseBody
    public Object rediectLogin(@RequestParam(value = "role", required = false)String role){
        Integer result = 2;
        if (role.equals(ParamConstants.ADMINP)){
            result = 1;
        }else if(role.equals(ParamConstants.USERP)){
            result = 0;
        }
        return result;
    }

    /**
     * 插入注册信息(0:不存在此用户,1:用户名已存在,2:员工表中无此员工信息)
     * @param empId
     * @param password
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveRegister")
    @ResponseBody
    public Object saveRegister(Integer empId,String password,String checkCode,HttpSession session){
        return loginHandler.saveRegister(empId, password, checkCode, session);
    }

    /**
     * 保存用户信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @RequestMapping(value = "/saveUserInfo")
    @ResponseBody
    public Object saveUserInfo( EmpDicBasicInfo empDicBasicInfo){
        loginHandler.saveUserInfo(empDicBasicInfo);
        return new JsonResult(DescribableEnum.SUCCESS);
    }
    /**
     * 检查用户名是否存在
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/remoteUsername")
    @ResponseBody
    public Object remoteUsername(Integer empId){
        return loginHandler.remoteUsername(empId);
    }

    /**
     * 登出
     * @author 何友池
     */
    @RequestMapping(value = "/doLogout")
    public ModelAndView doLogout(){
        try {
            loginHandler.doLogout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/");
    }

    /**
     * 查询人员权限
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAuthByEmpId")
    @ResponseBody
    public Object getAuthByEmpId(Integer empId){
        return loginHandler.getAuthByEmpId(empId);
    }

    /**
     * 查询未匹配人员权限数据
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getNoMatch")
    @ResponseBody
    public Object getNoMatch(Integer empId){
        return new JsonResult(DescribableEnum.SUCCESS,loginHandler.getNoMatch(empId));
    }

    /**
     * 查询已匹配人员权限数据
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getMatch")
    @ResponseBody
    public Object getMatch(Integer empId){
        return new JsonResult(DescribableEnum.SUCCESS,loginHandler.getMatch(empId));
    }

    /**
     * 查询所有管理员
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAdmin")
    @ResponseBody
    public Object getAdmin(){
        return new JsonResult(DescribableEnum.SUCCESS,loginHandler.getAdmin());
    }

    /**
     * 查询所有非管理员
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getNotAdmin")
    @ResponseBody
    public Object getNotAdmin(){
        return new JsonResult(DescribableEnum.SUCCESS,loginHandler.getNotAdmin());
    }

    /**
     * 新增此管理员一条权限
     * @param empId
     * @param id
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateAddAuth")
    @ResponseBody
    public Object updateAddAuth(Integer empId,Integer id){
        loginHandler.updateAddAuth(empId,id);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 删除此管理员一条权限
     * @param empId
     * @param id
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateDeleAuth")
    @ResponseBody
    public Object updateDeleAuth(Integer empId,Integer id){
        loginHandler.updateDeleAuth(empId,id);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 设置头像
     * @param empHeadImg
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveEmpImgMd")
    @ResponseBody
    public Object saveEmpImgMd(String empHeadImg,Integer empId){
        loginHandler.saveEmpImgMd(empHeadImg, empId);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 获取头像md5
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getEmpImgMd")
    @ResponseBody
    public Object getEmpImgMd(Integer empId){
        return loginHandler.getEmpImgMd(empId);
    }

    /**
     * 获取登录者个人信息
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getLoginInfo")
    @ResponseBody
    public Object getLoginInfo(){
        return loginHandler.getLoginInfo();
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
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public Object updatePassword(String oldPassword,String newPassword1,Integer questionType,String questionValue,String checkCode,HttpSession session){
        return loginHandler.updatePassword(oldPassword, newPassword1, questionType, questionValue, checkCode, session);
    }

    /**
     * 插入一条公告
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveNotice")
    @ResponseBody
    public Object saveNotice(SysNotice record){
        return loginHandler.saveNotice(record);
    }

    /**
     * 查询一条公告
     * @param id
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getOneNotice")
    @ResponseBody
    public Object getOneNotice(Integer id){
        return loginHandler.getOneNotice(id);
    }

    /**
     * 更新一条公告
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateNotice")
    @ResponseBody
    public Object updateNotice(SysNotice record){
        loginHandler.updateNotice(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 查询全部公告(0:不是此员工公告，1：是此员工公告)
     * @param pageRequest
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllNoticeByEmpId")
    @ResponseBody
    public Object getAllNoticeByEmpId(PageRequest pageRequest, String empName, Date releaseTimeStart, Date releaseTimeEnd){
        return new JsonResult(DescribableEnum.SUCCESS,loginHandler.getAllNoticeByEmpId(pageRequest, empName, releaseTimeStart, releaseTimeEnd));
    }

    /**
     * 删除一条公告
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/removeNotice")
    @ResponseBody
    public Object removeNotice(Integer id){
        loginHandler.removeNotice(id);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 保存多条员工查看公告权限数据
     * @param empNoticList
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveEmpNoticList")
    @ResponseBody
    public Object saveEmpNoticList(@RequestBody List<EmpNoticeKey> empNoticList){
        loginHandler.saveEmpNoticList(empNoticList);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 获取树形非管理员员工信息集合
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getNotAdminTree")
    @ResponseBody
    public Object getNotAdminTree(){
        return new JsonResult(DescribableEnum.SUCCESS,loginHandler.getNotAdminTree());
    }

    /**
     * 查询此员工未读记录条数
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getNoRead")
    @ResponseBody
    public Object getNoRead(Integer empId){
        return loginHandler.getNoRead(empId);
    }

    /**
     * 查询此员工公告
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getNotice")
    @ResponseBody
    public Object getNotice(Integer empId){
        return loginHandler.getNotice(empId);
    }
    /**
     * 查询此员工的公告
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getEmpNotice")
    @ResponseBody
    public Object getEmpNotice(PageRequest pageRequest){
        return new JsonResult(DescribableEnum.SUCCESS,loginHandler.getEmpNotice(pageRequest));
    }

    /**
     * 更新一条员工公告记录到已阅
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateIsReadNotice")
    @ResponseBody
    public Object updateIsReadNotice(Integer empId,Integer noticeId){
        loginHandler.updateIsReadNotice(empId,noticeId);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 获取登录次数
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getLoginCount")
    @ResponseBody
    public Object getLoginCount(Integer empId){
        return loginHandler.getLoginCount(empId);
    }
}
