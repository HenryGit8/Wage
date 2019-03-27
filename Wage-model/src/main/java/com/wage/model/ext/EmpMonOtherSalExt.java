/**
 * EmpMonOtherSalExt.java 2018/1/4 15:24
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.EmpMonOtherSal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * File：EmpMonOtherSalExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class EmpMonOtherSalExt extends EmpMonOtherSal implements Serializable {

    private String empName;

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
     * 获取请假或旷工扣除工资
     * @return EMP_MON_DEDUCT_SAL.REST_SALARY
     */
    public BigDecimal getRestSalary() {
        return restSalary;
    }

    /**
     * 设置请假或旷工扣除工资
     * @param restSalary 请假或旷工扣除工资
     */
    public void setRestSalary(BigDecimal restSalary) {
        this.restSalary = restSalary;
    }

    /**
     * 获取罚金
     * @return EMP_MON_DEDUCT_SAL.FINE_SALARY
     */
    public BigDecimal getFineSalary() {
        return fineSalary;
    }

    /**
     * 设置罚金
     * @param fineSalary 罚金
     */
    public void setFineSalary(BigDecimal fineSalary) {
        this.fineSalary = fineSalary;
    }

    /**
     * 获取总额
     * @return EMP_MON_DEDUCT_SAL.DEDUCT_SAL
     */
    public BigDecimal getDeductSal() {
        return deductSal;
    }

    /**
     * 设置总额
     * @param deductSal 总额
     */
    public void setDeductSal(BigDecimal deductSal) {
        this.deductSal = deductSal;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
