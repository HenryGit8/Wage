package com.wage.web.constant;

/**
 * <p>名称：Describable.java</p>
 * <p>描述：可以被描述的抽象接口</p>
 * <pre>
 * @author 何友池
 */
public interface Describable
{ 
	/**
	 * 描述：  
	 * <pre>描述编码</pre>
	 * @return
	 */
	Integer getCode();
	
	/**
	 * 描述：
	 * <pre>描述信息</pre>
	 * @return
	 */
	String getMsg();
}
