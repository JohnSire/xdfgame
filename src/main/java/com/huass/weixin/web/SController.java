package com.huass.weixin.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huass.common.utils.DateUtils;
import com.huass.common.web.BaseController;
import com.huass.weixin.entity.Suggest;
import com.huass.weixin.entity.User;
import com.huass.weixin.service.SService;
import com.huass.weixin.service.SuggestService;
import com.huass.weixin.service.UserService;
import com.sun.jmx.snmp.Timestamp;

@Controller
@RequestMapping(value = "suggest")
public class SController extends BaseController {
	@Autowired
	private SuggestService suggestService;
	@Autowired
	private SService sService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="save")
	@ResponseBody
	public Map<String, String> save(HttpServletRequest request,HttpServletResponse response,Model model)
	{	
		Map<String, String> map = new HashMap<String, String>();
		String openId = request.getParameter("openId");
		
		if(!userService.isSubscribe(openId)){
			map.put("fanhui", "-1");
			return map;
		}else{
			Suggest suggest = new Suggest();
			String contentV = request.getParameter("contentV");
			User u = userService.findByOpenId(openId);
			suggest.setUserId(u.getId());
			String time = new Timestamp().toString();
			suggest.setDateStrD(time);
			suggest.setDateD(DateUtils.getDateTime());
			suggest.setContentV(contentV);
			suggestService.save(suggest);	
			List<Suggest> list = suggestService.findAll();
			model.addAttribute("list", list);
			model.addAttribute("openId", openId);
			map.put("fanhui", "1");
			return map;
		}
	}
	@RequestMapping(value="myvoucher")
	public String voucher(HttpServletRequest request){
		String openId = request.getParameter("openId");
		sService.voucher(openId);
		return null;
	}

}
