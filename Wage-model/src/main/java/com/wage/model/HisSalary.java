package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HisSalary extends HisSalaryKey implements Serializable {
    /**
     * 固定工资  EMP_MON_SAL.MON_SAL 数据库字段是：MON_SAL  <br>
     */
    private BigDecimal monSal;

    /**
     * 额外工资  EMP_MON_OTHER_SAL.OTHER_SAL 数据库字段是：OTHER_SAL  <br>
     */
    private BigDecimal otherSal;

    /**
     * 扣除工资  EMP_MON_DEDUCT_SAL.DEDUCT_SAL 数据库字段是：DEDUCT_SAL  <br>
     */
    private BigDecimal deductSal;

    /**
     * 应发工资 数据库字段是：GROSS_PAY  <br>
     */
    private BigDecimal grossPay;

    /**
     * 实发工资 数据库字段是：ACTUAL_PAY  <br>
     */
    private BigDecimal actualPay;

    /**
     * 银行卡号   用于发放工资的银行卡号 数据库字段是：EMP_BANK_CARD  <br>
     */
    private String empBankCard;

    /**
     * 是否发放(0：未发放;1：已发放) 数据库字段是：IS_GRANT  <br>
     */
    private Short isGrant;

    /**
     * 发放时间 数据库字段是：GRANT_TIME  <br>
     */
    private Date grantTime;

    /**
     * 发放人员 数据库字段是：GRANT_EMPID  <br>
     */
    private Integer grantEmpid;

    /**
     * 养老保险金 数据库字段是：PEN_INSUR_PAY  <br>
     */
    private BigDecimal penInsurPay;

    /**
     * 医疗保险金 数据库字段是：MED_INSUR_PAY  <br>
     */
    private BigDecimal medInsurPay;

    /**
     * 失业保险金 数据库字段是：UNEMP_INSUR_PAY  <br>
     */
    private BigDecimal unempInsurPay;

    /**
     * 基本住房公积金 数据库字段是：BAS_HOUS_PRO_FUND_PAY  <br>
     */
    private BigDecimal basHousProFundPay;

    /**
     * 养老保险金(单位) 数据库字段是：PEN_INSUR_PAY_C  <br>
     */
    private BigDecimal penInsurPayC;

    /**
     * 医疗保险金(单位) 数据库字段是：MED_INSUR_PAY_C  <br>
     */
    private BigDecimal medInsurPayC;

    /**
     * 失业保险金(单位) 数据库字段是：UNEMP_INSUR_PAY_C  <br>
     */
    private BigDecimal unempInsurPayC;

    /**
     * 基本住房公积金(单位) 数据库字段是：BAS_HOUS_PRO_FUND_PAY_C  <br>
     */
    private BigDecimal basHousProFundPayC;

    /**
     * 工伤保险金(单位) 数据库字段是：EMP_INJURY_INSUR_PAY_C  <br>
     */
    private BigDecimal empInjuryInsurPayC;

    /**
     * 生育保险金(单位) 数据库字段是：BIRTH_INSUR_C  <br>
     */
    private BigDecimal birthInsurC;

    /**
     * 个人所得税 数据库字段是：PERSON_INCOME_TAX  <br>
     */
    private BigDecimal personIncomeTax;

    /**
     * 备注 数据库字段是：REMARKS  <br>
     */
    private String remarks;

    /**
     * 个人应缴五险一金总额 数据库字段是：PENSON_PAY  <br>
     */
    private BigDecimal pensonPay;

    /**
     * 企业应缴五险一金总额 数据库字段是：COMPANY_PAY  <br>
     */
    private BigDecimal companyPay;

    /**
     * 获取固定工资  EMP_MON_SAL.MON_SAL
     * @return HIS_SALARY.MON_SAL
     */
    public BigDecimal getMonSal() {
        return monSal;
    }

    /**
     * 设置固定工资  EMP_MON_SAL.MON_SAL
     * @param monSal 固定工资  EMP_MON_SAL.MON_SAL
     */
    public void setMonSal(BigDecimal monSal) {
        this.monSal = monSal;
    }

    /**
     * 获取额外工资  EMP_MON_OTHER_SAL.OTHER_SAL
     * @return HIS_SALARY.OTHER_SAL
     */
    public BigDecimal getOtherSal() {
        return otherSal;
    }

    /**
     * 设置额外工资  EMP_MON_OTHER_SAL.OTHER_SAL
     * @param otherSal 额外工资  EMP_MON_OTHER_SAL.OTHER_SAL
     */
    public void setOtherSal(BigDecimal otherSal) {
        this.otherSal = otherSal;
    }

    /**
     * 获取扣除工资  EMP_MON_DEDUCT_SAL.DEDUCT_SAL
     * @return HIS_SALARY.DEDUCT_SAL
     */
    public BigDecimal getDeductSal() {
        return deductSal;
    }

    /**
     * 设置扣除工资  EMP_MON_DEDUCT_SAL.DEDUCT_SAL
     * @param deductSal 扣除工资  EMP_MON_DEDUCT_SAL.DEDUCT_SAL
     */
    public void setDeductSal(BigDecimal deductSal) {
        this.deductSal = deductSal;
    }

    /**
     * 获取应发工资
     * @return HIS_SALARY.GROSS_PAY
     */
    public BigDecimal getGrossPay() {
        return grossPay;
    }

    /**
     * 设置应发工资
     * @param grossPay 应发工资
     */
    public void setGrossPay(BigDecimal grossPay) {
        this.grossPay = grossPay;
    }

    /**
     * 获取实发工资
     * @return HIS_SALARY.ACTUAL_PAY
     */
    public BigDecimal getActualPay() {
        return actualPay;
    }

    /**
     * 设置实发工资
     * @param actualPay 实发工资
     */
    public void setActualPay(BigDecimal actualPay) {
        this.actualPay = actualPay;
    }

    /**
     * 获取银行卡号   用于发放工资的银行卡号
     * @return HIS_SALARY.EMP_BANK_CARD
     */
    public String getEmpBankCard() {
        return empBankCard;
    }

    /**
     * 设置银行卡号   用于发放工资的银行卡号
     * @param empBankCard 银行卡号   用于发放工资的银行卡号
     */
    public void setEmpBankCard(String empBankCard) {
        this.empBankCard = empBankCard == null ? null : empBankCard.trim();
    }

    /**
     * 获取是否发放(0：未发放;1：已发放)
     * @return HIS_SALARY.IS_GRANT
     */
    public Short getIsGrant() {
        return isGrant;
    }

    /**
     * 设置是否发放(0：未发放;1：已发放)
     * @param isGrant 是否发放(0：未发放;1：已发放)
     */
    public void setIsGrant(Short isGrant) {
        this.isGrant = isGrant;
    }

    /**
     * 获取发放时间
     * @return HIS_SALARY.GRANT_TIME
     */
    public Date getGrantTime() {
        return grantTime;
    }

    /**
     * 设置发放时间
     * @param grantTime 发放时间
     */
    public void setGrantTime(Date grantTime) {
        this.grantTime = grantTime;
    }

    /**
     * 获取发放人员
     * @return HIS_SALARY.GRANT_EMPID
     */
    public Integer getGrantEmpid() {
        return grantEmpid;
    }

    /**
     * 设置发放人员
     * @param grantEmpid 发放人员
     */
    public void setGrantEmpid(Integer grantEmpid) {
        this.grantEmpid = grantEmpid;
    }

    /**
     * 获取养老保险金
     * @return HIS_SALARY.PEN_INSUR_PAY
     */
    public BigDecimal getPenInsurPay() {
        return penInsurPay;
    }

    /**
     * 设置养老保险金
     * @param penInsurPay 养老保险金
     */
    public void setPenInsurPay(BigDecimal penInsurPay) {
        this.penInsurPay = penInsurPay;
    }

    /**
     * 获取医疗保险金
     * @return HIS_SALARY.MED_INSUR_PAY
     */
    public BigDecimal getMedInsurPay() {
        return medInsurPay;
    }

    /**
     * 设置医疗保险金
     * @param medInsurPay 医疗保险金
     */
    public void setMedInsurPay(BigDecimal medInsurPay) {
        this.medInsurPay = medInsurPay;
    }

    /**
     * 获取失业保险金
     * @return HIS_SALARY.UNEMP_INSUR_PAY
     */
    public BigDecimal getUnempInsurPay() {
        return unempInsurPay;
    }

    /**
     * 设置失业保险金
     * @param unempInsurPay 失业保险金
     */
    public void setUnempInsurPay(BigDecimal unempInsurPay) {
        this.unempInsurPay = unempInsurPay;
    }

    /**
     * 获取基本住房公积金
     * @return HIS_SALARY.BAS_HOUS_PRO_FUND_PAY
     */
    public BigDecimal getBasHousProFundPay() {
        return basHousProFundPay;
    }

    /**
     * 设置基本住房公积金
     * @param basHousProFundPay 基本住房公积金
     */
    public void setBasHousProFundPay(BigDecimal basHousProFundPay) {
        this.basHousProFundPay = basHousProFundPay;
    }

    /**
     * 获取养老保险金(单位)
     * @return HIS_SALARY.PEN_INSUR_PAY_C
     */
    public BigDecimal getPenInsurPayC() {
        return penInsurPayC;
    }

    /**
     * 设置养老保险金(单位)
     * @param penInsurPayC 养老保险金(单位)
     */
    public void setPenInsurPayC(BigDecimal penInsurPayC) {
        this.penInsurPayC = penInsurPayC;
    }

    /**
     * 获取医疗保险金(单位)
     * @return HIS_SALARY.MED_INSUR_PAY_C
     */
    public BigDecimal getMedInsurPayC() {
        return medInsurPayC;
    }

    /**
     * 设置医疗保险金(单位)
     * @param medInsurPayC 医疗保险金(单位)
     */
    public void setMedInsurPayC(BigDecimal medInsurPayC) {
        this.medInsurPayC = medInsurPayC;
    }

    /**
     * 获取失业保险金(单位)
     * @return HIS_SALARY.UNEMP_INSUR_PAY_C
     */
    public BigDecimal getUnempInsurPayC() {
        return unempInsurPayC;
    }

    /**
     * 设置失业保险金(单位)
     * @param unempInsurPayC 失业保险金(单位)
     */
    public void setUnempInsurPayC(BigDecimal unempInsurPayC) {
        this.unempInsurPayC = unempInsurPayC;
    }

    /**
     * 获取基本住房公积金(单位)
     * @return HIS_SALARY.BAS_HOUS_PRO_FUND_PAY_C
     */
    public BigDecimal getBasHousProFundPayC() {
        return basHousProFundPayC;
    }

    /**
     * 设置基本住房公积金(单位)
     * @param basHousProFundPayC 基本住房公积金(单位)
     */
    public void setBasHousProFundPayC(BigDecimal basHousProFundPayC) {
        this.basHousProFundPayC = basHousProFundPayC;
    }

    /**
     * 获取工伤保险金(单位)
     * @return HIS_SALARY.EMP_INJURY_INSUR_PAY_C
     */
    public BigDecimal getEmpInjuryInsurPayC() {
        return empInjuryInsurPayC;
    }

    /**
     * 设置工伤保险金(单位)
     * @param empInjuryInsurPayC 工伤保险金(单位)
     */
    public void setEmpInjuryInsurPayC(BigDecimal empInjuryInsurPayC) {
        this.empInjuryInsurPayC = empInjuryInsurPayC;
    }

    /**
     * 获取生育保险金(单位)
     * @return HIS_SALARY.BIRTH_INSUR_C
     */
    public BigDecimal getBirthInsurC() {
        return birthInsurC;
    }

    /**
     * 设置生育保险金(单位)
     * @param birthInsurC 生育保险金(单位)
     */
    public void setBirthInsurC(BigDecimal birthInsurC) {
        this.birthInsurC = birthInsurC;
    }

    /**
     * 获取个人所得税
     * @return HIS_SALARY.PERSON_INCOME_TAX
     */
    public BigDecimal getPersonIncomeTax() {
        return personIncomeTax;
    }

    /**
     * 设置个人所得税
     * @param personIncomeTax 个人所得税
     */
    public void setPersonIncomeTax(BigDecimal personIncomeTax) {
        this.personIncomeTax = personIncomeTax;
    }

    /**
     * 获取备注
     * @return HIS_SALARY.REMARKS
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

    /**
     * 获取个人应缴五险一金总额
     * @return HIS_SALARY.PENSON_PAY
     */
    public BigDecimal getPensonPay() {
        return pensonPay;
    }

    /**
     * 设置个人应缴五险一金总额
     * @param pensonPay 个人应缴五险一金总额
     */
    public void setPensonPay(BigDecimal pensonPay) {
        this.pensonPay = pensonPay;
    }

    /**
     * 获取企业应缴五险一金总额
     * @return HIS_SALARY.COMPANY_PAY
     */
    public BigDecimal getCompanyPay() {
        return companyPay;
    }

    /**
     * 设置企业应缴五险一金总额
     * @param companyPay 企业应缴五险一金总额
     */
    public void setCompanyPay(BigDecimal companyPay) {
        this.companyPay = companyPay;
    }
}