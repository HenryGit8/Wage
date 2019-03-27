package com.wage.model;

import java.io.Serializable;
import java.util.Date;

public class HisLogin implements Serializable {
    /**
     * 流水号 数据库字段是：ID  <br>
     */
    private Long id;

    /**
     * 登录人员ID 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 登录时间 数据库字段是：LOGIN_TIME  <br>
     */
    private Date loginTime;

    /**
     * 登录IP 数据库字段是：IP  <br>
     */
    private String ip;

    /**
     * 地点 数据库字段是：PLACE  <br>
     */
    private String place;

    /**
     * 获取流水号
     * @return HIS_LOGIN.ID
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
     * 获取登录人员ID
     * @return HIS_LOGIN.EMP_ID
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置登录人员ID
     * @param empId 登录人员ID
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取登录时间
     * @return HIS_LOGIN.LOGIN_TIME
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登录IP
     * @return HIS_LOGIN.IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置登录IP
     * @param ip 登录IP
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取地点
     * @return HIS_LOGIN.PLACE
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置地点
     * @param place 地点
     */
    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }
}