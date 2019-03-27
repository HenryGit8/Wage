package test.wage; /**
 * <p>项目名：MNurse</p>
 * <p>包名：generic</p>
 * <p>文件名：generic.GenericTest.java</p>
 * <p>版本信息：</p>
 * <p>日期：2015-5-8-上午9:55:26</p>
 * Copyright (c) 2015aux公司-版权所有
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>名称：generic.GenericTest.java</p>
 * <p>描述：service、dao层测试基类</p>
 * <pre>
 *    
 * </pre>
 * @author shenlx
 * @date 2015-5-8 上午9:55:26
 * @version 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration(value = "src/main/webapp")
//@ContextHierarchy(@ContextConfiguration(name = "parent", locations = {"classpath:spring-mvc.xml", "classpath:applicationContext.xml"}))
@ContextHierarchy(@ContextConfiguration(name = "parent", locations = {"classpath*:/META-INF/spring/spring-*.xml"}))
public abstract class ServiceGenericTest extends AbstractJUnit4SpringContextTests
{
	
}

