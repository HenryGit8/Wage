/**
 * HisRestExt.java 2018/1/11 13:34
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.HisRest;

import java.io.Serializable;
import java.util.Date;

/**
 * File：HisRestExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class HisRestExt extends HisRest implements Serializable {

    /**
     * 请假开始时间 数据库字段是：REST_START_TIME  <br>
     */
    private Date restStartTimeOld;

    public Date getRestStartTimeOld() {
        return restStartTimeOld;
    }

    public void setRestStartTimeOld(Date restStartTimeOld) {
        this.restStartTimeOld = restStartTimeOld;
    }
}
