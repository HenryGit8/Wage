package com.wage.model;

import java.io.Serializable;

public class HisSalaryKey implements Serializable {
    /**
     * 员工编号  3位部门号+3位流水号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 年月  201712形式 数据库字段是：YEAR_MONTH  <br>
     */
    private Integer yearMonth;

    /**
     * 获取员工编号  3位部门号+3位流水号
     * @return HIS_SALARY.EMP_ID
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
     * 获取年月  201712形式
     * @return HIS_SALARY.YEAR_MONTH
     */
    public Integer getYearMonth() {
        return yearMonth;
    }

    /**
     * 设置年月  201712形式
     * @param yearMonth 年月  201712形式
     */
    public void setYearMonth(Integer yearMonth) {
        this.yearMonth = yearMonth;
    }
}