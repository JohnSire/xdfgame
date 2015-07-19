
package com.huass.common.config;

import java.util.Map;

import com.google.common.collect.Maps;
import com.huass.common.utils.PropertiesLoader;
import com.huass.weixin.utils.GlobalToken;
import com.huass.weixin.utils.WXConst;
import com.huass.wxsdk.httpclient.wxUtils;
  

public class Global {
	
	
	private static Map<String, String> map = Maps.newHashMap();
	
	
	private static PropertiesLoader propertiesLoader = new PropertiesLoader(new String[]{"jdbc.properties", "weixin.properties"});
	
	
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			if(WXConst.GLOBAL_ACCESS_TOKEN.equals(key))
			{
				try
				{
					value = GlobalToken.getInstance().getAccessToken();
					if(value == null){
						value = wxUtils.getInstance().getAccessToken(Global.getConfig("appid"), Global.getConfig("appsecret"));
						GlobalToken.getInstance().saveAccessToken(value);
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				value = propertiesLoader.getProperty(key);
				map.put(key, value);
			}
		}
		return value;
	}
	
	
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	public static String getAccessToken()
	{
		return getConfig(WXConst.GLOBAL_ACCESS_TOKEN);
	}
	
	
	public static void putConfig(String key, String value)
	{
		map.put(key, value);
	}
	
}
