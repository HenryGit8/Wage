/**
 * HisRestNExt.java 2018/1/11 13:54
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.HisRest;

import java.io.Serializable;

/**
 * File：HisRestNExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class HisRestNExt extends HisRest implements Serializable {

    private String empName;

    private Integer isAllow;

    private Integer isNotMonth;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(Integer isAllow) {
        this.isAllow = isAllow;
    }

    public Integer getIsNotMonth() {
        return isNotMonth;
    }

    public void setIsNotMonth(Integer isNotMonth) {
        this.isNotMonth = isNotMonth;
    }
}
