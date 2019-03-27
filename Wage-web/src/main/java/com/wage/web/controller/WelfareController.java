/**
 * WelfareController.java 2018/1/24 14:19
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.wage.model.EmpWelfare;
import com.wage.model.HisWelfare;
import com.wage.model.PageRequest;
import com.wage.model.ext.HisOverOExt;
import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.handler.WelfareHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * File：WelfareController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/welfare")
public class WelfareController {

    @Autowired
    WelfareHandler welfareHandler;

    /**
     * 根据查询条件查询福利表
     * @param pageRequest
     * @param empId
     * @param applyTimeMin
     * @param applyTimeMax
     * @param isAllow
     * @param welfareType
     * @param welfareTotalMin
     * @param welfareTotalMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getBySearch")
    @ResponseBody
    public Object getBySearch(PageRequest pageRequest, Integer empId, Date applyTimeMin, Date applyTimeMax, Short isAllow, Short welfareType,
                              BigDecimal welfareTotalMin, BigDecimal welfareTotalMax, String sort, String order){
        return new JsonResult(DescribableEnum.SUCCESS,welfareHandler.getBySearch(pageRequest,empId, applyTimeMin, applyTimeMax, isAllow,  welfareType, welfareTotalMin, welfareTotalMax, sort, order));
    }
    /**
     * 审批一条福利表数据
     * @param serialId
     * @param isAllow
     * @author 何友池
     */
    @RequestMapping(value = "/updateWelfare")
    @ResponseBody
    public Object updateWelfare(Long serialId,Short isAllow){
        welfareHandler.updateWelfare(serialId, isAllow);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 插入一条福利数据
     * @param record
     * @author 何友池
     */
    @RequestMapping(value = "/saveWelfare")
    @ResponseBody
    public Object saveWelfare(HisWelfare record){
        welfareHandler.saveWelfare(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 插入多条福利数据
     * @param list
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveWelfareList")
    @ResponseBody
    public Object saveWelfareList(@RequestBody List<HisWelfare> list){
        welfareHandler.saveWelfareList(list);
        return new JsonResult(DescribableEnum.SUCCESS);
    }
    /**
     * 根据条件查找员工福利记录
     * @param pageRequest
     * @param empId
     * @param type
     * @param effectiveDateMin
     * @param effectiveDateMax
     * @param moneyMin
     * @param moneyMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getEmpWelBySearch")
    @ResponseBody
    public Object getEmpWelBySearch(PageRequest pageRequest, Integer empId, String type, Date effectiveDateMin, Date effectiveDateMax,
                                    BigDecimal moneyMin, BigDecimal moneyMax, String sort, String order){
        return new JsonResult(DescribableEnum.SUCCESS,welfareHandler.getEmpWelBySearch(pageRequest,empId, type, effectiveDateMin, effectiveDateMax,moneyMin, moneyMax, sort, order));
    }
    /**
     * 更新一条员工福利记录
     * @param record
     * @author 何友池
     */
    @RequestMapping(value = "/updateEmpWelfare")
    @ResponseBody
    public Object updateEmpWelfare(EmpWelfare record){
        welfareHandler.updateEmpWelfare(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 插入一条员工福利记录
     * @param record
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveEmpWelfare")
    @ResponseBody
    public Object saveEmpWelfare(EmpWelfare record){
        welfareHandler.saveEmpWelfare(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 插入多条员工福利记录
     * @param list
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/saveEmpWelfareList")
    @ResponseBody
    public Object saveEmpWelfareList(@RequestBody List<EmpWelfare> list){
        welfareHandler.saveEmpWelfareList(list);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 删除一条员工福利记录
     * @param id
     * @author 何友池
     */
    @RequestMapping(value = "/removeEmpWelfare")
    @ResponseBody
    public Object removeEmpWelfare(Long id){
        welfareHandler.removeEmpWelfare(id);
        return new JsonResult(DescribableEnum.SUCCESS);
    }
    /**
     * 根据条件查找登录员工福利记录
     * @param pageRequest
     * @param type
     * @param effectiveDateMin
     * @param effectiveDateMax
     * @param moneyMin
     * @param moneyMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getLoginEmpWel")
    @ResponseBody
    public Object getLoginEmpWel(PageRequest pageRequest, String type, Date effectiveDateMin, Date effectiveDateMax,
                                    BigDecimal moneyMin, BigDecimal moneyMax, String sort, String order){
        return new JsonResult(DescribableEnum.SUCCESS,welfareHandler.getLoginEmpWel(pageRequest, type, effectiveDateMin, effectiveDateMax,moneyMin, moneyMax, sort, order));
    }

    /**
     * 根据查询条件查询登录者福利表
     * @param pageRequest
     * @param applyTimeMin
     * @param applyTimeMax
     * @param isAllow
     * @param welfareType
     * @param welfareTotalMin
     * @param welfareTotalMax
     * @param sort
     * @param order
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getLoginBySearch")
    @ResponseBody
    public Object getLoginBySearch(PageRequest pageRequest, Date applyTimeMin, Date applyTimeMax, Short isAllow, Short welfareType,
                              BigDecimal welfareTotalMin, BigDecimal welfareTotalMax, String sort, String order){
        return new JsonResult(DescribableEnum.SUCCESS,welfareHandler.getLoginBySearch(pageRequest, applyTimeMin, applyTimeMax, isAllow,  welfareType, welfareTotalMin, welfareTotalMax, sort, order));
    }
}
