package com.huass.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;


public class PathUtil {
	
	public  static String getWebInfPath(){
		URL url = PathUtil.class.getProtectionDomain().getCodeSource().getLocation();
		String path = url.toString();
		int index = path.indexOf("WEB-INF");
		
		if(index == -1){
			index = path.indexOf("classes");
		}
		
		if(index == -1){
			index = path.indexOf("bin");
		}
		
		path = path.substring(0, index);
		
		if(path.startsWith("zip")){
			path = path.substring(4);
		}else if(path.startsWith("file")){
			path = path.substring(6);
		}else if(path.startsWith("jar")){
			path = path.substring(10); 
		}
		try {
			path =  URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String osType = System.getProperties().getProperty("os.name");
		System.out.println("操作系统:" + osType.toLowerCase());
		if(osType != null && (!osType.toLowerCase().startsWith("win")))
		{
			path = "/" + path;
		}
		return path;
	}
}
