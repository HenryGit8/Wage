/**
 * HisSalaryDataExt.java 2018/2/7 13:31
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.HisSalary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * File：HisSalaryDataExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class HisSalaryDataExt extends HisSalary implements Serializable {

    /**
     * 应发工资 数据库字段是：GROSS_PAY  <br>
     */
    private BigDecimal grossPayMax;
    /**
     * 应发工资 数据库字段是：GROSS_PAY  <br>
     */
    private BigDecimal grossPayMin;

    /**
     * 实发工资 数据库字段是：ACTUAL_PAY  <br>
     */
    private BigDecimal actualPayMax;
    /**
     * 实发工资 数据库字段是：ACTUAL_PAY  <br>
     */
    private BigDecimal actualPayMin;

    /**
     * 发放时间 数据库字段是：GRANT_TIME  <br>
     */
    private Date grantTimeStart;

    /**
     * 发放时间 数据库字段是：GRANT_TIME  <br>
     */
    private Date grantTimeEnd;

    /**
     * 个人所得税 数据库字段是：PERSON_INCOME_TAX  <br>
     */
    private BigDecimal personIncomeTaxMax;
    /**
     * 个人所得税 数据库字段是：PERSON_INCOME_TAX  <br>
     */
    private BigDecimal personIncomeTaxMin;

    private String empName;
    /**
     * 部门ID 数据库字段是：DEPART_ID  <br>
     */
    private Integer departId;

    private String sort;

    private String order;

    public BigDecimal getGrossPayMax() {
        return grossPayMax;
    }

    public void setGrossPayMax(BigDecimal grossPayMax) {
        this.grossPayMax = grossPayMax;
    }

    public BigDecimal getGrossPayMin() {
        return grossPayMin;
    }

    public void setGrossPayMin(BigDecimal grossPayMin) {
        this.grossPayMin = grossPayMin;
    }

    public BigDecimal getActualPayMax() {
        return actualPayMax;
    }

    public void setActualPayMax(BigDecimal actualPayMax) {
        this.actualPayMax = actualPayMax;
    }

    public BigDecimal getActualPayMin() {
        return actualPayMin;
    }

    public void setActualPayMin(BigDecimal actualPayMin) {
        this.actualPayMin = actualPayMin;
    }

    public Date getGrantTimeStart() {
        return grantTimeStart;
    }

    public void setGrantTimeStart(Date grantTimeStart) {
        this.grantTimeStart = grantTimeStart;
    }

    public Date getGrantTimeEnd() {
        return grantTimeEnd;
    }

    public void setGrantTimeEnd(Date grantTimeEnd) {
        this.grantTimeEnd = grantTimeEnd;
    }

    public BigDecimal getPersonIncomeTaxMax() {
        return personIncomeTaxMax;
    }

    public void setPersonIncomeTaxMax(BigDecimal personIncomeTaxMax) {
        this.personIncomeTaxMax = personIncomeTaxMax;
    }

    public BigDecimal getPersonIncomeTaxMin() {
        return personIncomeTaxMin;
    }

    public void setPersonIncomeTaxMin(BigDecimal personIncomeTaxMin) {
        this.personIncomeTaxMin = personIncomeTaxMin;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
