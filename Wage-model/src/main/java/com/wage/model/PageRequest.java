package com.wage.model;

import java.io.Serializable;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2017-03-31 下午2:25
 * Company: wondersgroup.com
 *
 * @author jerry.pan(潘健雷)
 * @version 1.0
 */
public class PageRequest implements Serializable {

    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer page;//Easyui 第几页

    private Integer rows;//Easyui 页面大小

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
