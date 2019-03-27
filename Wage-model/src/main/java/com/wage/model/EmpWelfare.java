package com.wage.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmpWelfare implements Serializable {
    /**
     * 流水号 数据库字段是：ID  <br>
     */
    private Long id;

    /**
     * 员工编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 福利类型 数据库字段是：TYPE  <br>
     */
    private Short type;

    /**
     * 金额 数据库字段是：MONEY  <br>
     */
    private BigDecimal money;

    /**
     * 生效日期 数据库字段是：EFFECTIVE_DATE  <br>
     */
    private Date effectiveDate;

    /**
     * 原因 数据库字段是：REASON  <br>
     */
    private String reason;

    /**
     * 获取流水号
     * @return EMP_WELFARE.ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置流水号
     * @param id 流水号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取员工编号
     * @return EMP_WELFARE.EMP_ID
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置员工编号
     * @param empId 员工编号
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取福利类型
     * @return EMP_WELFARE.TYPE
     */
    public Short getType() {
        return type;
    }

    /**
     * 设置福利类型
     * @param type 福利类型
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * 获取金额
     * @return EMP_WELFARE.MONEY
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置金额
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取生效日期
     * @return EMP_WELFARE.EFFECTIVE_DATE
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * 设置生效日期
     * @param effectiveDate 生效日期
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     * 获取原因
     * @return EMP_WELFARE.REASON
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置原因
     * @param reason 原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}