package com.wage.model;

import java.io.Serializable;
import java.util.Date;

public class SysNotice implements Serializable {
    /**
     * 编号 数据库字段是：ID  <br>
     */
    private Integer id;

    /**
     * 发布人员编号 数据库字段是：EMP_ID  <br>
     */
    private Integer empId;

    /**
     * 发布时间 数据库字段是：RELEASE_TIME  <br>
     */
    private Date releaseTime;

    /**
     * 内容 数据库字段是：CONTENT  <br>
     */
    private String content;

    /**
     * 获取编号
     * @return SYS_NOTICE.ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     * @param id 编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取发布人员编号
     * @return SYS_NOTICE.EMP_ID
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * 设置发布人员编号
     * @param empId 发布人员编号
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * 获取发布时间
     * @return SYS_NOTICE.RELEASE_TIME
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置发布时间
     * @param releaseTime 发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 获取内容
     * @return SYS_NOTICE.CONTENT
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}