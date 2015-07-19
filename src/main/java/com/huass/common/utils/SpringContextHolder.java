
package com.huass.common.utils;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;


@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, ServletContextAware, DisposableBean {

	private static ApplicationContext applicationContext = null;
	
	private static ServletContext sc;

	private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

	
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}
	
	public static String getRootRealPath(){
		String rootRealPath ="";
		try {
			rootRealPath=getApplicationContext().getResource("").getFile().getAbsolutePath();
		} catch (IOException e) {
			logger.warn("获取系统根目录失败");
		}
		return rootRealPath;
	}
	
	public static String getResourceRootRealPath(){
		String rootRealPath ="";
		try {
			rootRealPath=new DefaultResourceLoader().getResource("").getFile().getAbsolutePath();
		} catch (IOException e) {
			logger.warn("获取资源根目录失败");
		}
		return rootRealPath;
	}
	

	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	
	public static void clearHolder() {
		if (logger.isDebugEnabled()){
			logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
		}
		applicationContext = null;
		sc = null;
	}

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {

		if (SpringContextHolder.applicationContext != null) {
			logger.info("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
		}

		SpringContextHolder.applicationContext = applicationContext;
	}

	
	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}

	
	private static void assertContextInjected() {
		Validate.validState(applicationContext != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
		Validate.validState(sc != null, "sc属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
	}

	@Override
	public void setServletContext(ServletContext sc)
	{
		if (SpringContextHolder.sc != null) {
			logger.info("SpringContextHolder中的ServletContext被覆盖, 原有ServletContext为:" + SpringContextHolder.sc);
		}
		SpringContextHolder.sc = sc;
	}
	
	
	public static String getValue(String value)
	{
		Object obj = sc.getAttribute(value);
		if(obj != null)
		{
			return obj.toString();
		}
		return null;
	}
	
	
	public static void putValue(String key, String value)
	{
		sc.setAttribute(key, value);
	}
}
