package com.huass.weixin.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huass.common.persistence.Page;
import com.huass.common.web.BaseController;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.BagLog;
import com.huass.weixin.entity.HuDong;
import com.huass.weixin.entity.SchoolBag;
import com.huass.weixin.entity.Tongji;
import com.huass.weixin.entity.User;
import com.huass.weixin.service.BagLogService;
import com.huass.weixin.service.SchoolBagService;
import com.huass.weixin.service.TjService;
import com.huass.weixin.service.UserService;
@Controller
@RequestMapping(value = "${adminPath}/tongji")
public class TongjiController extends BaseController {
	@Autowired
	private UserService userService;
	@Autowired
	private BagLogService bagLogService;
	@Autowired
	private SchoolBagService schoolBagService;

	@RequestMapping(value="tongji")
	public String tongji(Model model){
		String renshu = userService.getCountUser();
		model.addAttribute("renshu", renshu);
		int renci = bagLogService.count();
		model.addAttribute("renci", renci);
		 
		
		
		int xxhongbao = schoolBagService.xxhongbao();
		model.addAttribute("xxhongbao", xxhongbao);
		
		HuDong hudong = bagLogService.getHuDong();
		model.addAttribute("dianji", hudong.getHdNums());
		model.addAttribute("fenxiang", hudong.getShareNums());
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
		List<Object[]> schoolShare = schoolBagService.getSchoolShareNum();
		model.addAttribute("schoolShare", schoolShare);
		model.addAttribute("loginuser", "username");
		return "modules/tj/tongji";
	}
	
	
	/**
	 * 新增统计页面
	 * @param model
	 * @return
	 */
	@Autowired
	private TjService tjService;
	
	@RequestMapping(value="tjuser")
	public String tjuser(User user,HttpServletRequest request, HttpServletResponse response,Model model){
		
		model.addAttribute("userAmount", tjService.getUserAmount());
		
		Page<User> page = tjService.findUser(new Page<User>(request, response),user);
		model.addAttribute("page", page);
		return "modules/tj/tjuser";
	}
	
	@RequestMapping(value="tjbag")
	public String tjbag(BagLog bagLog,HttpServletRequest request, HttpServletResponse response,Model model){
		
		model.addAttribute("bagAmount", tjService.getBagAmount());
		Page<BagLog> page = tjService.findBaglog(new Page<BagLog>(request, response),bagLog);
		model.addAttribute("page", page);
		return "modules/tj/tjbag";
	}
	@RequestMapping(value="bag")
	public String bag(Bag bag,HttpServletRequest request, HttpServletResponse response,Model model){
		Page<Bag> page = tjService.findBag(new Page<Bag>(request, response),bag);
		model.addAttribute("page", page);
		return "modules/tj/bag";
	}
	@RequestMapping(value="schoolBag")
	public String schoolBag(SchoolBag schoolBag,HttpServletRequest request, HttpServletResponse response,Model model){
		Page<SchoolBag> page = tjService.findSchoolBag(new Page<SchoolBag>(request, response),schoolBag);
		model.addAttribute("page", page);
		return "modules/tj/schoolbag";
	}
}
