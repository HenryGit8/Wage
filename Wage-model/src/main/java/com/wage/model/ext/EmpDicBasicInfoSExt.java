/**
 * EmpDicBasicInfoSExt.java 2018/1/9 10:53
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.EmpDicBasicInfo;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * File：EmpDicBasicInfoSExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class EmpDicBasicInfoSExt extends EmpDicBasicInfo implements Serializable {

    private Date startTime;

    private Date endTime;

    private String createEmpname;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateEmpname() {
        return createEmpname;
    }

    public void setCreateEmpname(String createEmpname) {
        this.createEmpname = createEmpname;
    }
}
