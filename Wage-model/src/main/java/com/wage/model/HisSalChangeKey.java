package com.wage.model;

import java.io.Serializable;

public class HisSalChangeKey implements Serializable {
    /**
     * 员工编号  3位部门号+3位流水号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 年月 数据库字段是：CHANGE_TIME  <br>
     */
    private Integer changeTime;

    /**
     * 获取员工编号  3位部门号+3位流水号
     * @return HIS_SAL_CHANGE.EMP_ID
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
     * 获取年月
     * @return HIS_SAL_CHANGE.CHANGE_TIME
     */
    public Integer getChangeTime() {
        return changeTime;
    }

    /**
     * 设置年月
     * @param changeTime 年月
     */
    public void setChangeTime(Integer changeTime) {
        this.changeTime = changeTime;
    }
}