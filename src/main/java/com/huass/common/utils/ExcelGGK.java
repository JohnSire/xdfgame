package com.huass.common.utils;

import com.huass.common.utils.excel.annotation.ExcelField;


public class ExcelGGK
{
	@ExcelField(title="手机", align=2, sort=20)
	private String mobile;
	
	@ExcelField(title="数量", align=2, sort=21)
	private String prizeNum;

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getPrizeNum()
	{
		return prizeNum;
	}

	public void setPrizeNum(String prizeNum)
	{
		this.prizeNum = prizeNum;
	}
}
