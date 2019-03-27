/**
 * MainHandler.java 2017/12/19 8:16
 * Copyright ©2017 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wage.api.LoginService;
import com.wage.model.EmpDicBasicInfo;
import com.wage.util.JacksonUtil;
import com.wage.web.common.ContextHolder;
import com.wage.web.constant.ParamConstants;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * File：MainHandler.java<br>
 * Title: <br>
 * Description: <br>
 * Company: wondersgroup.com <br>
 * @author 何友池
 * @version 1.0
 */
@Component
public class MainHandler {

    @Reference
    LoginService loginService;

    /**
     * 登录获取用户姓名
     * @return
     */
    public String getLoginInfo(){
        return ContextHolder.getSessionEmp().getEmpName();
    }

    /**
     * 登录获取用户姓名
     * @return
     */
    public Integer getLoginId(){
        return ContextHolder.getSessionEmp().getEmpId();
    }
    /**
     * 登录获取公告数
     * @return
     */
    public Integer getNotice(){
        return loginService.getNoRead(ContextHolder.getSessionEmp().getEmpId());
    }

    /**
     * 获取菜单
     * @return
     * @author jerry.pan
     * @date 2017-04-12 10:04:33
     */
    public List menu(){
        Short userType = ContextHolder.getSessionUser().getIsAdmin();
        String menuJson = "";
        if(ParamConstants.USER_TYPE_0.equals(userType)){//员工
            menuJson = "[{\"name\":\"员工菜单\",\"url\":\"\",\"class\":\"glyphicon-education\",\"child\":[{\"name\":\"作业列表\",\"url\":\"/study/homework/page/list?publishStatus=1\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
        }else if(ParamConstants.USER_TYPE_1.equals(userType)){//管理员
            menuJson = "[{\"name\":\"系统管理员菜单\",\"url\":\"\",\"class\":\"glyphicon-education\",\"child\":[{\"name\":\"假期设置\",\"url\":\"/study/homework/page/findByTeacher\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"},{\"name\":\"税率设置\",\"url\":\"/study/homework/page/findByTeacher\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"},{\"name\":\"部门薪资设置\",\"url\":\"/study/homework/page/findByTeacher\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"},{\"name\":\"计算公式设置\",\"url\":\"/study/homework/page/findByTeacher\",\"class\":\"glyphicon-book\",\"icon\":\"icon-more\"}]}]";
        }
        List menuList = JacksonUtil.json2Bean(menuJson,ArrayList.class);
        return menuList;
    }
}
