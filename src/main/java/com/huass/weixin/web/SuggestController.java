package com.huass.weixin.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huass.common.utils.DateUtils;
import com.huass.weixin.entity.Suggest;
import com.huass.weixin.entity.Tongji;
import com.huass.weixin.entity.User;
import com.huass.weixin.service.BagLogService;
import com.huass.weixin.service.SchoolBagService;
import com.huass.weixin.service.SuggestService;
import com.huass.weixin.service.UserService;
import com.huass.weixin.utils.HDAdd;
import com.huass.weixin.utils.suggestUser;

@Controller
@RequestMapping(value = "sc")
public class SuggestController {
	@Autowired
	private SuggestService suggestService;
	@Autowired
	private UserService userService;
	@Autowired
	private BagLogService bagLogService;
	@Autowired
	private SchoolBagService schoolBagService;

	@RequestMapping(value = "save")
	@ResponseBody
	@HDAdd
	public String saveSuggest(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
	
		String content = request.getParameter("suggest");
		String openId = request.getParameter("openId");
		userService.isSubscribe(openId);
		Suggest suggest = new Suggest();
		User user1 = userService.findByOpenId(openId);
		suggest.setDateD(DateUtils.getDateTime());
		suggest.setContentV(content);
		suggest.setUserId(user1.getId());
		suggestService.save(suggest);
		return "success";
	}

	@RequestMapping(value = "get")
	@HDAdd
	public String getUserSuggest(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		HttpSession session = request.getSession();
		String userName = request.getParameter("username");
		String pw = request.getParameter("pw");
		String user = (String) session.getAttribute("loginuser");
		if("admin".equals(userName) && "xdfbag0815".equals(pw))
		{
			session.setAttribute("loginuser", userName + "#" + pw);
		}
		else if(user != null &&(!"".equals(userName)) && "admin#xdfbag0815".equals(user))
		{
			
		}
		else
		{
			return "login";
		}
				String renshu = userService.getCountUser();
				model.addAttribute("renshu", renshu);
				int renci = bagLogService.count();
				model.addAttribute("renci", renci);
				 
				
				
				int xxhongbao = schoolBagService.xxhongbao();
				model.addAttribute("xxhongbao", xxhongbao);
				
				List tongji = bagLogService.tongji();
				
				List<Tongji> list = new ArrayList<Tongji>();
				for (int i = 0; i < tongji.size(); i++) {
					Object [] obje = (Object[])tongji.get(i);
					Tongji tj = new Tongji();
					tj.setXuexiao(obje[0].toString());
					tj.setSum(Integer.parseInt(obje[1].toString()));
					tj.setLingqu(obje[2].toString());
					list.add(tj);
					
				}
				model.addAttribute("list",list);
				return "tongji";
	}
	
	@RequestMapping(value="suggestAll")
	public String findAll(Model model){
		List<Object> lists = suggestService.suggestAll();
		List<suggestUser> list = new ArrayList<suggestUser>();
		for (int i = 0; i < lists.size(); i++) {
			suggestUser su = new suggestUser();
			Object[] obje = (Object[])lists.get(i);
			try {su.setId(obje[0].toString());} catch (Exception e) {}
			try {su.setUserName(obje[1].toString());} catch (Exception e) {}
			try {su.setSuggestion(obje[2].toString());} catch (Exception e) {}
			try {su.setDatetime(obje[3].toString());} catch (Exception e) {}
			list.add(su);
		}
		model.addAttribute("list",list);
		return "suggest_1";
	}
}
