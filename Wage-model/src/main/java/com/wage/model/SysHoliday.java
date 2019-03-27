package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SysHoliday implements Serializable {
    /**
     * 日期 数据库字段是：DATE_TIME  <br>
     */
    private Date dateTime;

    /**
     * 类型（0：不是节假日，1：是节假日） 数据库字段是：TYPE  <br>
     */
    private BigDecimal type;

    /**
     * 获取日期
     * @return SYS_HOLIDAY.DATE_TIME
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * 设置日期
     * @param dateTime 日期
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * 获取类型（0：不是节假日，1：是节假日）
     * @return SYS_HOLIDAY.TYPE
     */
    public BigDecimal getType() {
        return type;
    }

    /**
     * 设置类型（0：不是节假日，1：是节假日）
     * @param type 类型（0：不是节假日，1：是节假日）
     */
    public void setType(BigDecimal type) {
        this.type = type;
    }
}