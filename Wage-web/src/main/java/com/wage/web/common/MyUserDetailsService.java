/**
 * MyUserDetailsService.java 2018/6/4 9:42
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wage.api.EmpInfoService;
import com.wage.api.HisSalaryService;
import com.wage.api.LoginService;
import com.wage.model.EmpDicBasicInfo;
import com.wage.model.EmpDicPassword;
import com.wage.web.constant.ParamConstants;
import com.wage.web.handler.LoginHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 从数据库中获取信息的自定义类
 */
public class MyUserDetailsService implements UserDetailsService {

    @Reference
    LoginService loginService;

    /**
     * 获取用户信息，设置角色
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // 获取用户信息
        EmpDicPassword user = loginService.getLoginPass(Integer.parseInt(username));
        if (user != null) {
            String role = user.getIsAdmin() == 1 ? ParamConstants.ADMINP: ParamConstants.USERP;
            // 设置角色
            return new User(user.getEmpId().toString(), user.getPassword(),AuthorityUtils.createAuthorityList(role));
        }

        throw new UsernameNotFoundException("User '" + username
                + "' not found.");
    }

}
