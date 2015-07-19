package com.huass.common.utils;

import com.huass.common.utils.excel.annotation.ExcelField;

public class ExcelUser
{
	@ExcelField(title="昵称", align=2, sort=20)
	private String name;
	
	@ExcelField(title="头像", align=2, sort=25)
	private String headImg;
	
	@ExcelField(title="OPENID", align=2, sort=30)
	private String openId;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHeadImg()
	{
		return headImg;
	}

	public void setHeadImg(String headImg)
	{
		this.headImg = headImg;
	}

	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
}
