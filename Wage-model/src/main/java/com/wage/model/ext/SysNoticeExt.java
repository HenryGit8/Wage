/**
 * SysNoticeExt.java 2018/3/6 8:44
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.SysNotice;

/**
 * File：SysNoticeExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class SysNoticeExt extends SysNotice {

    private String empName;

    private Integer isThisEmpS;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getIsThisEmpS() {
        return isThisEmpS;
    }

    public void setIsThisEmpS(Integer isThisEmpS) {
        this.isThisEmpS = isThisEmpS;
    }
}
