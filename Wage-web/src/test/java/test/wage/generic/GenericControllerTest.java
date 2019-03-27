package test.wage.generic; /**
 * <p>项目名：Mzyy</p>
 * <p>包名：generic</p>
 * <p>文件名：generic.GenericControllerTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-8-上午9:53:50</p>
 * Copyright (c) 2015aux公司-版权所有
 */

import java.util.Map;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;


/**
 * <p>名称：generic.GenericControllerTest.java</p>
 * <p>描述：控制层测试基类</p>
 * <pre>
 *    1、@WebAppConfiguration：测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根
 *    2、@ContextHierarchy：指定容器层次，如果子类继承该类时，且需要扩展配置文件，可以配置子容器。如：
 *    	 @ContextHierarchy(@ContextConfiguration(name = "child", locations = "classpath:spring-jpa.xml, classpath:spring-cache.xml"))
 *    3、通过@Autowired WebApplicationContext wac：注入web环境的ApplicationContext容器
 *    4、然后通过MockMvcBuilders.webAppContextSetup(wac).build()创建一个MockMvc进行测试
 * 	  ------------------------------------------------------------------------------------------------------------------------------------- 
 * 
 *    整个测试的规律：
 *       1、准备测试环境
 *       2、通过MockMvc执行请求
 *       3.1、添加验证断言
 *       3.2、添加结果处理器
 *       3.3、得到MvcResult进行自定义断言/进行下一步的异步请求
 *       4、卸载测试环境
 *       
 *    1、mockMvc.perform执行一个请求；                                                                                                         
 *    2、MockMvcRequestBuilders.get("/user/1")构造一个请求                                                                                       
 *    3、ResultActions.andExpect添加执行完成后的断言                                                                                                 
 *    4、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。                                          
 *    5、ResultActions.andReturn表示执行完成后返回相应的结果。
 * </pre>
 * @author shenlx
 * @date 2015-5-8 上午9:54:58
 * @version 1.0.0
 */
public abstract class GenericControllerTest extends GenericTest
{
	protected MockMvc mockMvc;
	protected FileTypeMap defaultFileTypeMap = MimetypesFileTypeMap.getDefaultFileTypeMap();

	@Autowired
	private WebApplicationContext wac;
	
	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * <p>描述：执行模拟的HttpServletRequest请求</p>
	 * <pre>
	 *    执行模拟的HttpServletRequest请求，并返回ResultActions
	 *    可以通过ResultActions处理请求结果
	 * </pre>
	 * @param requestBuilder
	 * @return
	 * @throws Exception
	 */
	protected ResultActions perform(MockHttpServletRequestBuilder requestBuilder) throws Exception
	{
		return mockMvc.perform(requestBuilder);
	}

	/**
	 * <p>描述：获得MockMultipartHttpServletRequestBuilder</p>
	 * <pre>
	 *    获得带文件上传的MockMultipartHttpServletRequestBuilder
	 * </pre>
	 * @param url
	 * @param params
	 *            ：Map<paramName, paramValue>
	 * @param urlVariables		
	 * 		url：/user/{id}		
	 * 		1L     
	 * @return
	 * @throws Exception
	 */
	protected MockMultipartHttpServletRequestBuilder getMultipartRequestBuilder(String url, Map<String, Object> params, Object... urlVariables) throws Exception
	{
		MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.fileUpload(url, urlVariables);
		setParams(requestBuilder, params);
		return requestBuilder;
	}
	
	/**
	 * <p>描述：获得MockHttpServletRequestBuilder</p>
	 * <pre>
	 *    
	 * </pre>
	 * @param url
	 * @param params
	 *            ：Map<paramName, paramValue>
	 * @param urlVariables		
	 * 		url：/user/{id}		
	 * 		1L     
	 * @return
	 */
	protected  MockHttpServletRequestBuilder getRequestBuilder(String url, Map<String, Object> params, Object objJson,HttpMethod requestType, Object... urlVariables)
	{
		MockHttpServletRequestBuilder requestBuilder = null;
		if (HttpMethod.GET.equals(requestType))
		{
			requestBuilder = MockMvcRequestBuilders.get(url, urlVariables);
		} 
		else if (HttpMethod.POST.equals(requestType))
		{
			requestBuilder = MockMvcRequestBuilders.post(url, urlVariables);
		}
		else if (HttpMethod.OPTIONS.equals(requestType))
		{
			requestBuilder = MockMvcRequestBuilders.options(url, urlVariables);
		}
		else if (HttpMethod.PUT.equals(requestType))
		{
			requestBuilder = MockMvcRequestBuilders.put(url, urlVariables);
		}
		else if (HttpMethod.DELETE.equals(requestType))
		{
			requestBuilder = MockMvcRequestBuilders.delete(url, urlVariables);
		}
		else 
		{
			requestBuilder = MockMvcRequestBuilders.post(url, urlVariables);
		}
		requestBuilder.content(objJson==null?"":JSON.toJSONString(objJson));
		//params.put("token", "adminAux");
		setParams(requestBuilder, params);
		requestBuilder.characterEncoding("UTF-8");
		requestBuilder.contentType(MediaType.APPLICATION_JSON);
		requestBuilder.contentType(MediaType.APPLICATION_XML);
		requestBuilder.contentType(MediaType.TEXT_HTML);
		return requestBuilder;
	}
	
	/**
	 * <p>描述：模拟请求</p>
	 * <pre>
	 *    模拟请求，并返回ResultActions
	 *    可以通过ResultActions处理请求结果
	 * </pre>
	 * @param url
	 * @param params
	 * @param requestType
	 * @param urlVariables
	 * @return
	 * @throws Exception
	 */
	protected ResultActions getResultActions(String url, Map<String, Object> params, Object objJson,HttpMethod requestType, Object... urlVariables) throws Exception
	{
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = getRequestBuilder(url, params,objJson, requestType, urlVariables);
		return mockMvc.perform(mockHttpServletRequestBuilder);
	}
	
	
	// ===========================================================================================================================

	private void setParams(MockHttpServletRequestBuilder requestBuilder, Map<String, Object> params)
	{
		if (null != params)
		{
			for (Map.Entry<String, Object> entry : params.entrySet())
			{
				Object obj = entry.getValue();
				requestBuilder.param(entry.getKey(), ObjectUtils.toString(obj, ""));
			}
		}
	}
	
	
}
