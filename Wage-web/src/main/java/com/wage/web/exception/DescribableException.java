package com.wage.web.exception;

import com.wage.web.constant.Describable;
import org.springframework.stereotype.Component;


/**
 * <p>名称：DescribableException.java</p>
 * <p>描述：可描述异常</p>
 * <pre>
 *         
 * </pre>
 * @author 何友池
 */
@SuppressWarnings("all")
@Component
public abstract class DescribableException extends RuntimeException {

	private Integer code;// 描述编码
	
	private String msg;// 描述信息

	private String detail;// 详细描述
	
	private Object data;// 详细描述
	
	/**
	 * 构造器
	 * 
	 * <pre></pre>
	 * 
	 * @param describableInfo
	 */
	public DescribableException(Describable describableInfo) {
		super(describableInfo.getMsg());
		this.code = describableInfo.getCode();
		this.msg = describableInfo.getMsg();
	}
	
	/**
	 * 构造器
	 * 
	 * <pre></pre>
	 * 
	 * @param describableInfo
	 */
	public DescribableException(Integer code,String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	/**
	 * <p>构造器：</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param describableInfo
	 * @param detail
	 */
	public DescribableException(Describable describableInfo, String detail) {
		super(describableInfo.getMsg());
		this.code = describableInfo.getCode();
		this.msg = describableInfo.getMsg();
		this.detail = detail;
	}
	
	public DescribableException(Describable describableInfo, Object data) {
		super(describableInfo.getMsg());
		this.code = describableInfo.getCode();
		this.msg = describableInfo.getMsg();
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setRet(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
