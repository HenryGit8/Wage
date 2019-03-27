package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HisOver extends HisOverKey implements Serializable {
    /**
     * 加班总时间（小时） 数据库字段是：OVER_HOUR  <br>
     */
    private BigDecimal overHour;

    /**
     * 是否已证实  0：未核对，1：已证实，2：未证实 数据库字段是：IS_CHECK  <br>
     */
    private Short isCheck;

    /**
     * 加班原因 数据库字段是：OVER_REASON  <br>
     */
    private String overReason;

    /**
     * 加班类型  1：工作日加班费：固定工资×加班小时数×1.5倍；2：双休日加班费：固定工资×加班小时数×2倍；:3：法定节假日加班费：固定工资×加班小时数×3倍。 数据库字段是：OVER_TYPE  <br>
     */
    private Short overType;

    /**
     * 加班结束时间 数据库字段是：OVER_TIME_END  <br>
     */
    private Date overTimeEnd;

    /**
     * 获取加班总时间（小时）
     * @return HIS_OVER.OVER_HOUR
     */
    public BigDecimal getOverHour() {
        return overHour;
    }

    /**
     * 设置加班总时间（小时）
     * @param overHour 加班总时间（小时）
     */
    public void setOverHour(BigDecimal overHour) {
        this.overHour = overHour;
    }

    /**
     * 获取是否已证实  0：未核对，1：已证实，2：未证实
     * @return HIS_OVER.IS_CHECK
     */
    public Short getIsCheck() {
        return isCheck;
    }

    /**
     * 设置是否已证实  0：未核对，1：已证实，2：未证实
     * @param isCheck 是否已证实  0：未核对，1：已证实，2：未证实
     */
    public void setIsCheck(Short isCheck) {
        this.isCheck = isCheck;
    }

    /**
     * 获取加班原因
     * @return HIS_OVER.OVER_REASON
     */
    public String getOverReason() {
        return overReason;
    }

    /**
     * 设置加班原因
     * @param overReason 加班原因
     */
    public void setOverReason(String overReason) {
        this.overReason = overReason == null ? null : overReason.trim();
    }

    /**
     * 获取加班类型  1：工作日加班费：固定工资×加班小时数×1.5倍；2：双休日加班费：固定工资×加班小时数×2倍；:3：法定节假日加班费：固定工资×加班小时数×3倍。
     * @return HIS_OVER.OVER_TYPE
     */
    public Short getOverType() {
        return overType;
    }

    /**
     * 设置加班类型  1：工作日加班费：固定工资×加班小时数×1.5倍；2：双休日加班费：固定工资×加班小时数×2倍；:3：法定节假日加班费：固定工资×加班小时数×3倍。
     * @param overType 加班类型  1：工作日加班费：固定工资×加班小时数×1.5倍；2：双休日加班费：固定工资×加班小时数×2倍；:3：法定节假日加班费：固定工资×加班小时数×3倍。
     */
    public void setOverType(Short overType) {
        this.overType = overType;
    }

    /**
     * 获取加班结束时间
     * @return HIS_OVER.OVER_TIME_END
     */
    public Date getOverTimeEnd() {
        return overTimeEnd;
    }

    /**
     * 设置加班结束时间
     * @param overTimeEnd 加班结束时间
     */
    public void setOverTimeEnd(Date overTimeEnd) {
        this.overTimeEnd = overTimeEnd;
    }

    @Override
    public String toString() {
        return "HisOver{" +
                "overHour=" + overHour +
                ", isCheck=" + isCheck +
                ", overReason='" + overReason + '\'' +
                ", overType=" + overType +
                ", overTimeEnd=" + overTimeEnd +
                '}';
    }
}