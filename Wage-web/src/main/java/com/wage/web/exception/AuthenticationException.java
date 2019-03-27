package com.wage.web.exception;


import com.wage.web.constant.Describable;

/**
 * <p>名称：AuthenticationException.java</p>
 * <p>描述：认证异常</p>
 * <pre>
 *    验证用户是否已登录失败时抛出
 * </pre>
 * @author shenlx
 * @date 2015-12-22 下午9:22:30
 * @version 1.0.0
 */
@SuppressWarnings("all")
public class AuthenticationException extends DescribableException
{
	public AuthenticationException(Describable describableInfo) {
		super(describableInfo);
	}

	public AuthenticationException(Describable describableInfo, String detail) {
		super(describableInfo, detail);
	}
}