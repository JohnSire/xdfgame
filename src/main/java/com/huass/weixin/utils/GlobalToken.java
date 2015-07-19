package com.huass.weixin.utils;

import com.huass.common.utils.SpringContextHolder;
import com.huass.weixin.service.UserService;

/**
 * 获取accessToken类
 * @author iibm
 *
 */
public class GlobalToken
{
	
	private UserService userService = SpringContextHolder.getBean(UserService.class);

	public static GlobalToken getInstance()
	{
		return new GlobalToken();
	}
	
	
	public String getAccessToken()
	{
		return userService.getAccessToken();
	}
	
	public void saveAccessToken(String token)
	{
		System.out.println("token:" + token);
		userService.saveAccessToken(token);
	}
}
