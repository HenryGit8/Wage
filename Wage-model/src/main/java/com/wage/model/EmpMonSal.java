package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmpMonSal implements Serializable {
    /**
     * 员工编号  3位部门号+3位流水号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 基本工资 数据库字段是：EMP_BASIC_PAY  <br>
     */
    private BigDecimal empBasicPay;

    /**
     * 岗位（职务）工资或技能工资 数据库字段是：EMP_JOB_SALARY  <br>
     */
    private BigDecimal empJobSalary;

    /**
     * 总额 数据库字段是：MON_SAL  <br>
     */
    private BigDecimal monSal;

    /**
     * 获取员工编号  3位部门号+3位流水号
     * @return EMP_MON_SAL.EMP_ID
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置员工编号  3位部门号+3位流水号
     * @param empId 员工编号  3位部门号+3位流水号
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取基本工资
     * @return EMP_MON_SAL.EMP_BASIC_PAY
     */
    public BigDecimal getEmpBasicPay() {
        return empBasicPay;
    }

    /**
     * 设置基本工资
     * @param empBasicPay 基本工资
     */
    public void setEmpBasicPay(BigDecimal empBasicPay) {
        this.empBasicPay = empBasicPay;
    }

    /**
     * 获取岗位（职务）工资或技能工资
     * @return EMP_MON_SAL.EMP_JOB_SALARY
     */
    public BigDecimal getEmpJobSalary() {
        return empJobSalary;
    }

    /**
     * 设置岗位（职务）工资或技能工资
     * @param empJobSalary 岗位（职务）工资或技能工资
     */
    public void setEmpJobSalary(BigDecimal empJobSalary) {
        this.empJobSalary = empJobSalary;
    }

    /**
     * 获取总额
     * @return EMP_MON_SAL.MON_SAL
     */
    public BigDecimal getMonSal() {
        return monSal;
    }

    /**
     * 设置总额
     * @param monSal 总额
     */
    public void setMonSal(BigDecimal monSal) {
        this.monSal = monSal;
    }
}