package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmpDicBasicInfo implements Serializable {
    /**
     * 员工编号  3位部门号+3位流水号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 员工姓名 数据库字段是：EMP_NAME  <br>
     */
    private String empName;

    /**
     * 性别 数据库字段是：EMP_SEX  <br>
     */
    private String empSex;

    /**
     * 住址 数据库字段是：EMP_ADDRESS  <br>
     */
    private String empAddress;

    /**
     * 联系电话 数据库字段是：EMP_TELL  <br>
     */
    private BigDecimal empTell;

    /**
     * 银行卡号  用于发放工资的银行卡号 数据库字段是：EMP_BANK_CARD  <br>
     */
    private String empBankCard;

    /**
     * 身份证号 数据库字段是：EMP_IDNUM  <br>
     */
    private String empIdnum;

    /**
     * 学历 数据库字段是：EMP_EDU  <br>
     */
    private String empEdu;

    /**
     * 毕业院校 数据库字段是：EMP_SCHOOL  <br>
     */
    private String empSchool;

    /**
     * 入职时间 数据库字段是：CREATE_TIME  <br>
     */
    private Date createTime;

    /**
     * 创建人员  只能是管理员编号 数据库字段是：CREATE_EMPID  <br>
     */
    private Integer createEmpid;

    /**
     * 修改时间 数据库字段是：MODIFY_TIME  <br>
     */
    private Date modifyTime;

    /**
     * 修改人员  只能是管理员编号 数据库字段是：MODIFY_EMPID  <br>
     */
    private Integer modifyEmpid;

    /**
     * 部门ID 数据库字段是：DEPART_ID  <br>
     */
    private Integer departId;

    /**
     * 薪资等级 数据库字段是：DEPART_RAND  <br>
     */
    private Short departRand;

    /**
     * 获取员工编号  3位部门号+3位流水号
     * @return EMP_DIC_BASIC_INFO.EMP_ID
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置员工编号  3位部门号+3位流水号
     * @param empId 员工编号  3位部门号+3位流水号
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取员工姓名
     * @return EMP_DIC_BASIC_INFO.EMP_NAME
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 设置员工姓名
     * @param empName 员工姓名
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    /**
     * 获取性别
     * @return EMP_DIC_BASIC_INFO.EMP_SEX
     */
    public String getEmpSex() {
        return empSex;
    }

    /**
     * 设置性别
     * @param empSex 性别
     */
    public void setEmpSex(String empSex) {
        this.empSex = empSex == null ? null : empSex.trim();
    }

    /**
     * 获取住址
     * @return EMP_DIC_BASIC_INFO.EMP_ADDRESS
     */
    public String getEmpAddress() {
        return empAddress;
    }

    /**
     * 设置住址
     * @param empAddress 住址
     */
    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress == null ? null : empAddress.trim();
    }

    /**
     * 获取联系电话
     * @return EMP_DIC_BASIC_INFO.EMP_TELL
     */
    public BigDecimal getEmpTell() {
        return empTell;
    }

    /**
     * 设置联系电话
     * @param empTell 联系电话
     */
    public void setEmpTell(BigDecimal empTell) {
        this.empTell = empTell;
    }

    /**
     * 获取银行卡号  用于发放工资的银行卡号
     * @return EMP_DIC_BASIC_INFO.EMP_BANK_CARD
     */
    public String getEmpBankCard() {
        return empBankCard;
    }

    /**
     * 设置银行卡号  用于发放工资的银行卡号
     * @param empBankCard 银行卡号  用于发放工资的银行卡号
     */
    public void setEmpBankCard(String empBankCard) {
        this.empBankCard = empBankCard == null ? null : empBankCard.trim();
    }

    /**
     * 获取身份证号
     * @return EMP_DIC_BASIC_INFO.EMP_IDNUM
     */
    public String getEmpIdnum() {
        return empIdnum;
    }

    /**
     * 设置身份证号
     * @param empIdnum 身份证号
     */
    public void setEmpIdnum(String empIdnum) {
        this.empIdnum = empIdnum == null ? null : empIdnum.trim();
    }

    /**
     * 获取学历
     * @return EMP_DIC_BASIC_INFO.EMP_EDU
     */
    public String getEmpEdu() {
        return empEdu;
    }

    /**
     * 设置学历
     * @param empEdu 学历
     */
    public void setEmpEdu(String empEdu) {
        this.empEdu = empEdu == null ? null : empEdu.trim();
    }

    /**
     * 获取毕业院校
     * @return EMP_DIC_BASIC_INFO.EMP_SCHOOL
     */
    public String getEmpSchool() {
        return empSchool;
    }

    /**
     * 设置毕业院校
     * @param empSchool 毕业院校
     */
    public void setEmpSchool(String empSchool) {
        this.empSchool = empSchool == null ? null : empSchool.trim();
    }

    /**
     * 获取入职时间
     * @return EMP_DIC_BASIC_INFO.CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置入职时间
     * @param createTime 入职时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人员  只能是管理员编号
     * @return EMP_DIC_BASIC_INFO.CREATE_EMPID
     */
    public Integer getCreateEmpid() {
        return createEmpid;
    }

    /**
     * 设置创建人员  只能是管理员编号
     * @param createEmpid 创建人员  只能是管理员编号
     */
    public void setCreateEmpid(Integer createEmpid) {
        this.createEmpid = createEmpid;
    }

    /**
     * 获取修改时间
     * @return EMP_DIC_BASIC_INFO.MODIFY_TIME
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取修改人员  只能是管理员编号
     * @return EMP_DIC_BASIC_INFO.MODIFY_EMPID
     */
    public Integer getModifyEmpid() {
        return modifyEmpid;
    }

    /**
     * 设置修改人员  只能是管理员编号
     * @param modifyEmpid 修改人员  只能是管理员编号
     */
    public void setModifyEmpid(Integer modifyEmpid) {
        this.modifyEmpid = modifyEmpid;
    }

    /**
     * 获取部门ID
     * @return EMP_DIC_BASIC_INFO.DEPART_ID
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
     * @return EMP_DIC_BASIC_INFO.DEPART_RAND
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