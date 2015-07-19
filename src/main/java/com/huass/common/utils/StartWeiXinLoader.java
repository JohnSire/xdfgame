package com.huass.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class StartWeiXinLoader implements ServletContextListener
{

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		try
		{
			ServletContext sc = sce.getServletContext();
			PropertiesLoader loader = new PropertiesLoader(new String[]{"weixin.properties"});
			Properties pro = loader.getProperties();
			if(pro != null && !pro.isEmpty())
			{
				for(Object keyObj : pro.keySet())
				{
					String key = keyObj.toString();
					String value = pro.getProperty(key);
					if(StringUtils.isNotBlank(value))
					{
						String value1 = value.replace("\\n", "\n");
						List<String> vars = this.findVar(value1);
						value1 = this.addvarValue(value1, vars, pro);
						System.out.println("key:" + key + "   value:" + value1);
						sc.setAttribute(key, value1);
					}
				}
			}
			System.out.println("---------------装载完成---------------------");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("-----------装载失败-----------");
		}
	}
	
	
	public String addvarValue(String url, List<String> vars, Properties pro)
	{
		for(String var : vars)
		{
			String varValue = (String) pro.get(var);
			if(StringUtils.isNotBlank(varValue))
			{
				url = url.replace("{" + var + "}", varValue);
			}
		}
		return url;
	}
	
	
	public List<String> findVar(String txt)
    {
	    String re="(\\{)((?:[a-z][a-z0-9_]*))(\\})";
	    Pattern p = Pattern.compile(re,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(txt);
	    List<String> keys = new ArrayList<String>();
	    while (m.find())
	    {
	        String var1=m.group(2);
	        if(StringUtils.isNotBlank(var1))
	        {
	        	keys.add(var1);
	        }
	    }
	    return keys;
    }
	
	public static void main(String[] args)
	{
		String sss = "asdfadsfadsf\nsdfadfasdf";
		System.out.println(sss);
	}
	

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		
	}

}
