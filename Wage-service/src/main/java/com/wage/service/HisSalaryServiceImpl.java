/**
 * HisSalaryServiceImpl.java 2018/2/7 14:29
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.wage.api.HisSalaryService;
import com.wage.contract.HisSalaryHandler;
import com.wage.model.HisSalary;
import com.wage.model.dto.EasyuiComboBoxDto;
import com.wage.model.ext.HisSalaryDataExt;
import com.wage.model.ext.HisSalaryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * File：HisSalaryServiceImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Service
public class HisSalaryServiceImpl implements HisSalaryService {

    @Autowired
    HisSalaryHandler hisSalaryHandler;

    /**
     * 根据查询条件查询月工资明细
     * @param page
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisSalaryExt> getBySearch(PageInfo page, HisSalaryDataExt hisSalaryDataExt){
        return hisSalaryHandler.getBySearch(page, hisSalaryDataExt);
    }

    /**
     * 更新并计算五险一金及税率
     * @param yearMonth
     * @author 何友池
     */
    @Override
    public void updateHisSalary(Integer yearMonth){
        if(yearMonth == null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            Calendar date = Calendar.getInstance();
            date.setTime(new Date());
            date.add(date.MONTH, -1);
            yearMonth = Integer.parseInt(sdf.format(date.getTime()));
        }
        hisSalaryHandler.updateHisSalary(yearMonth);
    }
    /**
     * 更新一条数据
     * @param record
     * @author 何友池
     */
    @Override
    public void updateData(HisSalary record){
        hisSalaryHandler.updateData(record);
    }
    /**
     * 获取月集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    @Override
    public List<EasyuiComboBoxDto> getAllMonth(){
        return hisSalaryHandler.getAllMonth();
    }

    /**
     * 获取部门集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    @Override
    public List<EasyuiComboBoxDto> getAllDepart(){
        return hisSalaryHandler.getAllDepart();
    }

    /**
     * 根据查询条件查询月工资明细
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    @Override
    public List<HisSalaryExt> getListBySearch(HisSalaryDataExt hisSalaryDataExt){
        return hisSalaryHandler.getListBySearch(hisSalaryDataExt);
    }
    /**
     * 查询图表数据集
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getDataChart(){
        return hisSalaryHandler.getDataChart();
    }
    /**
     * 查询统计表数据
     * @param yearMonth
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getTotalByMonth(Integer yearMonth){
        return hisSalaryHandler.getTotalByMonth(yearMonth);
    }

    /**
     * 插入初始化数据
     * @param yearMonth
     * @author 何友池
     */
    @Override
    public void updateNewDeductOther(Integer yearMonth){
        hisSalaryHandler.updateNewDeductOther(yearMonth);
    }
}
