/**
 *
 */
package com.wage.web.common;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败控制
 *
 */
public class MyAuthenticationFailureHandler extends
        SimpleUrlAuthenticationFailureHandler {
	private MyAuthenticationEntryPoint loginEntry;

	public MyAuthenticationEntryPoint getLoginEntry() {
		return loginEntry;
	}

	public void setLoginEntry(MyAuthenticationEntryPoint loginEntry) {
		this.loginEntry = loginEntry;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		// 从loginEntry中获取登录失败要跳转的url，并加上错误信息error
		String authenfailureUrl = this.loginEntry
				.determineUrlToUseForThisRequest(request, response, exception);
		authenfailureUrl = authenfailureUrl + "?role=1";
		super.setDefaultFailureUrl(authenfailureUrl);
		super.onAuthenticationFailure(request, response, exception);

	}

}
