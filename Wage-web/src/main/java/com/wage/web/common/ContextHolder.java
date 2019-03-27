package com.wage.web.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wage.model.EmpDicBasicInfo;
import com.wage.model.EmpDicPassword;
import com.wage.web.constant.ParamConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
* @ClassName: ContextHolderUtils 
* @Description: 上下文工具类，用于在Server,Dao层获取当前登录用户信息等；
* @author 何友池
 */
public class ContextHolder {
	/**
	 * SpringMvc下获取request
	 * 
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;

	}
	/**
	 * SpringMvc下获取session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		HttpSession session = getRequest().getSession();
		return session;

	}

	/**
	 * 获取当前登录用户名、密码、是否管理员
	 * @return
     */
	public static final EmpDicPassword getSessionUser() {
		HttpSession session = ContextHolder.getSession();
		EmpDicPassword u = (EmpDicPassword) session.getAttribute(ParamConstants.LOCAL_CLINET_USER);
		return u;
	}
	
	/**
	 * 获取当前登录的用户信息
	 * @return
     */
	public static final EmpDicBasicInfo getSessionEmp() {
		HttpSession session = ContextHolder.getSession();
		EmpDicBasicInfo emp = (EmpDicBasicInfo) session.getAttribute(ParamConstants.LOCAL_CLINET_EMP);
		return emp;
	}

}
