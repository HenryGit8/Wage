/**
 * EmpDepartmentExt.java 2018/1/29 13:25
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.model.ext;

import com.wage.model.EmpDepartment;

import java.io.Serializable;

/**
 * File：EmpDepartmentExt.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
public class EmpDepartmentExt extends EmpDepartment implements Serializable {

    private String _parentId;

    public String get_parentId() {
        return _parentId;
    }

    public void set_parentId(String _parentId) {
        this._parentId = _parentId;
    }
}
