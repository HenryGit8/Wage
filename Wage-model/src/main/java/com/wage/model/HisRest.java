package com.wage.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class HisRest extends HisRestKey implements Serializable {
    /**
     * 请假总时间（小时） 数据库字段是：REST_HOUR  <br>
     */
    private Short restHour;

    /**
     * 请假原因 数据库字段是：REST_REASON  <br>
     */
    private String restReason;

    /**
     * 请假结束时间 数据库字段是：REST_END_TIME  <br>
     */
    private Date restEndTime;

    /**
     * 是否请假全天 数据库字段是：ALL_DAY  <br>
     */
    private Short allDay;

    /**
     * 获取请假总时间（小时）
     * @return HIS_REST.REST_HOUR
     */
    public Short getRestHour() {
        return restHour;
    }

    /**
     * 设置请假总时间（小时）
     * @param restHour 请假总时间（小时）
     */
    public void setRestHour(Short restHour) {
        this.restHour = restHour;
    }

    /**
     * 获取请假原因
     * @return HIS_REST.REST_REASON
     */
    public String getRestReason() {
        return restReason;
    }

    /**
     * 设置请假原因
     * @param restReason 请假原因
     */
    public void setRestReason(String restReason) {
        this.restReason = restReason == null ? null : restReason.trim();
    }

    /**
     * 获取请假结束时间
     * @return HIS_REST.REST_END_TIME
     */
    public Date getRestEndTime() {
        return restEndTime;
    }

    /**
     * 设置请假结束时间
     * @param restEndTime 请假结束时间
     */
    public void setRestEndTime(Date restEndTime) {
        this.restEndTime = restEndTime;
    }

    /**
     * 获取是否请假全天
     * @return HIS_REST.ALL_DAY
     */
    public Short getAllDay() {
        return allDay;
    }

    /**
     * 设置是否请假全天
     * @param allDay 是否请假全天
     */
    public void setAllDay(Short allDay) {
        this.allDay = allDay;
    }
}