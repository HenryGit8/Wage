package com.wage.web.exception;


import com.wage.web.constant.Describable;

/**
 * 
 * @ClassName: BusinessException
 * @Description: 业务异常
 * @author 何友池
 */
@SuppressWarnings("all")
public class BusinessException extends DescribableException
{
	public BusinessException(Describable describableInfo) {
		super(describableInfo);
	}
	
	public BusinessException(Describable describableInfo, String errorDetails) {
		super(describableInfo, errorDetails);
	}
	
	public BusinessException(Describable describableInfo, Object data) {
		super(describableInfo, data);
	}
	
	public BusinessException(Integer code, String  msg) {
		super(code, msg);
	}
}
