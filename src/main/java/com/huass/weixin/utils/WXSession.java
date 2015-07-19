package com.huass.weixin.utils;

import java.util.HashMap;
import java.util.Map;


public class WXSession
{
	private String id;
	
	private Map<String,Object> values;

	private int expiredTime;
	
	private long lastActiveTime;
	
	public WXSession(String id)
	{
		this.id = id;
		this.expiredTime = WXSessionManager.EXPIRED_TIME;
		values = new HashMap<String, Object>();
	}
	
	public void setAttribute(String key, Object value)
	{
		values.put(key, value);
	}
	
	public Object getAttribute(String key)
	{
		return values.get(key);
	}
	
	public void removeAttribute(String key)
	{
		values.remove(key);
	}

	public int getExpiredTime()
	{
		return expiredTime;
	}

	public void setExpiredTime(int expiredTime)
	{
		this.expiredTime = expiredTime;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public long getLastActiveTime()
	{
		return lastActiveTime;
	}

	public void setLastActiveTime(long lastActiveTime)
	{
		this.lastActiveTime = lastActiveTime;
	}
}
