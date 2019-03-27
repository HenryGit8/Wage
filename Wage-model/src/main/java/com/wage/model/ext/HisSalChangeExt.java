/**
 * HisSalChangeExt.java 2017/12/29 15:24
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.HisSalChange;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * File：HisSalChangeExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class HisSalChangeExt extends HisSalChange implements Serializable {

    /**
     * 员工姓名 数据库字段是：EMP_NAME  <br>
     */
    private String empName;
    /**
     * 总额 数据库字段是：OTHER_SAL  <br>
     */
    private BigDecimal otherSal;
    /**
     * 总额 数据库字段是：DEDUCT_SAL  <br>
     */
    private BigDecimal deductSal;

    /**
     * 最终工资
     */
    private BigDecimal total;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public BigDecimal getOtherSal() {
        return otherSal;
    }

    public void setOtherSal(BigDecimal otherSal) {
        this.otherSal = otherSal;
    }

    public BigDecimal getDeductSal() {
        return deductSal;
    }

    public void setDeductSal(BigDecimal deductSal) {
        this.deductSal = deductSal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
