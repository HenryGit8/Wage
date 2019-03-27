package com.wage.model;

import java.io.Serializable;

public class SysSet implements Serializable {
    /**
     * 系统属性名称 数据库字段是：SET_NAME  <br>
     */
    private String setName;

    /**
     * 系统属性值 数据库字段是：SET_VALUE  <br>
     */
    private String setValue;

    /**
     * null 数据库字段是：REMARKS  <br>
     */
    private String remarks;

    /**
     * 获取系统属性名称
     * @return SYS_SET.SET_NAME
     */
    public String getSetName() {
        return setName;
    }

    /**
     * 设置系统属性名称
     * @param setName 系统属性名称
     */
    public void setSetName(String setName) {
        this.setName = setName == null ? null : setName.trim();
    }

    /**
     * 获取系统属性值
     * @return SYS_SET.SET_VALUE
     */
    public String getSetValue() {
        return setValue;
    }

    /**
     * 设置系统属性值
     * @param setValue 系统属性值
     */
    public void setSetValue(String setValue) {
        this.setValue = setValue == null ? null : setValue.trim();
    }

    /**
     * 获取null
     * @return SYS_SET.REMARKS
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置null
     * @param remarks null
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}