package com.huass.api.web;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.huass.api.model.GroupModel;
import com.huass.api.model.UserModel;
import com.huass.common.config.Global;
import com.huass.common.web.BaseController;
import com.huass.weixin.utils.WXConst;
import com.huass.wxsdk.httpclient.HttpUtil;
import com.huass.wxsdk.httpclient.wxUtils;
import com.huass.wxsdk.msg.Msg4GroupText;

@Controller
@RequestMapping(value = "${adminPath}/api/msg")
public class MessageController extends BaseController{
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="index")
	public String index(Model model,RedirectAttributes redirectAttributes){
		try {
			String groups = wxUtils.getInstance().getUserGroups(Global.getConfig(WXConst.GLOBAL_ACCESS_TOKEN));
			JSONObject jsonObject = JSON.parseObject(groups);
			JSONArray gs = jsonObject.getJSONArray("groups");
			List<GroupModel> list = Lists.newArrayList();
			for(int i=0;i<gs.size();i++){
				list.add(JSON.parseObject(gs.get(i).toString(),GroupModel.class));
			}
			model.addAttribute("groups", list);
			String openIds = wxUtils.getInstance().getSubscriber(Global.getConfig(WXConst.GLOBAL_ACCESS_TOKEN), "");
			JSONObject obj = JSON.parseObject(openIds);
			JSONObject objArr = (JSONObject) obj.getJSONObject("data");
			JSONArray openIdArr = objArr.getJSONArray("openid");
			List<UserModel> users = new ArrayList<UserModel>();
			for(int i=0; i< openIdArr.size(); i++){
				String openId = (String) openIdArr.get(i);
				String userInfo = wxUtils.getInstance().getUserInfo(Global.getConfig(WXConst.GLOBAL_ACCESS_TOKEN), openId);
				JSONObject userJson = JSONObject.parseObject(userInfo);
				Object errorcode = userJson.get("errcode");
				if(errorcode == null){
					Object isSubscribe = userJson.get("subscribe");
					 if(isSubscribe != null && !"0".equals(isSubscribe.toString())){
						 UserModel user = new UserModel();
						 user.setOpenId(openId);
						 user.setName(userJson.getString(WXConst.USER_NICKNAME));
						 user.setHeadImg(userJson.getString(WXConst.USER_HEADIMAGE)); 
						 user.setCity(userJson.getString(WXConst.USER_CITY)); 
						 user.setProvince(userJson.getString(WXConst.USER_PROVINCE)); 
						 user.setCountry(userJson.getString(WXConst.USER_COUNTRY)); 
						 user.setSex(userJson.getString(WXConst.USER_SEX)); 
						 user.setSubscribe_time(userJson.getString(WXConst.USER_SUBSCRIBE_TIME)); 
						 users.add(user);
					 }
				}
			}
			model.addAttribute("users", users);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(redirectAttributes, "获取用户分组失败："+e.getMessage());
		}
		return "modules/api/msg";
	}
	@ResponseBody
	@RequestMapping(value="send")
	public String send(String sendContent,String groupId){
		Msg4GroupText client = new Msg4GroupText();
		client.setGroup_id(groupId);
		client.setContent(sendContent);
		try {
			HttpUtil.getInstance().sendGroupMsg(Global.getConfig(WXConst.GLOBAL_ACCESS_TOKEN), client);
			logger.info("向群组"+groupId+"发送消息:"+sendContent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
}
