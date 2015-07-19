package com.huass.weixin.command;


import java.text.SimpleDateFormat;
import java.util.Date;

import com.huass.common.config.Global;
import com.huass.common.utils.DateUtils;
import com.huass.common.utils.SpringContextHolder;
import com.huass.common.utils.wxApiUtils;
import com.huass.weixin.entity.User;
import com.huass.weixin.service.UserService;
import com.huass.weixin.utils.WXConst;
import com.huass.wxsdk.msg.Msg;
import com.huass.wxsdk.msg.Msg4Event;
import com.huass.wxsdk.msg.Msg4Text;

/**
 * 关注命令
 * @author iibm
 *
 */
public class AttentionCommand implements IMenuCommand
{
	private Msg msg;
	
	private UserService userService;
	
	public AttentionCommand(Msg msg)
	{
		super();
		this.msg = msg;
		userService = SpringContextHolder.getBean(UserService.class);
	}
	
	@Override
	public Msg execute()
	{
		Msg4Text mt = new Msg4Text();
		if(msg instanceof Msg4Event)
		{
			Msg4Event eventMsg = (Msg4Event) this.msg;
			String openId = msg.getFromUserName();
			if(Msg4Event.SUBSCRIBE.equals(eventMsg.getEvent()))
			{
				User users = userService.findByOpenId(openId);
				if(users == null){
					wxApiUtils wxUtil = new wxApiUtils();
					try {
						User newUser = wxUtil.getUserInfoFromWX(openId);
						Date dt=new Date();
					    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
						newUser.setLotteryDate(matter1.format(dt));
						userService.save(newUser);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					users.setStatusV(User.STATUS_NORMAL);
					userService.update(users);
				}
			}
			else if(Msg4Event.UNSUBSCRIBE.equals(eventMsg.getEvent()))
			{
				User user = userService.findByOpenId(openId);
				if(user != null)
				{
					user.setStatusV(User.STATUS_DELETE);
					userService.update(user);
				}
			}
			
			mt.setContent(Global.getConfig("GUANZHU_SHARE_TITLE"));
			mt.setCreateTime(DateUtils.getDateTime());
			mt.setFromUserName(msg.getToUserName());
			mt.setToUserName(msg.getFromUserName());
		}
		return mt;
	}
}

