/**
 * SystemSetContoller.java 2018/2/26 13:56
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.wage.model.*;
import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.handler.SystemSetHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * File：SystemSetContoller.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/sysSet")
public class SystemSetContoller {

    @Autowired
    SystemSetHandler systemSetHandler;

    /**
     * 获取系统设置
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllSysSet")
    @ResponseBody
    public Object getAllSysSet(){
        return systemSetHandler.getAllSysSet();
    }

    /**
     * 获取系统设置
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateSet")
    @ResponseBody
    public Object updateSet(@RequestBody List<SysSet> list){
        systemSetHandler.updateSet(list);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 更新至默认值
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateToDefault")
    @ResponseBody
    public Object updateToDefault(){
        systemSetHandler.updateToDefault();
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 查询所有登录记录
     * @param pageRequest
     * @param empName
     * @param loginTimeStart
     * @param loginTimeEnd
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllLoginHis")
    @ResponseBody
    public Object getAllLoginHis(PageRequest pageRequest, String empName, Date loginTimeStart, Date loginTimeEnd){
        return new JsonResult(DescribableEnum.SUCCESS,systemSetHandler.getAllLoginHis(pageRequest, empName, loginTimeStart, loginTimeEnd));
    }

    /**
     * 查询所有额外假期数据
     * @param pageRequest
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllHoliday")
    @ResponseBody
    public Object getAllHoliday(PageRequest pageRequest){
        return new JsonResult(DescribableEnum.SUCCESS,systemSetHandler.getAllHoliday(pageRequest));
    }

    /**
     * 删除一条假期
     * @param dateTime
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/deleteHoliday")
    @ResponseBody
    public Object deleteHoliday(Date dateTime){
        systemSetHandler.deleteHoliday(dateTime);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 插入一条假期信息
     * @param record
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveHoliday")
    @ResponseBody
    public Object saveHoliday(SysHoliday record){
        systemSetHandler.saveHoliday(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 获取所有部门数据
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllDepart")
    @ResponseBody
    public Object getAllDepart(){
        return new JsonResult(DescribableEnum.SUCCESS,systemSetHandler.getAllDepart());
    }

    /**
     * 删除一条部门信息
     * @param key
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/deleteEmpDepart")
    @ResponseBody
    public Object deleteEmpDepart(EmpDepartmentKey key){
        systemSetHandler.deleteEmpDepart(key);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 保存一条部门数据
     * 返回值：0正常，1重复主键
     * @param record
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveEmpDepart")
    @ResponseBody
    public Object saveEmpDepart(EmpDepartment record){
        return new JsonResult(DescribableEnum.SUCCESS,systemSetHandler.saveEmpDepart(record));
    }

    /**
     * 更新部门薪资
     * @param record
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateDepart")
    @ResponseBody
    public Object updateDepart(EmpDepartment record){
        systemSetHandler.updateDepart(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 任命为管理员
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateAddAdmin")
    @ResponseBody
    public Object updateAddAdmin(Integer empId){
        systemSetHandler.updateAddAdmin(empId);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 撤销任命管理员
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateRemoveAdmin")
    @ResponseBody
    public Object updateRemoveAdmin(Integer empId){
        systemSetHandler.updateRemoveAdmin(empId);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 查询登录者登录记录
     * @param pageRequest
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getLoginHisByEmpId")
    @ResponseBody
    public Object getLoginHisByEmpId(PageRequest pageRequest, Integer empId){
        return new JsonResult(DescribableEnum.SUCCESS,systemSetHandler.getLoginHisByEmpId(pageRequest,empId));
    }

    /**
     * 获取所有滚轮图数据
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllNewImg")
    @ResponseBody
    public Object getAllNewImg(){
        return systemSetHandler.getAllNewImg();
    }

    /**
     * 获取所有非滚轮图数据
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllNewNoImg")
    @ResponseBody
    public Object getAllNewNoImg(){
        return systemSetHandler.getAllNewNoImg();
    }

    /**
     * 重置员工密码
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/updateEmpPass")
    @ResponseBody
    public Object updateEmpPass(Integer empId){
        return systemSetHandler.updateEmpPass(empId);
    }

    /**
     * 查询已注册用户
     * @param pageRequest
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getIsRegister")
    @ResponseBody
    public Object getIsRegister(PageRequest pageRequest,String empName){
        return new JsonResult(DescribableEnum.SUCCESS,systemSetHandler.getIsRegister(pageRequest, empName));
    }

    /**
     * 获取所有非图片新闻列表
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllNewsList")
    @ResponseBody
    public Object getAllNewsList(){
        return new JsonResult(DescribableEnum.SUCCESS,systemSetHandler.getAllNewsList());
    }

    /**
     * 获取所有图片新闻列表
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllNewsImgList")
    @ResponseBody
    public Object getAllNewsImgList(){
        return new JsonResult(DescribableEnum.SUCCESS,systemSetHandler.getAllNewsImgList());
    }
    /**
     * 保存所有推荐新闻
     * @param list
     * @author 何友池
     */
    @RequestMapping(value = "/saveAllNewNoImg")
    @ResponseBody
    public Object saveAllNewNoImg(@RequestBody List<SysEmpNewSet> list){
        systemSetHandler.saveAllNewNoImg(list);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 保存全部新闻滚轮图列表
     * @param list
     * @author 何友池
     */
    @RequestMapping(value = "/saveAllNewImg")
    @ResponseBody
    public Object saveAllNewImg(@RequestBody List<SysEmpNewSet> list){
        systemSetHandler.saveAllNewImg(list);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

}
