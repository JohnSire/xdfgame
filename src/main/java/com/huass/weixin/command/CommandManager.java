package com.huass.weixin.command;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.huass.common.utils.PropertiesLoader;
import com.huass.wxsdk.msg.Msg;


public class CommandManager
{
	private static final String COMMAND_PATH = "/command.properties";
	private Map<String, String> map;
	private static CommandManager instance;
	
	public static CommandManager getInstance()
	{
		if(instance == null)
		{
			instance = new CommandManager();
		}
		return instance;
	}
	
	private CommandManager()
	{
		init();
	}
	
	
	public void init()
	{
		PropertiesLoader loader = new PropertiesLoader(COMMAND_PATH);
		Properties pro = loader.getProperties();
		map = new HashMap<String, String>((Map) pro);
	}
	
	public synchronized IMenuCommand getCommand(String key, Msg msg)
	{
		String className = map.get(key);
		IMenuCommand command = null;
		try
		{
			Class cls = Class.forName(className);
			Constructor con = cls.getConstructor(Msg.class);
			command = (IMenuCommand)con.newInstance(msg);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return command;
	}
	
}
