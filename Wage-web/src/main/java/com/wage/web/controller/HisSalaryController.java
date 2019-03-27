/**
 * HisSalaryController.java 2018/2/7 14:28
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.github.pagehelper.PageInfo;
import com.wage.model.HisSalary;
import com.wage.model.PageRequest;
import com.wage.model.ext.HisOverOExt;
import com.wage.model.ext.HisSalaryDataExt;
import com.wage.model.ext.HisSalaryExt;
import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.handler.HisSalaryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * File：HisSalaryController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/hisSalary")
public class HisSalaryController {

    @Autowired
    HisSalaryHandler hisSalaryHandler;

    /**
     * 根据查询条件查询月工资明细
     * @param pageRequest
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getBySearch")
    @ResponseBody
    public Object getBySearch(PageRequest pageRequest, HisSalaryDataExt hisSalaryDataExt){
        return new JsonResult(DescribableEnum.SUCCESS,hisSalaryHandler.getBySearch(pageRequest,hisSalaryDataExt));
    }

    /**
     * 根据查询条件查询登录者月工资明细
     * @param pageRequest
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getLoginData")
    @ResponseBody
    public Object getLoginData(PageRequest pageRequest, HisSalaryDataExt hisSalaryDataExt){
        return new JsonResult(DescribableEnum.SUCCESS,hisSalaryHandler.getLoginData(pageRequest,hisSalaryDataExt));
    }

    /**
     * 更新并计算五险一金及税率
     * @param yearMonth
     * @author 何友池
     */
    @RequestMapping(value = "/updateHisSalary")
    @ResponseBody
    public Object updateHisSalary(Integer yearMonth){
        hisSalaryHandler.updateHisSalary(yearMonth);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 更新一条数据
     * @param record
     * @author 何友池
     */
    @RequestMapping(value = "/updateData")
    @ResponseBody
    public Object updateData(HisSalary record){
        hisSalaryHandler.updateData(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 获取月集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllMonth")
    @ResponseBody
    public Object getAllMonth(){
        return hisSalaryHandler.getAllMonth();
    }

    /**
     * 获取部门集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getAllDepart")
    @ResponseBody
    public Object getAllDepart(){
        return hisSalaryHandler.getAllDepart();
    }

    /**
     * 生成excel
     * @param hisSalaryDataExt
     * @param url
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getExcel")
    @ResponseBody
    public Object getExcel(HisSalaryDataExt hisSalaryDataExt,String url,String name){
        return new JsonResult(DescribableEnum.SUCCESS,hisSalaryHandler.getListBySearch(hisSalaryDataExt,url,name));
    }

    /**
     * 更新是否发放
     * @param empId
     * @param yearMonth
     * @param isGrant
     * @author 何友池
     */
    @RequestMapping(value = "/updateIsGrant")
    @ResponseBody
    public Object updateIsGrant(Integer empId,Integer yearMonth,Short isGrant,Date grantTime,String remarks){
        hisSalaryHandler.updateIsGrant(empId,yearMonth,isGrant,grantTime,remarks);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 根据查询条件查询月工资明细
     * @param pageRequest
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getIsGrant")
    @ResponseBody
    public Object getIsGrant(PageRequest pageRequest){
        return new JsonResult(DescribableEnum.SUCCESS,hisSalaryHandler.getIsGrant(pageRequest));
    }

    /**
     * 查询图表数据集
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getDataChart")
    @ResponseBody
    public Object getDataChart(){
        return hisSalaryHandler.getDataChart();
    }

    /**
     * 查询统计表数据
     * @param yearMonth
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getTotalByMonth")
    @ResponseBody
    public Object getTotalByMonth(Integer yearMonth){
        return hisSalaryHandler.getTotalByMonth(yearMonth);
    }
}
