package com.huass.weixin.command;

import com.huass.common.utils.DateUtils;
import com.huass.wxsdk.msg.Msg;
import com.huass.wxsdk.msg.Msg4Text;

/**
 * 关键词回复
 * 
 * @author Alex
 * {@code}
 * 			图文混合消息
 * 			Msg4ImageText it = new Msg4ImageText();
 * 			it.setCreateTime(DateUtils.getDateTime());
			it.setFromUserName(msg.getToUserName());
			it.setToUserName(msg.getFromUserName());
			Data4Item item1 = new Data4Item();
			item1.setPicUrl(Global.getConfig("WEIXIN_PIC_URL") + "meiqiuwang.jpg");
			item1.setTitle("不被题海淹没，青春就要有YONG，我们都是“中学小能手”");
			item1.setUrl("http://m.xdf.cn");
			it.addItem(item1);
			
			文本消息
			Msg4Text mt = new Msg4Text();
			mt.setCreateTime(DateUtils.getDateTime());
			mt.setFromUserName(msg.getToUserName());
			mt.setToUserName(msg.getFromUserName());
			mt.setContent("搜索“新东方深圳学校官方微信”，参与“世界杯，有奖连连猜”选出你最喜欢的球队，就有机会获得小米手机和死飞车等众多奖品！");
			return mt;
 *
 */
public class RespondCommand implements IMenuCommand
{
	private Msg msg;
	
	public RespondCommand(Msg msg)
	{
		super();
		this.msg = msg;
	}
	
	@Override
	public Msg execute()
	{
		Msg4Text mt = new Msg4Text();
		mt.setCreateTime(DateUtils.getDateTime());
		mt.setFromUserName(msg.getToUserName());
		mt.setToUserName(msg.getFromUserName());
		mt.setContent("开学季一起梦想分红！！免费红包拿到咱手软，根本停不下来马上点击菜单【玩坏红包】！");
		return mt;
	}

}
