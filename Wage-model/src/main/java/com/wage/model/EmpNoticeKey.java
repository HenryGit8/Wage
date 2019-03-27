package com.wage.model;

import java.io.Serializable;

public class EmpNoticeKey implements Serializable {
    /**
     * 员工编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 公告ID 数据库字段是：NOTICE_ID  <br>
     */
    private Integer noticeId;

    /**
     * 是否已阅（0未阅，1已阅） 数据库字段是：IS_READ  <br>
     */
    private Short isRead;

    /**
     * 获取员工编号
     * @return EMP_NOTICE.EMP_ID
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
     * 获取公告ID
     * @return EMP_NOTICE.NOTICE_ID
     */
    public Integer getNoticeId() {
        return noticeId;
    }

    /**
     * 设置公告ID
     * @param noticeId 公告ID
     */
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    /**
     * 获取是否已阅（0未阅，1已阅）
     * @return EMP_NOTICE.IS_READ
     */
    public Short getIsRead() {
        return isRead;
    }

    /**
     * 设置是否已阅（0未阅，1已阅）
     * @param isRead 是否已阅（0未阅，1已阅）
     */
    public void setIsRead(Short isRead) {
        this.isRead = isRead;
    }
}