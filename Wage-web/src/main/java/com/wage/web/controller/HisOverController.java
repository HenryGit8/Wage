/**
 * HisOverController.java 2018/1/17 14:17
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.controller;

import com.wage.model.EmpMonSal;
import com.wage.model.HisOver;
import com.wage.model.HisOverKey;
import com.wage.model.PageRequest;
import com.wage.model.ext.HisOverOExt;
import com.wage.web.common.JsonResult;
import com.wage.web.constant.DescribableEnum;
import com.wage.web.handler.HisOverHandler;
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
 * File：HisOverController.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Controller
@RequestMapping("/hisOver")
public class HisOverController {

    @Autowired
    HisOverHandler hisOverHandler;

    /**
     * 根据员工编号查询加班记录表
     * @param pageRequest
     * @param empId
     * @param overTimeStartMin
     * @param overTimeStartMax
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getByEmpid")
    @ResponseBody
    public Object getByEmpid(PageRequest pageRequest, Integer empId, Date overTimeStartMin, Date overTimeStartMax){
        return new JsonResult(DescribableEnum.SUCCESS,hisOverHandler.getByEmpid(pageRequest, empId, overTimeStartMin, overTimeStartMax));
    }

    /**
     * 更新加班记录表
     * @param record
     * @author 何友池
     */
    @RequestMapping(value = "/updateHisOver")
    @ResponseBody
    public Object updateHisOver(HisOverOExt record){
        hisOverHandler.updateHisOver(record);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 获取加班记录列表
     * @param empId
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getOverByEmpid")
    @ResponseBody
    public Object getOverByEmpid(Integer empId){
        return hisOverHandler.getOverByEmpid(empId);
    }

    /**
     * 保存一条加班信息
     * @param hisOver
     * @author 何友池
     */
    @RequestMapping(value = "/saveHisOver")
    @ResponseBody
    public Object saveHisOver(HisOver hisOver){
        hisOverHandler.saveHisOver(hisOver);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 删除一条加班信息
     * @param key
     * @author 何友池
     */
    @RequestMapping(value = "/removeHisOver")
    @ResponseBody
    public Object removeHisOver(HisOverKey key){
        hisOverHandler.removeHisOver(key);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 根据是否已审核查询
     * @param pageRequest
     * @param isCheck
     * @return
     * @author 何友池
     */
    @RequestMapping(value = "/getByEmpidC")
    @ResponseBody
    public Object getByEmpidC(PageRequest pageRequest, Short isCheck){
        return new JsonResult(DescribableEnum.SUCCESS,hisOverHandler.getByEmpidC(pageRequest, isCheck));
    }

    /**
     * 更新加班审核结果
     * @param empId
     * @param overTimeStart
     * @param isCheck
     * @author 何友池
     */
    @RequestMapping(value = "/updateOverApply")
    @ResponseBody
    public Object updateOverApply(Integer empId,Date overTimeStart,Short isCheck){
        hisOverHandler.updateOverApply(empId, overTimeStart, isCheck);
        return new JsonResult(DescribableEnum.SUCCESS);
    }

    /**
     * 判断此员工的加班时间是否与数据库重叠
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
        return hisOverHandler.checkIsOverLap(empId, startTime, endTime);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空值
    }
}
