package com.huass.weixin.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huass.common.utils.StringUtils;
import com.huass.common.utils.wxApiUtils;
import com.huass.common.web.BaseController;
import com.huass.weixin.command.CommandManager;
import com.huass.weixin.command.IMenuCommand;
import com.huass.weixin.command.RespondCommand;
import com.huass.weixin.entity.User;
import com.huass.weixin.service.UserService;
import com.huass.weixin.utils.WXConst;
import com.huass.wxsdk.DefaultSession;
import com.huass.wxsdk.HandleMessageAdapter;
import com.huass.wxsdk.MySecurity;
import com.huass.wxsdk.msg.Msg;
import com.huass.wxsdk.msg.Msg4Event;
import com.huass.wxsdk.msg.Msg4Head;
import com.huass.wxsdk.msg.Msg4Text;


@Controller
@RequestMapping(value = "weixin")
public class WeixinController extends BaseController
{
	@Autowired
	private UserService userService;
	
	protected static final Msg4Head Msg4Head = null;

	@ResponseBody
	@RequestMapping(value = "recMsg", method = { RequestMethod.GET,
			RequestMethod.POST })
	public void auth(HttpServletRequest request, HttpServletResponse response)
	{
		String method = request.getMethod();
		if ("GET".equals(method))
		{
			System.out.println("=====微信验证开始>>>>");
			this.get(request, response);
		} else if ("POST".equals(method))
		{
			System.out.println("=====微信消息发送>>>>");
			this.post(request, response);
		}
	}

	private void get(HttpServletRequest request, HttpServletResponse response)
	{
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		List<String> list = new ArrayList<String>(3) {
			private static final long serialVersionUID = 2621444383666420433L;

			public String toString()
			{
				return this.get(0) + this.get(1) + this.get(2);
			}
		};
		list.add(WXConst.TOKEN);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		String tmpStr = new MySecurity().encode(list.toString(),
				MySecurity.SHA_1);
		Writer out;
		try
		{
			out = response.getWriter();
			if (signature.equals(tmpStr))
			{
				out.write(echostr);
			} else
			{
				out.write("请求失败!");
			}
			out.flush();
			out.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void post(HttpServletRequest request, HttpServletResponse response) 
	{
		final DefaultSession session = DefaultSession.newInstance();
		try
		{
			InputStream is = request.getInputStream();
			OutputStream os = response.getOutputStream();
			session.addOnHandleMessageListener(new HandleMessageAdapter() {
				@Override
				public void onTextMsg(Msg4Text msg)
				{
					super.onTextMsg(msg);
					System.out.println("==文本消息>>>>");
					RespondCommand command = new RespondCommand(msg);
					Msg tomsg = command.execute();
					if(tomsg != null)
					{
						session.callback(tomsg);
					}
				}
				@Override
				public void onEventMsg(Msg4Event msg){
					super.onEventMsg(msg);
					String key = msg.getEvent();
					
					System.out.println("==事件>>>>类型"+key);
					if (Msg4Event.CLICK.equals(key)){
						key = msg.getEventKey();
						System.out.println("==CLIKC>>>>key"+key);
					}else if ("VIEW".equals(key)){
						
					}else if(Msg4Event.LOCATION.equals(key)){
						String aa = "http://api.map.baidu.com/geocoder?location=纬度,经度&output=json";
						String bb = aa.replaceAll("纬度", msg.getLatitude()).replaceAll("经度", msg.getLongitude());
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
							User user = userService.findByOpenId(msg.getFromUserName());
							if(user != null){
								user.setCity(city.substring(0, city.length()-1));
								user.setPsoiion(1);
								userService.update(user);
							}
						} catch (ClientProtocolException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} 
					}else{
						IMenuCommand command = CommandManager.getInstance().getCommand(key, msg);
						if (command != null){
							Msg tomsg = command.execute();
							if (tomsg != null){
								session.callback(tomsg);
							}
						}
					}
				}
			});
			session.process(is, os);
			session.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}
}
