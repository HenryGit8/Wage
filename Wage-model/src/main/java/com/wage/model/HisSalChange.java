package com.wage.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class HisSalChange extends HisSalChangeKey implements Serializable {
    /**
     * 工资类型   1：基本工资，2：职务工资 数据库字段是：TYPE_OF_WAGE  <br>
     */
    private BigDecimal typeOfWage;

    /**
     * 获取工资类型   1：基本工资，2：职务工资
     * @return HIS_SAL_CHANGE.TYPE_OF_WAGE
     */
    public BigDecimal getTypeOfWage() {
        return typeOfWage;
    }

    /**
     * 设置工资类型   1：基本工资，2：职务工资
     * @param typeOfWage 工资类型   1：基本工资，2：职务工资
     */
    public void setTypeOfWage(BigDecimal typeOfWage) {
        this.typeOfWage = typeOfWage;
    }
}