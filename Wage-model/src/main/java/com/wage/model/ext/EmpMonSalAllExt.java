/**
 * EmpMonSalAllExt.java 2018/2/8 13:47
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.EmpMonSal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * File：EmpMonSalAllExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class EmpMonSalAllExt extends EmpMonSal implements Serializable {

    /**
     * 请假或旷工扣除工资 数据库字段是：REST_SALARY  <br>
     */
    private BigDecimal restSalary;

    /**
     * 罚金 数据库字段是：FINE_SALARY  <br>
     */
    private BigDecimal fineSalary;

    /**
     * 总额 数据库字段是：DEDUCT_SAL  <br>
     */
    private BigDecimal deductSal;

    /**
     * 津贴  报销发票，补偿职工特殊或额外劳动消耗的津贴，保健性津贴，技术性津贴，年功性津贴等 数据库字段是：MONTH_ALLOWANCE  <br>
     */
    private BigDecimal monthAllowance;

    /**
     * 补贴  主要包括肉类等价格补贴、副食品价格补贴、粮价补贴、煤价补贴、房贴、水电贴等 数据库字段是：MONTH_SUBSIDY  <br>
     */
    private BigDecimal monthSubsidy;

    /**
     * 本月奖金(效益工资)   奖金即效益工资是根据企业的经济效益和职工实际完成的劳动的数量和质量支付给职工的工资。效益工资发挥着激励职工努力实干，多做贡献的作用。 数据库字段是：MONTH_BONUS  <br>
     */
    private BigDecimal monthBonus;

    /**
     * 加班费  小时*每小时加班费 数据库字段是：OVERTIME_PAY  <br>
     */
    private BigDecimal overtimePay;

    /**
     * 年终奖金  仅年终可用 数据库字段是：YEAR_END_BONUS  <br>
     */
    private BigDecimal yearEndBonus;

    /**
     * 总额 数据库字段是：OTHER_SAL  <br>
     */
    private BigDecimal otherSal;

    public BigDecimal getRestSalary() {
        return restSalary;
    }

    public void setRestSalary(BigDecimal restSalary) {
        this.restSalary = restSalary;
    }

    public BigDecimal getFineSalary() {
        return fineSalary;
    }

    public void setFineSalary(BigDecimal fineSalary) {
        this.fineSalary = fineSalary;
    }

    public BigDecimal getDeductSal() {
        return deductSal;
    }

    public void setDeductSal(BigDecimal deductSal) {
        this.deductSal = deductSal;
    }

    public BigDecimal getMonthAllowance() {
        return monthAllowance;
    }

    public void setMonthAllowance(BigDecimal monthAllowance) {
        this.monthAllowance = monthAllowance;
    }

    public BigDecimal getMonthSubsidy() {
        return monthSubsidy;
    }

    public void setMonthSubsidy(BigDecimal monthSubsidy) {
        this.monthSubsidy = monthSubsidy;
    }

    public BigDecimal getMonthBonus() {
        return monthBonus;
    }

    public void setMonthBonus(BigDecimal monthBonus) {
        this.monthBonus = monthBonus;
    }

    public BigDecimal getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(BigDecimal overtimePay) {
        this.overtimePay = overtimePay;
    }

    public BigDecimal getYearEndBonus() {
        return yearEndBonus;
    }

    public void setYearEndBonus(BigDecimal yearEndBonus) {
        this.yearEndBonus = yearEndBonus;
    }

    public BigDecimal getOtherSal() {
        return otherSal;
    }

    public void setOtherSal(BigDecimal otherSal) {
        this.otherSal = otherSal;
    }

}
