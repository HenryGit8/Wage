package com.wage.model;

import java.io.Serializable;

public class EmpDicPassword implements Serializable {
    /**
     * 编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 密码(加密) 数据库字段是：PASSWORD  <br>
     */
    private String password;

    /**
     * 是否管理员 数据库字段是：IS_ADMIN  <br>
     */
    private Short isAdmin;

    /**
     * 获取编号
     * @return EMP_DIC_PASSWORD.EMP_ID
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置编号
     * @param empId 编号
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取密码(加密)
     * @return EMP_DIC_PASSWORD.PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码(加密)
     * @param password 密码(加密)
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取是否管理员
     * @return EMP_DIC_PASSWORD.IS_ADMIN
     */
    public Short getIsAdmin() {
        return isAdmin;
    }

    /**
     * 设置是否管理员
     * @param isAdmin 是否管理员
     */
    public void setIsAdmin(Short isAdmin) {
        this.isAdmin = isAdmin;
    }
}