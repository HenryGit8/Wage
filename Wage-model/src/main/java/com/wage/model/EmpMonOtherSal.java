package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmpMonOtherSal extends EmpMonOtherSalKey implements Serializable {
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

    /**
     * 获取津贴  报销发票，补偿职工特殊或额外劳动消耗的津贴，保健性津贴，技术性津贴，年功性津贴等
     * @return EMP_MON_OTHER_SAL.MONTH_ALLOWANCE
     */
    public BigDecimal getMonthAllowance() {
        return monthAllowance;
    }

    /**
     * 设置津贴  报销发票，补偿职工特殊或额外劳动消耗的津贴，保健性津贴，技术性津贴，年功性津贴等
     * @param monthAllowance 津贴  报销发票，补偿职工特殊或额外劳动消耗的津贴，保健性津贴，技术性津贴，年功性津贴等
     */
    public void setMonthAllowance(BigDecimal monthAllowance) {
        this.monthAllowance = monthAllowance;
    }

    /**
     * 获取补贴  主要包括肉类等价格补贴、副食品价格补贴、粮价补贴、煤价补贴、房贴、水电贴等
     * @return EMP_MON_OTHER_SAL.MONTH_SUBSIDY
     */
    public BigDecimal getMonthSubsidy() {
        return monthSubsidy;
    }

    /**
     * 设置补贴  主要包括肉类等价格补贴、副食品价格补贴、粮价补贴、煤价补贴、房贴、水电贴等
     * @param monthSubsidy 补贴  主要包括肉类等价格补贴、副食品价格补贴、粮价补贴、煤价补贴、房贴、水电贴等
     */
    public void setMonthSubsidy(BigDecimal monthSubsidy) {
        this.monthSubsidy = monthSubsidy;
    }

    /**
     * 获取本月奖金(效益工资)   奖金即效益工资是根据企业的经济效益和职工实际完成的劳动的数量和质量支付给职工的工资。效益工资发挥着激励职工努力实干，多做贡献的作用。
     * @return EMP_MON_OTHER_SAL.MONTH_BONUS
     */
    public BigDecimal getMonthBonus() {
        return monthBonus;
    }

    /**
     * 设置本月奖金(效益工资)   奖金即效益工资是根据企业的经济效益和职工实际完成的劳动的数量和质量支付给职工的工资。效益工资发挥着激励职工努力实干，多做贡献的作用。
     * @param monthBonus 本月奖金(效益工资)   奖金即效益工资是根据企业的经济效益和职工实际完成的劳动的数量和质量支付给职工的工资。效益工资发挥着激励职工努力实干，多做贡献的作用。
     */
    public void setMonthBonus(BigDecimal monthBonus) {
        this.monthBonus = monthBonus;
    }

    /**
     * 获取加班费  小时*每小时加班费
     * @return EMP_MON_OTHER_SAL.OVERTIME_PAY
     */
    public BigDecimal getOvertimePay() {
        return overtimePay;
    }

    /**
     * 设置加班费  小时*每小时加班费
     * @param overtimePay 加班费  小时*每小时加班费
     */
    public void setOvertimePay(BigDecimal overtimePay) {
        this.overtimePay = overtimePay;
    }

    /**
     * 获取年终奖金  仅年终可用
     * @return EMP_MON_OTHER_SAL.YEAR_END_BONUS
     */
    public BigDecimal getYearEndBonus() {
        return yearEndBonus;
    }

    /**
     * 设置年终奖金  仅年终可用
     * @param yearEndBonus 年终奖金  仅年终可用
     */
    public void setYearEndBonus(BigDecimal yearEndBonus) {
        this.yearEndBonus = yearEndBonus;
    }

    /**
     * 获取总额
     * @return EMP_MON_OTHER_SAL.OTHER_SAL
     */
    public BigDecimal getOtherSal() {
        return otherSal;
    }

    /**
     * 设置总额
     * @param otherSal 总额
     */
    public void setOtherSal(BigDecimal otherSal) {
        this.otherSal = otherSal;
    }
}