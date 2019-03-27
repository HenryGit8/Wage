/**
 * SecureResourceFilterInvocationDefinitionSource.java 2018/6/4 14:01
 * Copyright ©2018 wondersgroup.com All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.wage.web.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import com.wage.web.constant.ParamConstants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;


public class SecureResourceFilterInvocationDefinitionSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

    private PathMatcher matcher;

    private static Map<String, Collection<ConfigAttribute>> map = new HashMap<String, Collection<ConfigAttribute>>();

    /*
     * 初始化用户权限，为了简便操作没有从数据库获取
     * 实际操作可以从数据库中获取所有资源路径url所对应的权限
     */
    public void afterPropertiesSet() throws Exception {
        this.matcher = new AntPathMatcher();//用来匹配访问资源路径
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        ConfigAttribute ca = new SecurityConfig(ParamConstants.ADMINP);
        atts.add(ca);
        map.put("/gotoMain", atts);
        map.put("/jump/empInfoManager", atts);
        map.put("/jump/empMonDeductSalManager", atts);
        map.put("/jump/allowanceHistory", atts);
        map.put("/jump/empMonOtherSalManager", atts);
        map.put("/jump/empMonSalManager", atts);
        map.put("/jump/overWorkHistory", atts);
        map.put("/jump/restWorkHistory", atts);
        map.put("/jump/salaryChanceHistory", atts);
        map.put("/jump/salaryPayHistory", atts);
        map.put("/jump/departmentManager", atts);
        map.put("/jump/systemSet", atts);
        map.put("/jump/welcome", atts);
        Collection<ConfigAttribute> attsno =new ArrayList<ConfigAttribute>();
        ConfigAttribute cano = new SecurityConfig(ParamConstants.USERP);
        attsno.add(cano);
        map.put("/gotoEmpMain", attsno);
        map.put("/jump/empWelcome", attsno);
        map.put("/jump/empWageQuery", attsno);
        map.put("/jump/restApply", attsno);
        map.put("/jump/restQuery", attsno);
        map.put("/jump/overQuery", attsno);
        map.put("/jump/overApply", attsno);
        map.put("/jump/welfareApply", attsno);
        map.put("/jump/welfareQuery", attsno);
        Collection<ConfigAttribute> all = new ArrayList<ConfigAttribute>();
        all.add(ca);
        all.add(cano);
        map.put("/menu", all);
        map.put("/getVerify", all);
        map.put("/checkVerify", all);
        map.put("/", all);
    }



    @Override
    public Collection<ConfigAttribute> getAttributes(Object object)
            throws IllegalArgumentException {
        // TODO Auto-generated method stub
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestURI = filterInvocation.getRequestUrl();
        //循环资源路径，当访问的Url和资源路径url匹配时，返回该Url所需要的权限
        for(Iterator<Map.Entry<String, Collection<ConfigAttribute>>> iter = map.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, Collection<ConfigAttribute>> entry = iter.next();
            String url = entry.getKey();
            if(matcher.match(url, requestURI)||(requestURI.split("/").length>2)&&requestURI.indexOf("/jump")==-1) {
                return map.get(requestURI);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
     */
    @SuppressWarnings("rawtypes")
    public Collection getConfigAttributeDefinitions() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.intercept.ObjectDefinitionSource#supports(java.lang.Class)
     */
    public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
        return true;
    }

    /**
     *
     * @param filterInvocation
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, String> getUrlAuthorities(org.springframework.security.web.FilterInvocation filterInvocation) {
        ServletContext servletContext = filterInvocation.getHttpRequest().getSession().getServletContext();
        return (Map<String, String>)servletContext.getAttribute("urlAuthorities");
    }

}
