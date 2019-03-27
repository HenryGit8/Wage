package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmpDepartment extends EmpDepartmentKey implements Serializable {
    /**
     * 部门名称 数据库字段是：DEPART_NAME  <br>
     */
    private String departName;

    /**
     * 部门岗位薪资 数据库字段是：DEPART_WAGE  <br>
     */
    private BigDecimal departWage;

    /**
     * 获取部门名称
     * @return EMP_DEPARTMENT.DEPART_NAME
     */
    public String getDepartName() {
        return departName;
    }

    /**
     * 设置部门名称
     * @param departName 部门名称
     */
    public void setDepartName(String departName) {
        this.departName = departName == null ? null : departName.trim();
    }

    /**
     * 获取部门岗位薪资
     * @return EMP_DEPARTMENT.DEPART_WAGE
     */
    public BigDecimal getDepartWage() {
        return departWage;
    }

    /**
     * 设置部门岗位薪资
     * @param departWage 部门岗位薪资
     */
    public void setDepartWage(BigDecimal departWage) {
        this.departWage = departWage;
    }
}