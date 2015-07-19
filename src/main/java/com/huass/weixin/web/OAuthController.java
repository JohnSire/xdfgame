package com.huass.weixin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.huass.common.config.Global;
import com.huass.common.utils.SpringContextHolder;
import com.huass.common.utils.StringUtils;
import com.huass.common.utils.wxApiUtils;
import com.huass.common.web.BaseController;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.City;
import com.huass.weixin.entity.School;
import com.huass.weixin.entity.SchoolBag;
import com.huass.weixin.entity.User;
import com.huass.weixin.service.BagService;
import com.huass.weixin.service.CityService;
import com.huass.weixin.service.SchoolBagService;
import com.huass.weixin.service.SchoolService;
import com.huass.weixin.service.UserService;
import com.huass.weixin.utils.HDAdd;
import com.huass.weixin.utils.WXConst;


@Controller
@RequestMapping(value="oauth")
public class OAuthController extends BaseController
{
	@Autowired
	private UserService userService;
	@Autowired
	private SchoolBagService schoolbagService;
	@Autowired
	private BagService bagService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private CityService cityService;
	
	
	@HDAdd
	@RequestMapping(value="index")
	public String index(HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception
	{
		String code = request.getParameter("code");
		String panduan = request.getParameter("panduan");
		String dbagId = request.getParameter("dbagId");
		System.out.println("index:code"+code);
		if(code == null){
			String openId = request.getParameter("openId");
			model.addAttribute("openId", openId);
		}
		if(StringUtils.isNotBlank(code))
		{
			String openId = wxApiUtils.getOpenId(code);
	        model.addAttribute("openId", openId);
		}
		if(panduan != null){
			if(panduan.equals("1")){
				String bagId = request.getParameter("bagId");
				Bag bag = bagService.findById(bagId);
				SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
				model.addAttribute("hengimage", schoolbag.getHeadImage());
				model.addAttribute("msg", schoolbag.getMsgV());
				model.addAttribute("beijing", schoolbag.getBgtype());
				model.addAttribute("bagId", bag.getId());
				
				model.addAttribute("moban", schoolbag.getMoban());
				model.addAttribute("schoolId", schoolbag.getSchoolId());
				return "home";
			}
		}
		String bagId = request.getParameter("bagId");
		Bag bag = bagService.findById(bagId);
		SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
		model.addAttribute("hengimage", schoolbag.getHeadImage());
		model.addAttribute("msg", schoolbag.getMsgV());
		model.addAttribute("beijing", schoolbag.getBgtype());
		model.addAttribute("bagId", bagId);
		
		model.addAttribute("moban", schoolbag.getMoban());
		model.addAttribute("schoolId", schoolbag.getSchoolId());
		if(dbagId!=null){
			model.addAttribute("dbagId",dbagId);
		}
		return "school";
	}
	
	
	@RequestMapping(value="isSubscribe/{openId}")
	@ResponseBody
	public String isSubscribe(@PathVariable String openId)
	{
		boolean flag = userService.isSubscribe(openId);
		return Boolean.toString(flag);
	}
	
	
	@RequestMapping(value="toindex")
	public String toindex(HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception
	{
		String openId = request.getParameter("openId");
		if(StringUtils.isNotBlank(openId))
		{
	        model.addAttribute("openId", openId);
	        boolean flag = userService.isSubscribe(openId);
	        if(flag)
			{
				User user = userService.findByOpenId(openId);
				model.addAttribute("headImage", user.getHeadImage());
				model.addAttribute("userName", user.getNameV());
				return "diyBagAgain";
			}
			else
			{
				return "home";
			}
		}
		else
		{
			model.addAttribute("index",
					Global.getConfig("GUANZHU_INDEX_URL"));
			return "toindex";
		}
	} 
	
	
	@RequestMapping(value="suggest")
	public String suggest(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String code=request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
		String openId=wxApiUtils.getOpenId(code);
		model.addAttribute("openId",openId);
		}
		return "suggest";
	}
	@RequestMapping(value="secret")
	public String secret(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String code=request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
		String openId=wxApiUtils.getOpenId(code);
		model.addAttribute("openId",openId);
		}
		return "redirect:/secret/checking";
	}
	@HDAdd
	@RequestMapping(value="xdfbag")
	public String bag(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String code=request.getParameter("code");
		System.out.println("xdfbag获取到的code"+code);
		if(StringUtils.isNotBlank(code)){
			String openId=wxApiUtils.getOpenId(code);
			System.out.println("xdfbag获取到的openId"+openId);
			if(StringUtils.isNotBlank(openId)){
				request.getSession().setAttribute("openId", openId);
			}
			if(StringUtils.isBlank(openId)){
				openId = (String) request.getSession().getAttribute("openId");
				System.out.println("xdfbag获取openId request "+openId);
			}
			User user = userService.findByOpenId(openId);
			if(user == null){
				userService.isSubscribe(openId);
			}
			System.out.println("xdfbag-user："+user);
			String city = request.getParameter("city");
			if(city != null){
				String cn = cityService.findByCity(city);
				user.setCity(cn);
				userService.update(user);
			}
			
			if(StringUtils.isBlank(user.getCity())){
				model.addAttribute("openId", openId);
				model.addAttribute("type", 1);
				return "meichengshi";
			}
			School school = schoolService.findByCity(user.getCity());
			
			if(school==null){ 
				model.addAttribute("openId", openId);
				model.addAttribute("type", 2);
				return "meichengshi";
			}
			String str = school.urlN;
			int cc = str.indexOf("bagId=");
			String bagId = str.substring(cc+6, cc+6+32);
			//Bag bag = bagService.findById(bagId);
			List<Bag> bags = bagService.getAll();
			if(bags.size()==0){
				return "nobags";
			}
			Bag bag = StringUtils.randomList(bags, 1).get(0);//使用随机选择bag
			System.out.println("随机选择的红包："+bag.getId());
			SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
			model.addAttribute("hengimage", schoolbag.getHeadImage());
			model.addAttribute("msg", schoolbag.getMsgV()); 
			model.addAttribute("beijing", schoolbag.getBgtype());
			model.addAttribute("bagId", bag.getId());
 			model.addAttribute("openId",openId);
 			
 			model.addAttribute("moban", schoolbag.getMoban());
 			model.addAttribute("schoolId", schoolbag.getSchoolId());
 			model.addAttribute("qufen", "1");
		}
		return "home";
	}
	
	@HDAdd
	@RequestMapping(value="Aishi")
	public String aishi(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception
	{
		String code=request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
			String openId=wxApiUtils.getOpenId(code);
			model.addAttribute("openId",openId);
		}
		return "teacher";
	}
	
	@RequestMapping(value="hideoauth")
	public String hideoauth(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception
	{
		String code=request.getParameter("code");
		System.out.println("hideoauth>>code:"+code);
		String bagId = request.getParameter("bagId");
		String dbagId = request.getParameter("dbagId");
		if(StringUtils.isNotBlank(code))
		{
			String userInfo=wxApiUtils.getTokenAndOpenId(code);
			JSONObject userJson = JSONObject.parseObject(userInfo);
			Object errorcode = userJson.get("errcode");
			if(errorcode == null)
			{
				String openId = userJson.getString("openid");
				if(!userService.isSubscribe(openId))
				{
					userService.saveUser(userJson);
					String url = Global.getConfig(WXConst.SHOUQUAN_URL).replace("(bagId)", bagId).replace("(dbagId)", dbagId);
					model.addAttribute("index", url);
					return "toindex";
				}
				else{
					return "redirect:/oauth/index?bagId="+bagId+"&dbagId="+dbagId+"&openId="+openId+"";
				}
			}
		}
		return "error";
	}
	
	@HDAdd
	@RequestMapping(value="dbag")
	public String dbag(HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception
	{
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		if(StringUtils.isNotBlank(code))
		{
			String userInfo=wxApiUtils.getTokenAndOpenId(code);
			JSONObject userJson = JSONObject.parseObject(userInfo);
			Object errorcode = userJson.get("errcode");
			if(errorcode == null)
			{
				String openId = userJson.getString("openid");
				
				if(!userService.isSubscribe(openId))
				{
					System.out.println("dbag iss:false");
					userService.saveUser(userJson);
					String urls = SpringContextHolder.getValue(WXConst.DIANJI_FENXIANG_XIAO);
					String url = urls.replace("(bagId)", bagId);
					model.addAttribute("index", url);
					return "toindex";
				}
				else{
				    model.addAttribute("openId", openId);
					Bag bag = bagService.findById(bagId);
					SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
					model.addAttribute("hengimage", schoolbag.getHeadImage());
					model.addAttribute("msg", schoolbag.getMsgV());
					model.addAttribute("beijing", schoolbag.getBgtype());
					model.addAttribute("bagId", bagId);
					
					model.addAttribute("moban", schoolbag.getMoban());
					model.addAttribute("schoolId", schoolbag.getSchoolId());
					return "school";
				}
			}
		}else{
			String openId = request.getParameter("openId");
			model.addAttribute("openId", openId);
			Bag bag = bagService.findById(bagId);
			SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
			model.addAttribute("hengimage", schoolbag.getHeadImage());
			model.addAttribute("msg", schoolbag.getMsgV());
			model.addAttribute("beijing", schoolbag.getBgtype());
			model.addAttribute("bagId", bagId);
			
			model.addAttribute("moban", schoolbag.getMoban());
			model.addAttribute("schoolId", schoolbag.getSchoolId());
			return "school";
		}
		return "error";
	}
	
	@HDAdd
	@RequestMapping(value="xbag")
	public String xbag(HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception
	{
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		String dbagId = request.getParameter("dbagId");
		System.out.println("xbag==code:"+code+"==bagId:"+bagId);
		if(StringUtils.isNotBlank(code))
		{
			String userInfo=wxApiUtils.getTokenAndOpenId(code);
			JSONObject userJson = JSONObject.parseObject(userInfo);
			Object errorcode = userJson.get("errcode");
			if(errorcode == null)
			{
				String openId = userJson.getString("openid");
				if(!userService.isSubscribe(openId))
				{
					userService.saveUser(userJson);
					String urls = SpringContextHolder.getValue(WXConst.DIANJI_FENXIANG_XIAO);
					String url = urls.replace("(bagId)", bagId).replace("(dbagId)", dbagId);
					model.addAttribute("index", url);
					return "toindex";
				}
				else{
					Bag bag = bagService.findById(bagId);
					SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
					model.addAttribute("hengimage", schoolbag.getHeadImage());
					model.addAttribute("msg", schoolbag.getMsgV());
					model.addAttribute("beijing", schoolbag.getBgtype());
					model.addAttribute("openId", openId);
					model.addAttribute("bagId", bagId);
					model.addAttribute("dbagId", dbagId);
					
					model.addAttribute("moban", schoolbag.getMoban());
					model.addAttribute("schoolId", schoolbag.getSchoolId());
					return "school";
				}
			}
		}
		return "error";
	}
	
	@RequestMapping(value="diyShow")
	public String diyShow(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String code=request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
			String openId=wxApiUtils.getOpenId(code);
			model.addAttribute("openId",openId);
		}else{
			return "error";
		}
		return "redirect:/user/secret";
	}
	
	@HDAdd
	@RequestMapping(value="pkindex")
	public String pkindex(HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception
	{
		String code = request.getParameter("code");
		String panduan = request.getParameter("panduan");
		String dbagId = request.getParameter("dbagId");
		if(code == null){
			String openId = request.getParameter("openId");
			model.addAttribute("openId", openId);
		}
		if(StringUtils.isNotBlank(code))
		{
			String openId = wxApiUtils.getOpenId(code);
	        model.addAttribute("openId", openId);
		}
		if(panduan != null){
			if(panduan.equals("1")){
				String bagId = request.getParameter("bagId");
				Bag bag = bagService.findById(bagId);
				SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
				model.addAttribute("hengimage", schoolbag.getHeadImage());
				model.addAttribute("msg", schoolbag.getMsgV());
				model.addAttribute("beijing", schoolbag.getBgtype());
				model.addAttribute("bagId", bag.getId());
				return "pkhome";
			}
		}
		String bagId = request.getParameter("bagId");
		Bag bag = bagService.findById(bagId);
		SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
		model.addAttribute("hendimage", schoolbag.getHeadImage());
		model.addAttribute("msg", schoolbag.getMsgV());
		model.addAttribute("bgtype", schoolbag.getBgtype());
		model.addAttribute("bagId", bagId);
		if(dbagId!=null){
			model.addAttribute("dbagId",dbagId);
		}
		return "pkschool";
	}
	
	@HDAdd
	@RequestMapping(value="pkdbag")
	public String pkdbag(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception
	{
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		String openId=wxApiUtils.getOpenId(code);
		Bag bag = bagService.findById(bagId);
		SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
		model.addAttribute("hendimage", schoolbag.getHeadImage());
		model.addAttribute("msg", schoolbag.getMsgV());
		model.addAttribute("bgtype", schoolbag.getBgtype());
		model.addAttribute("bagId", bagId);
		model.addAttribute("openId", openId);
		return "pkschool";
	}
	
	@HDAdd
	@RequestMapping(value="pkxbag")
	public String pkxbag(HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception
	{
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		String dbagId = request.getParameter("dbagId");
		String openId=wxApiUtils.getOpenId(code);
		if(StringUtils.isNotBlank(code))
		{
			Bag bag = bagService.findById(bagId);
			SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
			model.addAttribute("hendimage", schoolbag.getHeadImage());
			model.addAttribute("msg", schoolbag.getMsgV());
			model.addAttribute("bgtype", schoolbag.getBgtype());
			model.addAttribute("openId", openId);
			model.addAttribute("bagId", bagId);
			model.addAttribute("dbagId", dbagId);
			return "pkschool";
		}
		return "error";
	}
	
	@HDAdd
	@RequestMapping(value="pkbag")
	public String pkbag(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String code=request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
			String openId=wxApiUtils.getOpenId(code);
			User user = userService.findByOpenId(openId);
			School school = schoolService.findByCity(user.getCity());
			String str = school.urlN;
			int cc = str.indexOf("bagId=");
			String bagId = str.substring(cc+6, cc+6+32);
			Bag bag = bagService.findById(bagId);
			SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
			model.addAttribute("hengimage", schoolbag.getHeadImage());
			model.addAttribute("msg", schoolbag.getMsgV());
			model.addAttribute("beijing", schoolbag.getBgtype());
			model.addAttribute("bagId", bag.getId());
 			model.addAttribute("openId",openId);
		}
		return "pkhome";
	}
	
	@HDAdd
	@RequestMapping(value="guanzhuurl")
	public String guanzhuurl(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		String code = request.getParameter("code");
		if(StringUtils.isNotBlank(code)){
			String userInfo=wxApiUtils.getTokenAndOpenId(code);
			JSONObject userJson = JSONObject.parseObject(userInfo);
			Object errorcode = userJson.get("errcode");
			if(errorcode == null)
			{
				String openId = userJson.getString("openid");
				if(!userService.isSubscribe(openId))
				{
					userService.saveUser(userJson);
					String url = SpringContextHolder.getValue(WXConst.DIANJI_GONGGONG_FENXIANG);
					model.addAttribute("index", url);
					return "toindex";
				}
				else{
					User user = userService.findByOpenId(openId);
					if(StringUtils.isNotBlank(user.getCity())){
						School school = schoolService.findCity(user.getCity());
						if(school != null){
							model.addAttribute("index",school.getUrlN());
							return "toindex";
						}
					}
					return "redirect:/bag/allcity";
				}
			}
		}
		return "error";
	}
}
