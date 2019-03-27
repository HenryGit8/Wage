package com.wage.web.common;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wage.api.LoginService;
import com.wage.model.EmpDicBasicInfo;
import com.wage.model.EmpDicPassword;
import com.wage.web.constant.ParamConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * 登录授权成功后操作控制，如果是直接点击登录的情况下，根据授权权限跳转不同页面； 否则跳转到原请求页面
 */
public class MyAuthenticationSuccessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {
	private Map<String, String> authDispatcherMap;
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Reference
	LoginService loginService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// 获取用户权限
		Collection<? extends GrantedAuthority> authCollection = authentication
				.getAuthorities();
		if (authCollection.isEmpty()) {
			return;
		}

		// 认证成功后，获取用户信息并添加到session中
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Integer empId = Integer.parseInt(userDetails.getUsername());
		EmpDicBasicInfo empDicBasicInfo = loginService.getLoginInfo(empId);
		request.getSession().setAttribute(ParamConstants.LOCAL_CLINET_EMP, empDicBasicInfo);
		EmpDicPassword empDicPassword = loginService.getLoginPass(empId);
		request.getSession().setAttribute(ParamConstants.LOCAL_CLINET_USER, empDicPassword);

		String url = null;
		// 从别的请求页面跳转过来的情况，savedRequest不为空
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if (savedRequest != null) {
			url = savedRequest.getRedirectUrl();
		}

		// 直接点击登录页面，根据登录用户的权限跳转到不同的页面
		if (url == null) {
			for (GrantedAuthority auth : authCollection) {
				url = authDispatcherMap.get(auth.getAuthority());
				if(auth.getAuthority().equals(ParamConstants.ADMINP)){
					request.getSession().setAttribute(ParamConstants.EMP_AUTHORITY, loginService.getAuthByEmpId(empId));
					url = url+"?role="+ParamConstants.ADMINP;
				}
				if(auth.getAuthority().equals(ParamConstants.USERP)){
					request.getSession().setAttribute(ParamConstants.EMP_NOTICE, loginService.getNotice(empId));
					url = url+"?role="+ParamConstants.USERP;
				}
			}
			getRedirectStrategy().sendRedirect(request, response, url);
		}

		super.onAuthenticationSuccess(request, response, authentication);

	}


	public RequestCache getRequestCache() {
		return requestCache;
	}

	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}


	public Map<String, String> getAuthDispatcherMap() {
		return authDispatcherMap;
	}

	public void setAuthDispatcherMap(Map<String, String> authDispatcherMap) {
		this.authDispatcherMap = authDispatcherMap;
	}

}
