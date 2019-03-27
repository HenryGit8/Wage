package com.wage.model;

import java.io.Serializable;
import java.util.Date;

public class HisOverKey implements Serializable {
    /**
     * 员工编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 加班开始时间 数据库字段是：OVER_TIME_START  <br>
     */
    private Date overTimeStart;

    /**
     * 获取员工编号
     * @return HIS_OVER.EMP_ID
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
     * 获取加班开始时间
     * @return HIS_OVER.OVER_TIME_START
     */
    public Date getOverTimeStart() {
        return overTimeStart;
    }

    /**
     * 设置加班开始时间
     * @param overTimeStart 加班开始时间
     */
    public void setOverTimeStart(Date overTimeStart) {
        this.overTimeStart = overTimeStart;
    }
}