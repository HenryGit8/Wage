package com.wage.model;

import java.io.Serializable;
import java.util.Date;

public class HisRevokeApply implements Serializable {
    /**
     * 流水号 数据库字段是：ID  <br>
     */
    private Integer id;

    /**
     * 员工编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 请假时间 数据库字段是：REST_TIME  <br>
     */
    private Date restTime;

    /**
     * 申请时间 数据库字段是：APPLY_TIME  <br>
     */
    private Date applyTime;

    /**
     * 是否允许：0未审批 1通过 2不通过 数据库字段是：IS_ALLOW  <br>
     */
    private Short isAllow;

    /**
     * 获取流水号
     * @return HIS_REVOKE_APPLY.ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置流水号
     * @param id 流水号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取员工编号
     * @return HIS_REVOKE_APPLY.EMP_ID
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
     * 获取请假时间
     * @return HIS_REVOKE_APPLY.REST_TIME
     */
    public Date getRestTime() {
        return restTime;
    }

    /**
     * 设置请假时间
     * @param restTime 请假时间
     */
    public void setRestTime(Date restTime) {
        this.restTime = restTime;
    }

    /**
     * 获取申请时间
     * @return HIS_REVOKE_APPLY.APPLY_TIME
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置申请时间
     * @param applyTime 申请时间
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取是否允许：0未审批 1通过 2不通过
     * @return HIS_REVOKE_APPLY.IS_ALLOW
     */
    public Short getIsAllow() {
        return isAllow;
    }

    /**
     * 设置是否允许：0未审批 1通过 2不通过
     * @param isAllow 是否允许：0未审批 1通过 2不通过
     */
    public void setIsAllow(Short isAllow) {
        this.isAllow = isAllow;
    }
}