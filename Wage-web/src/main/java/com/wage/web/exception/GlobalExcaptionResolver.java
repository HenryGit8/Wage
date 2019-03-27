package com.wage.web.exception;

import com.wage.web.constant.DescribableEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: GlobalExcaptionResolver
 * @Description: 全局异常处理类，分AJAX请求和通用请求
 * @author: shenlx
 * @date: 2015年12月15日 下午5:38:56
 * @version: v1.0
 */
@Component
public class GlobalExcaptionResolver extends AbstractHandlerExceptionResolver {

	private static final Logger logger = Logger.getLogger(GlobalExcaptionResolver.class);

	// 记录数据库最大字符长度
	private static final int WIRTE_DB_MAX_LENGTH = 3500;
	// 记录数据库最大字符长度
	private static final String LOG_LEVEL = "6";
	
	private static final String Log_LEVEL_CUSTOM = "5";
	
	// 记录数据库最大字符长度
	private static final String LOG_OPT = "3";

	private HttpMessageConverter<Object> jsonMessageConverter;

	public void setJsonMessageConverter(HttpMessageConverter<Object> jsonMessageConverter) {
		this.jsonMessageConverter = jsonMessageConverter;
	}

	public HttpMessageConverter<Object> getJsonMessageConverter() {
		return jsonMessageConverter;
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		try {
			return handleException(e, request, response, handler);
		} catch (Exception handlerException) {
			logger.error("Handling of [" + e.getClass().getName() + "] resulted in Exception", handlerException);
		}
		return null;
	}

	private ModelAndView handleException(Exception e, HttpServletRequest request, HttpServletResponse response,
			Object handler) throws IOException {
		//步骤一、异常信息记录到日志文件中.
		if(!e.getClass().equals(BusinessException.class)){
			logger.error("全局处理异常捕获:", e);//如果不是业务异常，不打印日志
		}
		//步骤二、异常信息记录截取前50字符写入数据库中.
		logDb(e);
		//步骤三、分普通请求和ajax请求分别处理.
		if (isAjax(e, request, response, handler)) {
			return handleAjaxeException(e, request, response);
		} else {
			return handleCommonException(e, request, response);
		}
	}
	/**
	 * 获取最原始的异常出处，即最初抛出异常的地方
	 */
	private Throwable deepestException(Throwable e){
		Throwable tmp = e;
		int breakPoint = 0;
		while(tmp.getCause()!=null){
			if(tmp.equals(tmp.getCause())){
				break;
			}
			tmp=tmp.getCause();
			breakPoint++;
			if(breakPoint>1000){
				break;
			}
		}
		return tmp;
	}
	/**
	 * @Title: logDb
	 * @Description: 异常信息记录截取前50字符写入数据库中
	 * @param ex
	 * @throws
	 */
	private void logDb(Exception ex) {
		
		try {
			Throwable deepestException = deepestException(ex);
			String exceptionMessage = getThrowableMessage(deepestException);
			//String exceptionMessage = "错误异常: "+deepestException.getClass().getSimpleName()+",错误描述："+deepestException.getMessage();
			String logLevel = LOG_LEVEL;
			if (ex instanceof DescribableException) {
				logLevel = Log_LEVEL_CUSTOM;
			}
			if(StringUtils.isNotEmpty(exceptionMessage)){
				if(exceptionMessage.length() > WIRTE_DB_MAX_LENGTH){
					exceptionMessage = exceptionMessage.substring(0,WIRTE_DB_MAX_LENGTH);
				}
			}
			//sysLogService.addLog(exceptionMessage, logLevel, LOG_OPT);
		} catch (Exception handlerException) {
			logger.error("Handling of logDb出错 [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
		}
		
	}


	/*
	 * @Title: handleAjaxeException
	 * 
	 * @Description: 处理Ajax请求异常
	 * 
	 * @param e
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 * @throws
	 */
	private ModelAndView handleAjaxeException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpMessageConverter<Object> messageConverter = getJsonMessageConverter();
		HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
		outputMessage.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		outputMessage.getHeaders().add("Cache-Control", "no-cache");

		Map<String, Object> error = new HashMap<String, Object>();
		// 自定义异常
		if (e instanceof DescribableException) {
			DescribableException describableException = (DescribableException) e;
			error.put("statusCode", describableException.getCode());
			error.put("message", describableException.getMsg()
					+ (describableException.getDetail() == null ? "" : describableException.getDetail()));
			if (describableException.getData() != null) {
				error.put("data", describableException.getData());
			}
		}
		// 未知异常
		else {
			logger.error(e.getMessage(), e);
			error.put("statusCode", DescribableEnum.SYSTEM_ERROR.getCode());
			error.put("message", DescribableEnum.SYSTEM_ERROR.getMsg() + "[请与管理员联系!]");
		}
		messageConverter.write(error, MediaType.APPLICATION_JSON, outputMessage);
		return new ModelAndView();
	}

	/**
	 * @Title: handleCommonException @Description: 处理普通异常 @param e @param
	 * request @param response @return @throws IOException @throws
	 */
	private ModelAndView handleCommonException(Exception e, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// TODO
		logger.error(e.getMessage(), e);
//		return new ModelAndView("forward:/admin/error");
		return null;
	}

	/**
	 * @Title: isAjax @Description: 判断是否是Ajax请求 @param e @param request @param
	 * response @param handler @return @throws
	 */
	private boolean isAjax(Exception e, HttpServletRequest request, HttpServletResponse response, Object handler) {
		String contentType = request.getContentType();
		if (!StringUtils.isEmpty(contentType) && contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
			return true;
		}
		ResponseBody responseBody = null;
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
		}
		return responseBody != null;
	}

	/**
	 * 返回错误信息字符串
	 *
	 * @param ex
	 *            Exception
	 * @return 错误信息字符串
	 */
	public String getThrowableMessage(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
	}

	public static boolean isAjaxRequest(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");
		boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;
		return isAjax;
	}
}