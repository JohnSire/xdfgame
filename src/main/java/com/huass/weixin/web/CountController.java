package com.huass.weixin.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huass.common.web.BaseController;
import com.huass.weixin.service.HuDongService;
import com.huass.weixin.utils.WXConst;

@Controller
@RequestMapping(value="count")
public class CountController extends BaseController 
{
	@Autowired
	private HuDongService hdService;
	
	@RequestMapping(value="addshare")
	@ResponseBody
	public void addShare(HttpServletRequest request)
	{
		WXConst.HD_NUM++;
		String schoolId = request.getParameter("schoolId");
		hdService.addSchoolMemShareNum(schoolId);
	}
	
	@RequestMapping(value="addhd")
	@ResponseBody
	public void addhd()
	{
		WXConst.HD_NUM++;
	}
	
	@RequestMapping("savehd")
	@ResponseBody
	public void savehd()
	{
		hdService.addHuDongNum();
		WXConst.HD_NUM = 0L;
		WXConst.SHARE_NUM = 0L;
	}
	
	@RequestMapping("testhd")
	@ResponseBody
	public void testhd()
	{
		hdService.addSchoolMemShareNum("1");
	}
	
}
