package com.wage.model;

import java.io.Serializable;

public class EmpDepartmentKey implements Serializable {
    /**
     * 部门ID 数据库字段是：DEPART_ID  <br>
     */
    private Integer departId;

    /**
     * 薪资等级 数据库字段是：DEPART_RAND  <br>
     */
    private Short departRand;

    /**
     * 获取部门ID
     * @return EMP_DEPARTMENT.DEPART_ID
     */
    public Integer getDepartId() {
        return departId;
    }

    /**
     * 设置部门ID
     * @param departId 部门ID
     */
    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    /**
     * 获取薪资等级
     * @return EMP_DEPARTMENT.DEPART_RAND
     */
    public Short getDepartRand() {
        return departRand;
    }

    /**
     * 设置薪资等级
     * @param departRand 薪资等级
     */
    public void setDepartRand(Short departRand) {
        this.departRand = departRand;
    }
}