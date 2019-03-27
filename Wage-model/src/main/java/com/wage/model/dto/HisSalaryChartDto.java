/**
 * HisSalaryChartDto.java 2018/2/25 9:00
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * File：HisSalaryChartDto.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class HisSalaryChartDto implements Serializable {

    private Integer yearMonth;

    private BigDecimal monSalSum;

    private BigDecimal otherSalSum;

    private BigDecimal deductSalSum;

    private BigDecimal grossPaySum;

    private BigDecimal actualPaySum;

    public Integer getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Integer yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getMonSalSum() {
        return monSalSum;
    }

    public void setMonSalSum(BigDecimal monSalSum) {
        this.monSalSum = monSalSum;
    }

    public BigDecimal getOtherSalSum() {
        return otherSalSum;
    }

    public void setOtherSalSum(BigDecimal otherSalSum) {
        this.otherSalSum = otherSalSum;
    }

    public BigDecimal getDeductSalSum() {
        return deductSalSum;
    }

    public void setDeductSalSum(BigDecimal deductSalSum) {
        this.deductSalSum = deductSalSum;
    }

    public BigDecimal getGrossPaySum() {
        return grossPaySum;
    }

    public void setGrossPaySum(BigDecimal grossPaySum) {
        this.grossPaySum = grossPaySum;
    }

    public BigDecimal getActualPaySum() {
        return actualPaySum;
    }

    public void setActualPaySum(BigDecimal actualPaySum) {
        this.actualPaySum = actualPaySum;
    }
}
