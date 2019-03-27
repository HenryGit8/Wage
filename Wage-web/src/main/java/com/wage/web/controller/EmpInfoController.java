/**
 * EmpInfoController.java 2017/12/25 11:16
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.github.pagehelper.PageInfo;
import com.wage.model.EmpDicBasicInfo;
import com.wage.model.PageRequest;
import com.wage.model.ext.EmpDicBasicInfoSExt;
import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.handler.EmpInfoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File：EmpInfoController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/empInfo")
public class EmpInfoController {

    @Autowired
    EmpInfoHandler empInfoHandler;

    /**
     * 根据查询条件分页查询员工信息表
     * @param pageRequest
     * @param empDicBasicInfo
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getBySearch")
    @ResponseBody
    public Object getBySearch(PageRequest pageRequest, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order){
        return new JsonResult(DescribableEnum.SUCCESS,empInfoHandler.getBySearch(pageRequest,empDicBasicInfo,sort,order));
    }

    /**
     * 删除一条数据
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/removeInfo")
    @ResponseBody
    public Object removeInfo(Integer empId){
        empInfoHandler.removeInfo(empId);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 保存一条信息
     * @param empDicBasicInfo
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveInfo")
    @ResponseBody
    public Object saveInfo(EmpDicBasicInfo empDicBasicInfo,BigDecimal empBasicPay,BigDecimal empJobSalary,String headMd){
        empInfoHandler.saveInfo(empDicBasicInfo, empBasicPay, empJobSalary ,headMd);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 修改人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @RequestMapping(value = "/updateInfo")
    @ResponseBody
    public Object updateInfo(EmpDicBasicInfo empDicBasicInfo){
        empInfoHandler.updateInfo(empDicBasicInfo);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 获取部门信息集合
     * @param departId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getDepartMap")
    @ResponseBody
    public Object getDepartMap(Integer departId){
        return empInfoHandler.getDepartMap(departId);
    }

    /**
     * 获取树形员工信息集合
     * @param pageRequest
     * @param empDicBasicInfo
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getEmpTree")
    @ResponseBody
    public Object getEmpTree(PageRequest pageRequest, EmpDicBasicInfoSExt empDicBasicInfo, String sort, String order){
        return new JsonResult(DescribableEnum.SUCCESS,empInfoHandler.getEmpTree(pageRequest,empDicBasicInfo,sort,order));
    }

    /**
     * 修改当前登录人员人员信息
     * @param empDicBasicInfo
     * @author 何友池
     */
    @RequestMapping(value = "/updateLoginInfo")
    @ResponseBody
    public Object updateLoginInfo(EmpDicBasicInfo empDicBasicInfo){
        empInfoHandler.updateLoginInfo(empDicBasicInfo);
        return new JsonResult(DescribableEnum.SUCCESS);
    }
    /**
     * 获取登录者信息
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getLoginInfo")
    @ResponseBody
    public Object getLoginInfo(Integer empId){
        return empInfoHandler.getLoginInfo(empId);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }
}
