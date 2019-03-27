package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HisWelfare implements Serializable {
    /**
     * 流水号  自增长ID 数据库字段是：SERIAL_ID  <br>
     */
    private Long serialId;

    /**
     * 申请日期 数据库字段是：APPLY_TIME  <br>
     */
    private Date applyTime;

    /**
     * 员工编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 申请原因 数据库字段是：APPLY_REASON  <br>
     */
    private String applyReason;

    /**
     * 总金额 数据库字段是：WELFARE_TOTAL  <br>
     */
    private BigDecimal welfareTotal;

    /**
     * 是否批准  0：未审批，1：批准，2：未批准 数据库字段是：IS_ALLOW  <br>
     */
    private Short isAllow;

    /**
     * 福利类型 1：津贴 2.补贴 数据库字段是：WELFARE_TYPE  <br>
     */
    private Short welfareType;

    /**
     * 审批人员 数据库字段是：APPROVER_EMPID  <br>
     */
    private Integer approverEmpid;

    /**
     * 审批时间 数据库字段是：APPROVER_TIME  <br>
     */
    private Date approverTime;

    /**
     * 获取流水号  自增长ID
     * @return HIS_WELFARE.SERIAL_ID
     */
    public Long getSerialId() {
        return serialId;
    }

    /**
     * 设置流水号  自增长ID
     * @param serialId 流水号  自增长ID
     */
    public void setSerialId(Long serialId) {
        this.serialId = serialId;
    }

    /**
     * 获取申请日期
     * @return HIS_WELFARE.APPLY_TIME
     */
    public Date getApplyTime() {
        return applyTime;
    }

    /**
     * 设置申请日期
     * @param applyTime 申请日期
     */
    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    /**
     * 获取员工编号
     * @return HIS_WELFARE.EMP_ID
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
     * 获取申请原因
     * @return HIS_WELFARE.APPLY_REASON
     */
    public String getApplyReason() {
        return applyReason;
    }

    /**
     * 设置申请原因
     * @param applyReason 申请原因
     */
    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
    }

    /**
     * 获取总金额
     * @return HIS_WELFARE.WELFARE_TOTAL
     */
    public BigDecimal getWelfareTotal() {
        return welfareTotal;
    }

    /**
     * 设置总金额
     * @param welfareTotal 总金额
     */
    public void setWelfareTotal(BigDecimal welfareTotal) {
        this.welfareTotal = welfareTotal;
    }

    /**
     * 获取是否批准  0：未审批，1：批准，2：未批准
     * @return HIS_WELFARE.IS_ALLOW
     */
    public Short getIsAllow() {
        return isAllow;
    }

    /**
     * 设置是否批准  0：未审批，1：批准，2：未批准
     * @param isAllow 是否批准  0：未审批，1：批准，2：未批准
     */
    public void setIsAllow(Short isAllow) {
        this.isAllow = isAllow;
    }

    /**
     * 获取福利类型 1：津贴 2.补贴
     * @return HIS_WELFARE.WELFARE_TYPE
     */
    public Short getWelfareType() {
        return welfareType;
    }

    /**
     * 设置福利类型 1：津贴 2.补贴
     * @param welfareType 福利类型 1：津贴 2.补贴
     */
    public void setWelfareType(Short welfareType) {
        this.welfareType = welfareType;
    }

    /**
     * 获取审批人员
     * @return HIS_WELFARE.APPROVER_EMPID
     */
    public Integer getApproverEmpid() {
        return approverEmpid;
    }

    /**
     * 设置审批人员
     * @param approverEmpid 审批人员
     */
    public void setApproverEmpid(Integer approverEmpid) {
        this.approverEmpid = approverEmpid;
    }

    /**
     * 获取审批时间
     * @return HIS_WELFARE.APPROVER_TIME
     */
    public Date getApproverTime() {
        return approverTime;
    }

    /**
     * 设置审批时间
     * @param approverTime 审批时间
     */
    public void setApproverTime(Date approverTime) {
        this.approverTime = approverTime;
    }
}