package com.huass.weixin.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class WXSessionManager implements Runnable
{
	
	public static final int EXPIRED_TIME = 3600 * 24 * 1000;
	
	
	public static final int FREQUENCY = 3600 * 1000;
	
	private static Map<String, WXSession> sessionMap;

	private static WXSessionManager sm;
	
	public static WXSessionManager getInstance()
	{
		if(sm == null)
		{
			sm = new WXSessionManager();
		}
		return sm;
	}
	
	public WXSessionManager()
	{
		sessionMap = new HashMap<String, WXSession>();
	}
	
	@Override
	public void run()
	{
		if(!sessionMap.isEmpty())
		{
			Set<String> sessionKeys = sessionMap.keySet();
			for(String key : sessionKeys)
			{
				WXSession session = sessionMap.get(key);
				long lastActiveTime = session.getLastActiveTime();
				long expiredTime = session.getExpiredTime();
				long subTime = System.currentTimeMillis() - lastActiveTime;
				if(subTime >= expiredTime)
				{
					sessionMap.remove(key);
				}
			}
		}
		try
		{
			Thread.currentThread().sleep(FREQUENCY);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	

	public WXSession getSession(String key)
	{
		WXSession session = sessionMap.get(key);
		if(session == null)
		{
			session = new WXSession(key);
			sessionMap.put(key, session);
		}
		session.setLastActiveTime(System.currentTimeMillis());
		return session;
	}
}
