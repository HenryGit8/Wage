/**
 * HisSalaryHandlerImpl.java 2018/2/7 14:30
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.biz.handler;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wage.biz.mybatis.*;
import com.wage.contract.HisSalaryHandler;
import com.wage.model.EmpDepartment;
import com.wage.model.EmpMonDeductSal;
import com.wage.model.HisSalary;
import com.wage.model.SysSet;
import com.wage.model.dto.EasyuiComboBoxDto;
import com.wage.model.dto.HisSalaryChartDto;
import com.wage.model.dto.PerModleDto;
import com.wage.model.ext.HisSalaryDataExt;
import com.wage.model.ext.HisSalaryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File：HisSalaryHandlerImpl.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class HisSalaryHandlerImpl implements HisSalaryHandler {

    @Autowired
    HisSalaryMapper hisSalaryMapper;

    @Autowired
    HisSalChangeMapper hisSalChangeMapper;

    @Autowired
    SysSetMapper sysSetMapper;

    @Autowired
    EmpDepartmentMapper empDepartmentMapper;

    @Autowired
    EmpMonDeductSalMapper empMonDeductSalMapper;

    @Autowired
    EmpMonOtherSalMapper empMonOtherSalMapper;

    /**
     * 根据查询条件查询月工资明细
     * @param page
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    @Override
    public PageInfo<HisSalaryExt> getBySearch(PageInfo page,HisSalaryDataExt hisSalaryDataExt){
        PageHelper.startPage(page);
        List<HisSalaryExt> list = hisSalaryMapper.findBySearch(hisSalaryDataExt);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 更新并计算五险一金及税率
     * @param yearMonth
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateHisSalary(Integer yearMonth){
        List<SysSet> setList = sysSetMapper.findAll();
        BigDecimal personI = new BigDecimal(setList.get(9).getSetValue());
        BigDecimal personIC = new BigDecimal(setList.get(10).getSetValue());
        BigDecimal medI = new BigDecimal(setList.get(5).getSetValue());
        BigDecimal medIC = new BigDecimal(setList.get(6).getSetValue());
        BigDecimal unempI = new BigDecimal(setList.get(27).getSetValue());
        BigDecimal unempIC = new BigDecimal(setList.get(28).getSetValue());
        BigDecimal hoursePF = new BigDecimal(setList.get(0).getSetValue());
        BigDecimal hoursePFC = new BigDecimal(setList.get(1).getSetValue());
        BigDecimal birthIC = new BigDecimal(setList.get(2).getSetValue());
        BigDecimal empInjuryIC = new BigDecimal(setList.get(3).getSetValue());
        BigDecimal threshold = new BigDecimal(setList.get(26).getSetValue());
        BigDecimal taxRate0 = new BigDecimal(setList.get(19).getSetValue());
        BigDecimal taxRate1500 = new BigDecimal(setList.get(20).getSetValue());
        BigDecimal taxRate35000 = new BigDecimal(setList.get(21).getSetValue());
        BigDecimal taxRate4500 = new BigDecimal(setList.get(22).getSetValue());
        BigDecimal taxRate55000 = new BigDecimal(setList.get(23).getSetValue());
        BigDecimal taxRate80000 = new BigDecimal(setList.get(24).getSetValue());
        BigDecimal taxRate9000 = new BigDecimal(setList.get(25).getSetValue());
        BigDecimal quickDeduct0 = new BigDecimal(setList.get(11).getSetValue());
        BigDecimal quickDeduct1500 = new BigDecimal(setList.get(12).getSetValue());
        BigDecimal quickDeduct35000 = new BigDecimal(setList.get(13).getSetValue());
        BigDecimal quickDeduct4500 = new BigDecimal(setList.get(14).getSetValue());
        BigDecimal quickDeduct55000 = new BigDecimal(setList.get(15).getSetValue());
        BigDecimal quickDeduct80000 = new BigDecimal(setList.get(16).getSetValue());
        BigDecimal quickDeduct9000 = new BigDecimal(setList.get(17).getSetValue());
        hisSalChangeMapper.insertByMonth(yearMonth);
        hisSalaryMapper.insertHisSalary(yearMonth);
        List<HisSalary> list = hisSalaryMapper.findByMonth(yearMonth);
        List<HisSalary> resultList = new ArrayList<>();
        for (HisSalary hisSalary : list) {
            HisSalary h = new HisSalary();
            BigDecimal monSal = hisSalary.getMonSal();
            BigDecimal otherSal = hisSalary.getOtherSal();
            BigDecimal deductSal = hisSalary.getDeductSal();
            BigDecimal grossPay = monSal.add(otherSal).subtract(deductSal);//应发工资
            BigDecimal personITotal = grossPay.multiply(personI).setScale(1, RoundingMode.HALF_UP);
            BigDecimal personICTotal = grossPay.multiply(personIC).setScale(1, RoundingMode.HALF_UP);
            BigDecimal medITotal = grossPay.multiply(medI).setScale(1, RoundingMode.HALF_UP);
            BigDecimal medICTotal = grossPay.multiply(medIC).setScale(1, RoundingMode.HALF_UP);
            BigDecimal unempITotal = grossPay.multiply(unempI).setScale(1, RoundingMode.HALF_UP);
            BigDecimal unempICTotal = grossPay.multiply(unempIC).setScale(1, RoundingMode.HALF_UP);
            BigDecimal hoursePFTotal = grossPay.multiply(hoursePF).setScale(1, RoundingMode.HALF_UP);
            BigDecimal hoursePFCTotal = grossPay.multiply(hoursePFC).setScale(1, RoundingMode.HALF_UP);
            BigDecimal birthICTotal = grossPay.multiply(birthIC).setScale(1, RoundingMode.HALF_UP);
            BigDecimal empInjuryICTotal = grossPay.multiply(empInjuryIC).setScale(1, RoundingMode.HALF_UP);
            BigDecimal perTotal = personITotal.add(medITotal).add(unempITotal).add(hoursePFTotal).setScale(1, RoundingMode.HALF_UP);//个人需缴纳五险一金部分
            BigDecimal companyTotal = personICTotal.add(medICTotal).add(unempICTotal).add(hoursePFCTotal).add(birthICTotal).add(empInjuryICTotal).setScale(1, RoundingMode.HALF_UP);//企业需缴纳五险一金部分
            BigDecimal grossPayDeduct = grossPay.subtract(perTotal).setScale(1, RoundingMode.HALF_UP);//应发工资扣除个人缴纳五险一金
            BigDecimal surplus = grossPayDeduct.subtract(threshold).setScale(1, RoundingMode.HALF_UP);//减去起征点需缴纳税率部分
            BigDecimal tax = new BigDecimal(0);//个人所得税
            if(surplus.compareTo(BigDecimal.ZERO) == 1){
                if(surplus.compareTo(new BigDecimal(1500)) != 1){
                    tax = surplus.multiply(taxRate0).subtract(quickDeduct0).setScale(1, RoundingMode.HALF_UP);
                }else if(surplus.compareTo(new BigDecimal(4500)) != 1){
                    tax = surplus.multiply(taxRate1500).subtract(quickDeduct1500).setScale(1, RoundingMode.HALF_UP);
                }else if(surplus.compareTo(new BigDecimal(9000)) != 1){
                    tax = surplus.multiply(taxRate4500).subtract(quickDeduct4500).setScale(1, RoundingMode.HALF_UP);
                }else if(surplus.compareTo(new BigDecimal(35000)) != 1){
                    tax = surplus.multiply(taxRate9000).subtract(quickDeduct9000).setScale(1, RoundingMode.HALF_UP);
                }else if(surplus.compareTo(new BigDecimal(55000)) != 1){
                    tax = surplus.multiply(taxRate35000).subtract(quickDeduct35000).setScale(1, RoundingMode.HALF_UP);
                }else if(surplus.compareTo(new BigDecimal(80000)) != 1){
                    tax = surplus.multiply(taxRate55000).subtract(quickDeduct55000).setScale(1, RoundingMode.HALF_UP);
                }else{
                    tax = surplus.multiply(taxRate80000).subtract(quickDeduct80000).setScale(1, RoundingMode.HALF_UP);
                }
            }
            BigDecimal actualPay = grossPayDeduct.subtract(tax).setScale(1, RoundingMode.HALF_UP);
            h.setActualPay(actualPay);
            h.setBasHousProFundPay(hoursePFTotal);
            h.setBasHousProFundPayC(hoursePFCTotal);
            h.setBirthInsurC(birthICTotal);
            h.setEmpInjuryInsurPayC(empInjuryICTotal);
            h.setPenInsurPay(personITotal);
            h.setPenInsurPayC(personICTotal);
            h.setMedInsurPay(medITotal);
            h.setMedInsurPayC(medICTotal);
            h.setPersonIncomeTax(tax);
            h.setUnempInsurPayC(unempICTotal);
            h.setUnempInsurPay(unempITotal);
            h.setYearMonth(hisSalary.getYearMonth());
            h.setEmpId(hisSalary.getEmpId());
            h.setPensonPay(perTotal);
            h.setCompanyPay(companyTotal);
            resultList.add(h);
        }
        hisSalaryMapper.updateHisSalaryList(resultList);
    }

    /**
     * 更新一条数据
     * @param record
     * @author 何友池
     */
    @Override
    public void updateData(HisSalary record){
        hisSalaryMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 获取月集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    @Override
    public List<EasyuiComboBoxDto> getAllMonth(){
        List<Integer> monthList = hisSalaryMapper.findAllMonth();
        List<EasyuiComboBoxDto> dtoList = new ArrayList<>();
        EasyuiComboBoxDto e = new EasyuiComboBoxDto();
        e.setValue("");
        e.setText("全部");
        dtoList.add(e);
        for (Integer integer : monthList) {
            EasyuiComboBoxDto d = new EasyuiComboBoxDto();
            d.setText(integer.toString());
            d.setValue(integer.toString());
            dtoList.add(d);
        }
        return dtoList;
    }

    /**
     * 获取部门集合返回easyui下拉框数据
     * @return
     * @author 何友池
     */
    @Override
    public List<EasyuiComboBoxDto> getAllDepart(){
        List<EmpDepartment> departList = empDepartmentMapper.findDepartList();
        List<EasyuiComboBoxDto> dtoList = new ArrayList<>();
        EasyuiComboBoxDto e = new EasyuiComboBoxDto();
        e.setValue("");
        e.setText("全部");
        dtoList.add(e);
        for (EmpDepartment empDepartment : departList) {
            EasyuiComboBoxDto d = new EasyuiComboBoxDto();
            d.setValue(empDepartment.getDepartId().toString());
            d.setText(empDepartment.getDepartName());
            dtoList.add(d);
        }
        return dtoList;
    }
    /**
     * 根据查询条件查询月工资明细
     * @param hisSalaryDataExt
     * @return
     * @author 何友池
     */
    @Override
    public List<HisSalaryExt> getListBySearch(HisSalaryDataExt hisSalaryDataExt){
        List<HisSalaryExt> list = hisSalaryMapper.findBySearch(hisSalaryDataExt);
        return list;
    }

    /**
     * 查询图表数据集
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getDataChart(){
        Map<String,Object> result = new HashMap<>();
        List<Integer> yearMonthList = new ArrayList<>();
        List<BigDecimal> monSalSumList = new ArrayList<>();
        List<BigDecimal> otherSalSumList = new ArrayList<>();
        List<BigDecimal> deductSalSumList = new ArrayList<>();
        List<BigDecimal> grossPaySumList = new ArrayList<>();
        List<BigDecimal> actualPaySumList = new ArrayList<>();
        List<HisSalaryChartDto> list = hisSalaryMapper.findDataChart();
        for (HisSalaryChartDto hisSalaryChartDto : list) {
            yearMonthList.add(hisSalaryChartDto.getYearMonth());
            monSalSumList.add(hisSalaryChartDto.getMonSalSum());
            otherSalSumList.add(hisSalaryChartDto.getOtherSalSum());
            deductSalSumList.add(hisSalaryChartDto.getDeductSalSum());
            grossPaySumList.add(hisSalaryChartDto.getGrossPaySum());
            actualPaySumList.add(hisSalaryChartDto.getActualPaySum());
        }
        result.put("yearMonthList",yearMonthList);
        result.put("monSalSumList",monSalSumList);
        result.put("otherSalSumList",otherSalSumList);
        result.put("deductSalSumList",deductSalSumList);
        result.put("grossPaySumList",grossPaySumList);
        result.put("actualPaySumList",actualPaySumList);
        List<HisSalaryChartDto> pieList = hisSalaryMapper.findDataPie();
        List<PerModleDto> list1 = new ArrayList<>();
        PerModleDto monSal = new PerModleDto();
        monSal.setName("本月固定工资");
        monSal.setY(pieList.get(0).getMonSalSum());
        PerModleDto otherSal = new PerModleDto();
        otherSal.setName("本月其他工资");
        otherSal.setY(pieList.get(0).getOtherSalSum());
        PerModleDto deductSal = new PerModleDto();
        deductSal.setName("本月扣除工资");
        deductSal.setY(pieList.get(0).getDeductSalSum());
        list1.add(monSal);
        list1.add(otherSal);
        list1.add(deductSal);
        result.put("peiListData",list1);
        return result;
    }

    /**
     * 查询统计表数据
     * @param yearMonth
     * @return
     * @author 何友池
     */
    @Override
    public Map<String,Object> getTotalByMonth(Integer yearMonth){
        Map<String,Object> result = new HashMap<>();
        if(yearMonth != null){
            result.put("totalData",hisSalaryMapper.findTotalData(yearMonth));
        }
        result.put("monthsData",hisSalaryMapper.findAllMonth());
        return result;
    }

    /**
     * 插入初始化数据
     * @param yearMonth
     * @author 何友池
     */
    @Override
    @Transactional
    public void updateNewDeductOther(Integer yearMonth){
        empMonDeductSalMapper.insertInitialData(yearMonth);
        empMonOtherSalMapper.insertInitialData(yearMonth);
    }
}
