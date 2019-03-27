package com.wage.web.common;

import com.github.pagehelper.PageInfo;
import com.wage.model.PageRequest;

/**
 * Title: 分页工具类
 * Description:
 * Copyright: Copyright (c) 2017-03-31 下午2:39
 * @author 何友池
 */
public class PageUtil {

    public static final int DEFAULT_PAGE_NUM = 1; //默认第几页

    public static final int DEFAULT_PAGE_SIZE = 20; //默认每页显示数

    private PageUtil() {
        super();
    }

    /**
     * 获取Page对象
     *
     * @param pageRequest
     * @return page
     * @author jerry.pan
     */
    public static PageInfo getPage(PageRequest pageRequest) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNum(pageRequest.getPage() == null ? PageUtil.DEFAULT_PAGE_NUM : pageRequest.getPage());
        pageInfo.setPageSize(pageRequest.getRows() == null ? PageUtil.DEFAULT_PAGE_SIZE : pageRequest.getRows());
        return pageInfo;
    }

}
