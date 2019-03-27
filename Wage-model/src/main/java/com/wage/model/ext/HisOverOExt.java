/**
 * HisOverOExt.java 2018/1/18 9:27
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.HisOver;

import java.io.Serializable;
import java.util.Date;

/**
 * File：HisOverOExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class HisOverOExt extends HisOver implements Serializable {

    /**
     * 加班开始时间 数据库字段是：OVER_TIME_START  <br>
     */
    private Date overTimeStartOld;

    public Date getOverTimeStartOld() {
        return overTimeStartOld;
    }

    public void setOverTimeStartOld(Date overTimeStartOld) {
        this.overTimeStartOld = overTimeStartOld;
    }
}
