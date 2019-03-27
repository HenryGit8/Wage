/**
 * FullCalendarDto.java 2018/1/10 10:05
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * File：FullCalendarDto.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class FullCalendarDto implements Serializable {
    /**
     * 员工编号  3位部门号+3位流水号 数据库字段是：EMP_ID  <br>
     */
    private Integer id;

    private String name;

    private String title;

    private Date start;

    private Date end;

    private boolean allDay;

    private String color;

    private String url;

    private BigDecimal hours;

    private Short overType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public Short getOverType() {
        return overType;
    }

    public void setOverType(Short overType) {
        this.overType = overType;
    }
}
