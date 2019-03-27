/**
 * EmpDicBasicInfoExt.java 2017/12/25 16:45
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.EmpDicBasicInfo;

import java.io.Serializable;

/**
 * File：EmpDicBasicInfoExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class EmpDicBasicInfoExt extends EmpDicBasicInfo implements Serializable {
    /**
     * 创建人员  只能是管理员编号 数据库字段是：CREATE_EMPID  <br>
     */
    private String createEmpName;

    private String departName;

    public String getCreateEmpName() {
        return createEmpName;
    }

    public void setCreateEmpName(String createEmpName) {
        this.createEmpName = createEmpName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}
