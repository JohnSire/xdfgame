package com.huass.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huass.common.utils.excel.ExportExcel;
import com.huass.common.utils.excel.ImportExcel;
import com.huass.weixin.utils.WXConst;
import com.huass.wxsdk.httpclient.wxUtils;

public class WXTest
{
	public static void main(String[] args)
	{
		try
		{
			String accessToken = "F6nAwAsFk8LPjmm2oUhkbakQ2G1KQtyTCAKI6ErmWFrqk7t5V-LtzUDZ4w5_13WMq9lB_Da9BwDTRy2pauu-YQ";
			System.out.println("accessToken:" + accessToken);
			String jsonStr = wxUtils.getInstance().getSubscriber(accessToken,null);
			JSONObject obj = JSON.parseObject(jsonStr);
			JSONObject objArr = (JSONObject) obj.getJSONObject("data");
			JSONArray openIdArr = objArr.getJSONArray("openid");
			List<ExcelUser> eusers = new ArrayList<ExcelUser>();
			for(int i=0; i< openIdArr.size(); i++)
			{
				String openId = (String) openIdArr.get(i);
				String userInfo = wxUtils.getInstance().getUserInfo(accessToken, openId);
				JSONObject userJson = JSONObject.parseObject(userInfo);
				Object errorcode = userJson.get("errcode");
				if(errorcode == null)
				{
					 Object isSubscribe = userJson.get("subscribe");
					 if(isSubscribe != null && "0".equals(isSubscribe.toString()))
					 {
						 
					 }
					 else
					 {
						 String nickname = userJson.getString(WXConst.USER_NICKNAME);
						 String headImg = userJson.getString(WXConst.USER_HEADIMAGE);
						 ExcelUser euser = new ExcelUser();
						 euser.setOpenId(openId);
						 euser.setName(StringUtils.abbr(nickname, 11));
						 euser.setHeadImg(headImg); 
						 eusers.add(euser);
					 }
				}
			}
			ExportExcel ee = new ExportExcel("微信数据", ExcelUser.class);
			ee.setDataList(eusers);
			ee.writeFile("target/export.xlsx");
			
			try
			{
				ImportExcel excel = new ImportExcel("D:/11.xlsx",1,0);
				List<ExcelGGK> list = excel.getDataList(ExcelGGK.class);
				System.out.println(list.size());
			} catch (InvalidFormatException e)
			{
				e.printStackTrace();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void importExcel()
	{
		try
		{
			ImportExcel excel = new ImportExcel("D:\11.xlsx",1,0);
			List<ExcelGGK> list = excel.getDataList(ExcelGGK.class);
			System.out.println(list.size());
		} catch (InvalidFormatException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
