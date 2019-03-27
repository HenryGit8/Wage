/**
 * EmpMonSalExt.java 2018/1/2 14:23
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.EmpMonSal;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * File：EmpMonSalExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class EmpMonSalExt extends EmpMonSal implements Serializable {

    private String empName;

    /**
     * 总额 数据库字段是：DEDUCT_SAL  <br>
     */
    private BigDecimal deductSal;

    /**
     * 总额 数据库字段是：OTHER_SAL  <br>
     */
    private BigDecimal otherSal;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public BigDecimal getDeductSal() {
        return deductSal;
    }

    public void setDeductSal(BigDecimal deductSal) {
        this.deductSal = deductSal;
    }

    public BigDecimal getOtherSal() {
        return otherSal;
    }

    public void setOtherSal(BigDecimal otherSal) {
        this.otherSal = otherSal;
    }
}
