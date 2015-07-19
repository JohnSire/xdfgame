package com.huass.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.huass.common.config.Global;
import com.huass.weixin.entity.User;
import com.huass.weixin.service.BagService;
import com.huass.weixin.service.UserService;
import com.huass.weixin.utils.WXConst;
import com.huass.wxsdk.httpclient.wxUtils;
import com.huass.wxsdk.msg.Msg4Articles;
import com.huass.wxsdk.msg.Msg4ImageTextClient;

public class wxApiUtils
{
	private UserService userService;
	
	private BagService bagService;
	
	public static String getOpenId(String code) throws Exception
	{
		String atUrl = getOpenIdUrl(code);
		return wxUtils.getOpenId(atUrl);
	}
	
	
	public static String getOpenIdUrl(String code) throws Exception
	{
		String atUrlNoCode =  Global.getConfig(WXConst.GET_ACCESS_TOKEN_URL);
		if(StringUtils.isEmpty(atUrlNoCode))
		{
			String atUrlNoInit =  Global.getConfig(WXConst.GET_ACCESS_TOKEN_URL_NOINIT);
			String appid = Global.getConfig("appid");
			String secret = Global.getConfig("appsecret");
			atUrlNoCode = atUrlNoInit.replace("APPID", appid).replace("SECRET", secret);
			Global.putConfig(WXConst.GET_ACCESS_TOKEN_URL, atUrlNoCode);
		}
		String atUrl = atUrlNoCode.replace("CODE", code);
		return atUrl;
	}
	public static String getTokenAndOpenId(String code) throws Exception
	{
		String json = wxUtils.getTokenAndOpenId(getOpenIdUrl(code));
		return json;
	}
	
	public User getUserInfoFromWeb(String accessToken, String openId) throws Exception
	{
		String userInfo = wxUtils.getUserInfoByWeb(accessToken, openId);
		User user = new User();
		this.transform2User(user, userInfo);
		System.out.println("from weixinWeb:" + userInfo);
		return user;
	}
	
	public  String getUserInfoFromWX1(String openId) throws Exception
	{
		String userInfo = wxUtils.getInstance().getUserInfo(this.getAccessToken(), openId);
		System.out.println("from weixin1:" + userInfo);
		return userInfo;
	}
	
	
	public  User getUserInfoFromWX(String openId) throws Exception
	{
		if(StringUtils.isBlank(openId))
		{
			return null;
		}
		User user = new User();
		String userInfo = wxUtils.getInstance().getUserInfo(this.getAccessToken(), openId);
		System.out.println("from weixin:" + userInfo);
		this.transform2User(user, userInfo);
		return user;
	}
	
	private void transform2User(User user, String userInfo)
	{
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
					if(nickname == null)
					{
						nickname = "";
					}
					else
					{
						nickname = nickname.replace("", "");
						nickname = nickname.replace("?", "");
						nickname = EmojiFilter.filterEmoji(nickname);
					}
					String headImg = userJson.getString(WXConst.USER_HEADIMAGE);
					String openId = userJson.getString("openid");
					user.setOpenId(openId);
					user.setNameV(StringUtils.abbr(nickname, 11));
					user.setHeadImage(headImg);
			 }
		}
	}
	
	public String getAccessToken() throws Exception
	{
		String accessToken = Global.getConfig(WXConst.GLOBAL_ACCESS_TOKEN);
		return accessToken;
	}
	
	
	public void sendClientMsg(String accessToken, String msg) throws Exception
	{
		 wxUtils.getInstance().sendClientMsg(accessToken, msg);
	}
	
	
	public void sendQiangMsg(String openId, String moenyN, Map<String, String> urlParaMap, String dbagId, String bagId, String guanming) throws Exception
	{
		List<Msg4Articles> list = new ArrayList<Msg4Articles>();
		Msg4ImageTextClient itc = new Msg4ImageTextClient(list);
		itc.setTouser(openId);
		Msg4Articles a1 = new Msg4Articles();
		a1.setTitle(SpringContextHolder.getValue(WXConst.MSG_CLIENT_QIANG_TITLE));
		String desc = SpringContextHolder.getValue(WXConst.MSG_CLIENT_QIANG_DESC).replace("(红包冠名)", guanming).replace("(XX)", moenyN);
		a1.setDescription(desc);
		a1.setPicurl(SpringContextHolder.getValue(WXConst.MSG_CLIENT_QIANG_IMG));
		
		String url = SpringContextHolder.getValue(WXConst.MSG_CLIENT_QIANG_URL).replace("(openId)", openId).replace("(bagId)", dbagId).replace("(dbagId)", dbagId);
		for(String key : urlParaMap.keySet())
		{
			url = url.replace(key, urlParaMap.get(key));
		}
		a1.setUrl(url); 
		list.add(a1);
		wxApiUtils wxUtil = new wxApiUtils();
		wxUtil.sendClientMsg(Global.getAccessToken(), itc.tojson());
	}
	
	
	public void sendMyBagMsg(String openId, String userName, String naming, String moneyN, Map<String, String> urlParaMap) throws Exception
	{
		List<Msg4Articles> list = new ArrayList<Msg4Articles>();
		Msg4ImageTextClient itc = new Msg4ImageTextClient(list);
		itc.setTouser(openId);
		Msg4Articles a1 = new Msg4Articles();
		a1.setTitle(SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_TITLE));
		String desc = SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_DESC).replace("(NAME)", userName).replace("(NAMING)", naming).replace("(MONEYN)", moneyN);
		a1.setDescription(desc);
		a1.setPicurl(SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_IMG));
		String url = SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_URL);
		for(String key : urlParaMap.keySet())
		{
			url = url.replace(key, urlParaMap.get(key));
		}
		a1.setUrl(url);
		list.add(a1);
		wxApiUtils wxUtil = new wxApiUtils();
		wxUtil.sendClientMsg(Global.getAccessToken(), itc.tojson());
	}
	
	
	public void sendMyBagFirstMsg(String openId, String friendName, String naming, String moneyN, Map<String, String> urlParaMap) throws Exception
	{
		List<Msg4Articles> list = new ArrayList<Msg4Articles>();
		Msg4ImageTextClient itc = new Msg4ImageTextClient(list);
		itc.setTouser(openId);
		Msg4Articles a1 = new Msg4Articles();
		a1.setTitle(SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_FIRST_TITLE));
		String desc = SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_FIRST_DESC).replace("(冠名)", naming).replace("(好友)", friendName);
		a1.setDescription(desc);
		a1.setPicurl(SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_FIRST_IMG));
		String url = SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_FIRST_URL).replace("(openId)", openId).replace("(bagId)", urlParaMap.get("(bagId)")).replace("(dbagId)", urlParaMap.get("(dbagId)"));
		for(String key : urlParaMap.keySet())
		{
			url = url.replace(key, urlParaMap.get(key));
		}
		a1.setUrl(url);
		list.add(a1);
		wxApiUtils wxUtil = new wxApiUtils();
		wxUtil.sendClientMsg(Global.getAccessToken(), itc.tojson());
	}
	
	
	public void sendMyBagSecondMsg(String openId, String friendName, String naming, String moneyN, Map<String, String> urlParaMap) throws Exception
	{
		this.sendMyBagFirstMsg(openId, friendName, naming, moneyN, urlParaMap);
	}
	
	
	public void sendMyBagThirdMsg(String openId, String friendName, String naming, String moneyN, Map<String, String> urlParaMap) throws Exception
	{
		List<Msg4Articles> list = new ArrayList<Msg4Articles>();
		Msg4ImageTextClient itc = new Msg4ImageTextClient(list);
		itc.setTouser(openId);
		Msg4Articles a1 = new Msg4Articles();
		a1.setTitle(SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_THIRD_TITLE));
		String desc = SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_THIRD_DESC).replace("(NAME)", friendName).replace("(NAMING)", naming);
		a1.setDescription(desc);
		a1.setPicurl(SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_THIRD_IMG));
		String url = SpringContextHolder.getValue(WXConst.MSG_CLIENT_MYBAG_THIRD_URL);
		for(String key : urlParaMap.keySet())
		{
			url = url.replace(key, urlParaMap.get(key));
		}
		a1.setUrl(url);
		list.add(a1);
		wxApiUtils wxUtil = new wxApiUtils();
		wxUtil.sendClientMsg(Global.getAccessToken(), itc.tojson());
	}
	
	
	public void sendFriendBagMsg(String openId, String userName, String naming, String moneyN, Map<String, String> urlParaMap) throws Exception
	{
		List<Msg4Articles> list = new ArrayList<Msg4Articles>();
		Msg4ImageTextClient itc = new Msg4ImageTextClient(list);
		itc.setTouser(openId);
		Msg4Articles a1 = new Msg4Articles();
		a1.setTitle(SpringContextHolder.getValue(WXConst.MSG_CLIENT_FRIENDBAG_TITLE));
		String desc = SpringContextHolder.getValue(WXConst.MSG_CLIENT_FRIENDBAG_DESC).replace("(好友)", userName).replace("(冠名)", naming).replace("(money)", moneyN);
		a1.setDescription(desc);
		a1.setPicurl(SpringContextHolder.getValue(WXConst.MSG_CLIENT_FRIENDBAG_IMG));
		String url = SpringContextHolder.getValue(WXConst.MSG_CLIENT_FRIENDBAG_URL).replace("(openId)", openId).replace("(bagId)", urlParaMap.get("(bagId)")).replace("(dbagId)", urlParaMap.get("(dbagId)"));
		for(String key : urlParaMap.keySet())
		{
			url = url.replace(key, urlParaMap.get(key));
		}
		a1.setUrl(url);
		list.add(a1);
		wxApiUtils wxUtil = new wxApiUtils();
		wxUtil.sendClientMsg(Global.getAccessToken(), itc.tojson());
	}
	
	
	public void sendTeacherBagMsg(String openId, String userName, String naming, String moneyN, Map<String, String> urlParaMap) throws Exception
	{
		List<Msg4Articles> list = new ArrayList<Msg4Articles>();
		Msg4ImageTextClient itc = new Msg4ImageTextClient(list);
		itc.setTouser(openId);
		Msg4Articles a1 = new Msg4Articles();
		a1.setTitle(SpringContextHolder.getValue(WXConst.MSG_CLIENT_TEACHERBAG_TITLE));
		String desc = SpringContextHolder.getValue(WXConst.MSG_CLIENT_TEACHERBAG_DESC).replace("(NAME)", userName).replace("(NAMING)", naming).replace("(MONEYN)", moneyN);
		a1.setDescription(desc);
		a1.setPicurl(SpringContextHolder.getValue(WXConst.MSG_CLIENT_TEACHERBAG_IMG));
		String url = SpringContextHolder.getValue(WXConst.MSG_CLIENT_TEACHERBAG_URL);
		for(String key : urlParaMap.keySet())
		{
			url = url.replace(key, urlParaMap.get(key));
		}
		a1.setUrl(url);
		list.add(a1);
		wxApiUtils wxUtil = new wxApiUtils();
		wxUtil.sendClientMsg(Global.getAccessToken(), itc.tojson());
	}
	
	
	public void TeacherBagMsg(String openId, String moenyN, Map<String, String> urlParaMap) throws Exception
	{
		List<Msg4Articles> list = new ArrayList<Msg4Articles>();
		Msg4ImageTextClient itc = new Msg4ImageTextClient(list);
		itc.setTouser(openId);
		Msg4Articles a1 = new Msg4Articles();
		a1.setTitle(SpringContextHolder.getValue(WXConst.MSG_TEACHER_ZHUANYONG_TITLE));
		String desc = SpringContextHolder.getValue(WXConst.MSG_TEACHER_ZHUANYONG_DESC).replace("(MONEYN)", moenyN);
		a1.setDescription(desc);
		a1.setPicurl(SpringContextHolder.getValue(WXConst.TEACHER_TUISONG));
		
		String url = SpringContextHolder.getValue(WXConst.MSG_CLIENT_QIANG_URL);
		for(String key : urlParaMap.keySet())
		{
			url = url.replace(key, urlParaMap.get(key));
		}
		a1.setUrl(url); 
		list.add(a1);
		wxApiUtils wxUtil = new wxApiUtils();
		wxUtil.sendClientMsg(Global.getAccessToken(), itc.tojson());
	}
	
	
	public void ToTeacherBagMsg(String openId, String moenyN, Map<String, String> urlParaMap) throws Exception
	{
		List<Msg4Articles> list = new ArrayList<Msg4Articles>();
		Msg4ImageTextClient itc = new Msg4ImageTextClient(list);
		itc.setTouser(openId);
		Msg4Articles a1 = new Msg4Articles();
		a1.setTitle(SpringContextHolder.getValue(WXConst.MSG_TEACHER_ZHUANYONG_TITLE));
		String desc = SpringContextHolder.getValue(WXConst.MSG_TEACHER_ZHUANYONG_TO_DESC).replace("(MONEYN)", moenyN);
		a1.setDescription(desc);
		a1.setPicurl(SpringContextHolder.getValue(WXConst.TEACHER_TUISONG));
		
		String url = SpringContextHolder.getValue(WXConst.GUANZHU_INDEX_URL);
		for(String key : urlParaMap.keySet())
		{
			url = url.replace(key, urlParaMap.get(key));
		}
		a1.setUrl(url); 
		list.add(a1);
		wxApiUtils wxUtil = new wxApiUtils();
		wxUtil.sendClientMsg(Global.getAccessToken(), itc.tojson());
	}

	
	public static String position(String getLatitude, String getLongitude, String openId){
		String aa = "http://api.map.baidu.com/geocoder?location=纬度,经度&output=json";
		String bb = aa.replaceAll("纬度", getLatitude).replaceAll("经度", getLongitude);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(bb);
		try {
			HttpResponse response = httpclient.execute(httpost);
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			JSONObject obj = JSONObject.parseObject(result);
			String result1 = obj.getString("result");
			JSONObject obj1 = JSONObject.parseObject(result1);
			String addressComponent = obj1.getString("addressComponent");
			JSONObject obj2 = JSONObject.parseObject(addressComponent);
			String city = obj2.getString("city");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return "city";
	}
}
