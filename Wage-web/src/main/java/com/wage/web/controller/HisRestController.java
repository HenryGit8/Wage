/**
 * HisRestController.java 2018/1/10 10:09
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.github.pagehelper.PageInfo;
import com.wage.model.*;
import com.wage.model.ext.EmpDicBasicInfoExt;
import com.wage.model.ext.EmpDicBasicInfoSExt;
import com.wage.model.ext.HisRestExt;
import com.wage.web.common.JsonResult;
import com.wage.web.common.PageUtil;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.handler.HisRestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * File：HisRestController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/hisRest")
public class HisRestController {

    @Autowired
    HisRestHandler hisRestHandler;

    /**
     * 根据员工编号查询请假记录
     * @param pageRequest
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getByEmpId")
    @ResponseBody
    public Object getByEmpid(PageRequest pageRequest, Integer empId, Date restStartTimeMin,Date restStartTimeMax){
        return new JsonResult(DescribableEnum.SUCCESS,hisRestHandler.getByEmpid(pageRequest, empId, restStartTimeMin, restStartTimeMax));
    }

    /**
     * 获取请假记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getRestByEmpid")
    @ResponseBody
    public Object getRestByEmpid(Integer empId){
        return hisRestHandler.getRestByEmpid( empId);
    }

    /**
     * 更新一条请假信息数据
     * @param record
     * @author 何友池
     */
    @RequestMapping(value = "/updateHisRest")
    @ResponseBody
    public Object updateHisRest(HisRestExt record){
        hisRestHandler.updateHisRest(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 删除一条请假记录
     * @param key
     * @author 何友池
     */
    @RequestMapping(value = "/removeHisRest")
    @ResponseBody
    public Object removeHisRest(HisRestKey key){
        hisRestHandler.removeHisRest(key);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 新增一条请假记录
     * @param record
     * @author 何友池
     */
    @RequestMapping(value = "/saveHisRest")
    @ResponseBody
    public Object saveHisRest(HisRest record){
        hisRestHandler.saveHisRest(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 查询全部撤回请假申请数据
     * @param pageRequest
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getRevokeApply")
    @ResponseBody
    public Object getRevokeApply(PageRequest pageRequest,Short isAllow){
        return new JsonResult(DescribableEnum.SUCCESS,hisRestHandler.getRevokeApply(pageRequest, isAllow));
    }

    /**
     * 更新审批结果
     * @param isAllow
     * @param id
     * @author 何友池
     */
    @RequestMapping(value = "/updateRevokeApply")
    @ResponseBody
    public Object updateRevokeApply(Short isAllow,Integer id){
        hisRestHandler.updateRevokeApply(isAllow, id);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 判断此员工的请假时间是否与数据库重叠
     * true:重叠
     * false:不重叠
     * @param empId
     * @param startTime
     * @param endTime
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/checkIsOverLap")
    @ResponseBody
    public Object checkIsOverLap(Integer empId,Date startTime,Date endTime){
        return hisRestHandler.checkIsOverLap(empId, startTime, endTime);
    }

    /**
     * 提交撤回请假申请
     * @param record
     * @author 何友池
     */
    @RequestMapping(value = "/saveRestApply")
    @ResponseBody
    public Object saveRestApply(HisRevokeApply record){
        hisRestHandler.saveRestApply(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }
}
