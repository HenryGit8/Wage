package com.wage.model;

import java.io.Serializable;

public class SysAuth implements Serializable {
    /**
     * 人员ID 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 可访问的视图（以逗号隔开） 数据库字段是：AUTH  <br>
     */
    private String auth;

    /**
     * 获取人员ID
     * @return SYS_AUTH.EMP_ID
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置人员ID
     * @param empId 人员ID
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取可访问的视图（以逗号隔开）
     * @return SYS_AUTH.AUTH
     */
    public String getAuth() {
        return auth;
    }

    /**
     * 设置可访问的视图（以逗号隔开）
     * @param auth 可访问的视图（以逗号隔开）
     */
    public void setAuth(String auth) {
        this.auth = auth == null ? null : auth.trim();
    }
}