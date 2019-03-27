package com.wage.model;

import java.io.Serializable;

public class EmpImg implements Serializable {
    /**
     * 员工编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 员工头像 数据库字段是：EMP_HEAD_IMG  <br>
     */
    private String empHeadImg;

    /**
     * 员工其他附件 数据库字段是：EMP_OTHER_FILE  <br>
     */
    private String empOtherFile;

    /**
     * 获取员工编号
     * @return EMP_IMG.EMP_ID
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
     * 获取员工头像
     * @return EMP_IMG.EMP_HEAD_IMG
     */
    public String getEmpHeadImg() {
        return empHeadImg;
    }

    /**
     * 设置员工头像
     * @param empHeadImg 员工头像
     */
    public void setEmpHeadImg(String empHeadImg) {
        this.empHeadImg = empHeadImg == null ? null : empHeadImg.trim();
    }

    /**
     * 获取员工其他附件
     * @return EMP_IMG.EMP_OTHER_FILE
     */
    public String getEmpOtherFile() {
        return empOtherFile;
    }

    /**
     * 设置员工其他附件
     * @param empOtherFile 员工其他附件
     */
    public void setEmpOtherFile(String empOtherFile) {
        this.empOtherFile = empOtherFile == null ? null : empOtherFile.trim();
    }
}