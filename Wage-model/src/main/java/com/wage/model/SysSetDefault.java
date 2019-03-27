package com.wage.model;

import java.io.Serializable;

public class SysSetDefault implements Serializable {
    /**
     * 系统属性名称 数据库字段是：SET_NAME  <br>
     */
    private String setName;

    /**
     * 系统属性值 数据库字段是：SET_VALUE  <br>
     */
    private String setValue;

    /**
     * 备注 数据库字段是：REMARKS  <br>
     */
    private String remarks;

    /**
     * 获取系统属性名称
     * @return SYS_SET_DEFAULT.SET_NAME
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
     * @return SYS_SET_DEFAULT.SET_VALUE
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
     * 获取备注
     * @return SYS_SET_DEFAULT.REMARKS
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}