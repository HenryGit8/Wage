/**
 * HisSalaryHandler.java 2018/2/7 14:28
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.wage.api.HisSalaryService;
import com.wage.model.HisSalary;
import com.wage.model.PageRequest;
import com.wage.model.dto.EasyuiComboBoxDto;
import com.wage.model.dto.Man;
import com.wage.model.ext.HisSalaryDataExt;
import com.wage.model.ext.HisSalaryExt;
import com.wage.util.PoiExcelExport;
import com.wage.web.common.ContextHolder;
import com.wage.web.common.PageUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * File：HisSalaryHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class HisSalaryHandler {

    @Reference
    HisSalaryService hisSalaryService;

    /**
     * 根据查询条件查询月工资明细
     * @param pageRequest
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    public PageInfo<HisSalaryExt> getBySearch(PageRequest pageRequest, HisSalaryDataExt hisSalaryDataExt){
        PageInfo page = PageUtil.getPage(pageRequest);
        return hisSalaryService.getBySearch(page, hisSalaryDataExt);
    }

    /**
     * 根据查询条件查询登录者月工资明细
     * @param pageRequest
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    public PageInfo<HisSalaryExt> getLoginData(PageRequest pageRequest, HisSalaryDataExt hisSalaryDataExt){
        PageInfo page = PageUtil.getPage(pageRequest);
        hisSalaryDataExt.setEmpName(ContextHolder.getSessionEmp().getEmpId().toString());
        return hisSalaryService.getBySearch(page, hisSalaryDataExt);
    }

    /**
     * 更新并计算五险一金及税率
     * @param yearMonth
     * @author 何友池
     */
    public void updateHisSalary(Integer yearMonth){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        if(yearMonth == null){
            Calendar date = Calendar.getInstance();
            date.setTime(new Date());
            date.add(date.MONTH, -1);
            yearMonth = Integer.parseInt(sdf.format(date.getTime()));
        }
        hisSalaryService.updateHisSalary(yearMonth);
    }
    /**
     * 更新一条数据
     * @param record
     * @author 何友池
     */
    public void updateData(HisSalary record){
        hisSalaryService.updateData(record);
    }
    /**
     * 获取月集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    public List<EasyuiComboBoxDto> getAllMonth(){
        return hisSalaryService.getAllMonth();
    }

    /**
     * 获取部门集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    public List<EasyuiComboBoxDto> getAllDepart(){
        return hisSalaryService.getAllDepart();
    }

    /**
     * 获取并生成excel
     * @param hisSalaryDataExt
     * @param url
     * @author 何友池
     */
    public Integer getListBySearch(HisSalaryDataExt hisSalaryDataExt,String url,String name){
        url = url.replaceAll("\\\\","/");
        File file = new File(url);
// 判断文件路径是否存在
        if(!file.exists()) {
            return 1;
        }else{
            url = url+name;
            List<HisSalaryExt> list = hisSalaryService.getListBySearch(hisSalaryDataExt);
            PoiExcelExport pee = new PoiExcelExport(url,"sheet1");
            String titleColumn[] = {"empId","empName","yearMonth","monSal","otherSal","deductSal","grossPay","actualPay","penInsurPay","medInsurPay","unempInsurPay","basHousProFundPay","penInsurPayC","medInsurPayC","unempInsurPayC","basHousProFundPayC","empInjuryInsurPayC","birthInsurC","pensonPay","companyPay","empBankCard","isGrant","grantTime","grantName","remarks","departName"};
            String titleName[] = {"员工编号","员工姓名","年月","固定工资","额外工资","扣除工资","应发工资","实发工资","养老保险金","医疗保险金","失业保险金","基本住房公积金","养老保险金(单位)","医疗保险金(单位)","失业保险金(单位)","基本住房公积金(单位)","工伤保险金(单位)","生育保险金(单位)","个人应缴五险一金总额","企业应缴五险一金总额","银行卡号","是否发放","发放时间","发放人员","备注","部门名称"};
            int titleSize[] = {13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13,13};
            //其他设置 set方法可全不调用
            pee.wirteExcel(titleColumn, titleName, titleSize, list);
            return 0;
        }
    }

    /**
     * 更新是否发放
     * @param empId
     * @param yearMonth
     * @param isGrant
     * @author 何友池
     */
    public void updateIsGrant(Integer empId,Integer yearMonth,Short isGrant,Date grantTime,String remarks){
        HisSalary record = new HisSalary();
        record.setEmpId(empId);
        record.setYearMonth(yearMonth);
        record.setIsGrant(isGrant);
        record.setRemarks(remarks);
        if(grantTime == null){
            record.setGrantTime(new Date());
        }else{
            record.setGrantTime(grantTime);
        }
        record.setGrantEmpid(ContextHolder.getSessionEmp().getEmpId());
        hisSalaryService.updateData(record);
    }


    /**
     * 根据查询条件查询本月工资明细
     * @param pageRequest
     * @return
     * @author 何友池
     */
    public PageInfo<HisSalaryExt> getIsGrant(PageRequest pageRequest){
        PageInfo page = PageUtil.getPage(pageRequest);
        HisSalaryDataExt hisSalaryDataExt = new HisSalaryDataExt();
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        hisSalaryDataExt.setYearMonth(Integer.parseInt(sdf.format(new Date())));*/
        hisSalaryDataExt.setIsGrant((short)0);
        return hisSalaryService.getBySearch(page, hisSalaryDataExt);
    }
    /**
     * 查询图表数据集
     * @return
     * @author 何友池
     */
    public Map<String,Object> getDataChart(){
        return hisSalaryService.getDataChart();
    }
    /**
     * 查询统计表数据
     * @param yearMonth
     * @return
     * @author 何友池
     */
    public Map<String,Object> getTotalByMonth(Integer yearMonth){
        return hisSalaryService.getTotalByMonth(yearMonth);
    }

    /**
     * 更新上个月数据并初始化本月数据
     * @param yearMonth
     * @author 何友池
     */
    public void updateNewDeductOther(Integer yearMonth){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        if(yearMonth == null){
            Calendar date = Calendar.getInstance();
            date.setTime(new Date());
            date.add(date.MONTH, -1);
            yearMonth = Integer.parseInt(sdf.format(date.getTime()));
            hisSalaryService.updateHisSalary(yearMonth);
            hisSalaryService.updateNewDeductOther(Integer.parseInt(sdf.format(new Date())));
        }
    }
}
