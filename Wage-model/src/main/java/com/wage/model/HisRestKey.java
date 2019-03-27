package com.wage.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class HisRestKey implements Serializable {
    /**
     * 员工编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 请假开始时间 数据库字段是：REST_START_TIME  <br>
     */
    private Date restStartTime;

    /**
     * 获取员工编号
     * @return HIS_REST.EMP_ID
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
     * 获取请假开始时间
     * @return HIS_REST.REST_START_TIME
     */
    public Date getRestStartTime() {
        return restStartTime;
    }

    /**
     * 设置请假开始时间
     * @param restStartTime 请假开始时间
     */
    public void setRestStartTime(Date restStartTime) {
        this.restStartTime = restStartTime;
    }
}