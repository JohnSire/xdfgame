package com.huass.weixin.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.huass.common.config.Global;
import com.huass.common.utils.Collections3;
import com.huass.common.utils.DateUtils;
import com.huass.common.utils.StringUtils;
import com.huass.common.utils.wxApiUtils;
import com.huass.common.web.BaseController;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.BagLog;
import com.huass.weixin.entity.School;
import com.huass.weixin.entity.SchoolBag;
import com.huass.weixin.entity.Temp;
import com.huass.weixin.entity.User;
import com.huass.weixin.entity.Voucher;
import com.huass.weixin.service.BagLogService;
import com.huass.weixin.service.BagService;
import com.huass.weixin.service.DrawBagService;
import com.huass.weixin.service.SchoolBagService;
import com.huass.weixin.service.SchoolBingoService;
import com.huass.weixin.service.SchoolService;
import com.huass.weixin.service.TempService;
import com.huass.weixin.service.UserService;
import com.huass.weixin.service.VoucherService;

@Controller
@RequestMapping(value="pkbag")
public class pkBagController extends BaseController 
{
	@Autowired
	private BagService bagService;
	@Autowired
	private BagLogService baglogService;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private UserService userService;
	@Autowired
	private TempService tempService;
	@Autowired
	private SchoolBingoService schoolbingoService;
	@Autowired
	private SchoolBagService schoolbagService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private DrawBagService drawBagService;
	
	
	@RequestMapping(value="open")
	public String open(HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception {
		String dbagId = request.getParameter("dbagId");
		
		String bagId = request.getParameter("bagId");  
		
		String openId = request.getParameter("openId");
		
		User user = userService.findByOpenId(openId);  
		
		Bag bag = bagService.findById(bagId);  
		User users = userService.getById(bag.getUserId());
		SchoolBag sbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
		
		if(bag.getType() == 1){
			if(user==null){
				if(baglogService.isFullBag(bagId)){
					return "wandan";
				}else{
					
					Temp temps = drawBagService.getTemp(openId, bagId);
					if (temps!=null) { 
						model.addAttribute("money", temps.getMoneyN());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("voucherId", temps.getVoucherId());
					}else{
						
						if(userService.everyNum(openId, DateUtils.getDate())){
							model.addAttribute("openId",openId);
							return "msg2";
						}
						
						if (bag != null && bag.getNumN() > 0) {
							bag.setNumN(bag.getNumN() - 1);
							bagService.update(bag);
						}
						
						Voucher voucher = voucherService.bingoVoucher(sbag.getId());
						voucher.setStatusN(1);
						voucherService.updateVoucher(voucher);
						model.addAttribute("money", voucher.getMoneyN());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId); 
						model.addAttribute("voucherId", voucher.getId()); 
						model.addAttribute("dbagId", dbagId);
						
						Temp temp = new Temp();
						temp.setBagId(bagId);
						temp.setMoneyN(voucher.getMoneyN());
						temp.setOpenId(openId);
						temp.setVoucherId(voucher.getId());
						tempService.save(temp);
					}
					return "pkopen";
				}
			}else{
				
				if(baglogService.isFullBag(bagId)){
					
					if(tempService.findTempByUserId(openId, bagId)) {
						Temp ttemp = tempService.findTemp(openId, bagId);
						if(ttemp!=null){
							model.addAttribute("money", ttemp.getMoneyN());
							model.addAttribute("voucherId",ttemp.getVoucherId());
							model.addAttribute("bagId", bagId);
							model.addAttribute("openId", openId);
						}
						User master = userService.getById(bag.getUserId());
						if (master != null) {
							model.addAttribute("masterName", 
									master.getNameV());
						}
						return "pkfxhb";
					}else{
						model.addAttribute("money", 0);
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("headImg", user.getHeadImage());
						
						List<BagLog> list = baglogService.
									findAllDivBags(bagId);
						List newList = new ArrayList();
						if (Collections3.isNotEmpty(list)) {
							for (BagLog blog : list) {
								if (!blog.getUserId().equals(user.getId())) {
									Map map = new HashMap();
									map.put("getTime",blog.getDateD());
									User uu = userService.getById(blog.getUserId());
									map.put("himg", uu.getHeadImage());
									map.put("nameV", uu.getNameV());
									map.put("moneys", blog.getMoneyN());
									map.put("content", blog.getContentV());
									newList.add(map);
								}
							}
							model.addAttribute("list", newList);
						}
					}
					return "pkdiyBagOver";
				}else{
					
					if(baglogService.isOpened(openId, bagId)){ 
						BagLog bl = baglogService.fingBagLog(user.getId(), bagId);
						model.addAttribute("money", bl.getMoneyN());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("headImg", users.getHeadImage());
						model.addAttribute("dbagId", dbagId);
						
						List<BagLog> list = baglogService.findAllDivBags(bagId);
						List newList = new ArrayList();
						if(Collections3.isNotEmpty(list)){
							for(BagLog blog : list){
								if(!blog.getUserId().equals(users.getId()))
								{
									Map map = new HashMap();
									User uu = userService.getById(blog.getUserId());
									map.put("himg", uu.getHeadImage());
									map.put("nameV", uu.getNameV());
									map.put("moneys", blog.getMoneyN());
									map.put("content", blog.getContentV());
									map.put("getTime", blog.getDateD());
									newList.add(map);
								}
							}
							model.addAttribute("list",newList);
						}
						return "pkgetDiySuccess";
					}
					
					if (tempService.findTempByUserId(openId, bagId)) {
						Temp ttemp = tempService.findTemp(openId, bagId);
						model.addAttribute("money", ttemp.getMoneyN());
						model.addAttribute("voucherId", ttemp.getVoucherId());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("userName", users.getNameV());
						model.addAttribute("img", users.getHeadImage());
					}else{
						
						if(userService.everyNum(openId, DateUtils.getDate())){
							model.addAttribute("openId",openId);
							return "msg2";
						}
						if(bag != null && bag.getNumN() > 0){
							
							bag.setNumN(bag.getNumN() - 1);
							bagService.update(bag);
						}
							
						if(StringUtils.isBlank(user.getShareDate())){
							user.setShareDate(DateUtils.getDate());
							user.setShareNum(1);
							userService.update(user);
						}
						if(DateUtils.getDate().equals(user.getShareDate())){
							user.setShareNum(user.getShareNum()+1);
							userService.update(user);
						}else{
							user.setShareDate(DateUtils.getDate());
							user.setShareNum(1);
							userService.update(user);
						}
						
						Voucher voucher = voucherService
								.bingoVoucher(sbag.getId());
						voucher.setStatusN(1);
						voucherService.updateVoucher(voucher);
						model.addAttribute("money", voucher.getMoneyN());
						model.addAttribute("voucherId", voucher.getId());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("userName", users.getNameV());
						model.addAttribute("img", users.getHeadImage());
						model.addAttribute("dbagId", dbagId);
						
						BagLog bagLog = new BagLog();
						bagLog.setBagId(bagId);
						if(StringUtils.isNotBlank(voucher.getMoneyN().toString())){
							bagLog.setMoneyN(Integer.parseInt(voucher.getMoneyN().toString()));
						}
						bagLog.setUserId(user.getId());
						Date dt=new Date();
					    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
						bagLog.setDateD(matter1.format(dt));
						bagLog.setVoucherId(voucher.getId());
						baglogService.save(bagLog);	
						
						
						Bag bag1 = bagService.findById(bagId);
						SchoolBag sb = schoolbagService.findBySchoolBagId(bag1.getSchoolBagId());
						User uuu = userService.getById(bag.getUserId());
						Map<String,String> urlParaMap = new HashMap<String, String>();
						urlParaMap.put("(bagId)", bagId);
						urlParaMap.put("(openId)", openId);
						wxApiUtils wxUtil = new wxApiUtils();
						wxUtil.sendMyBagFirstMsg(uuu.getOpenId(), user.getNameV(), sb.getHeadImage(), voucher.getMoneyN().toString(), urlParaMap);
						
						wxUtil.sendFriendBagMsg(openId, uuu.getNameV(), sb.getHeadImage(), voucher.getMoneyN().toString(), urlParaMap);
						
						model.addAttribute("hengimage", sb.getHeadImage());
						
						
						List<BagLog> list = baglogService.findAllDivBags(bagId);
						List newList = new ArrayList();
						if(Collections3.isNotEmpty(list)){
							for(BagLog blog : list){
								if(!blog.getUserId().equals(users.getId()))
								{
									Map map = new HashMap();
									User uu = userService.getById(blog.getUserId());
									map.put("himg", uu.getHeadImage());
									map.put("nameV", uu.getNameV());
									map.put("moneys", blog.getMoneyN());
									map.put("content", blog.getContentV());
									map.put("getTime",blog.getDateD());
									newList.add(map);
								}
							}
							model.addAttribute("list",newList);
						}
					}
				}
				return "pkgetDiySuccess";
			}
		}else{
			
			
			if(user==null){
				
				if(baglogService.isFullBag(bagId)){
					return "wandan";
				}else{
					
					Temp temps = drawBagService.getTemp(openId, bagId);
					if (temps!=null) {
						model.addAttribute("money", temps.getMoneyN());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("voucherId", temps.getVoucherId());
					}else{
						
						if(userService.everyNum(openId, DateUtils.getDate())){
							model.addAttribute("openId",openId);
							return "msg2";
						}
						if(bag != null && bag.getNumN() > 0){
							
							bag.setNumN(bag.getNumN() - 1);
							bagService.update(bag);
						}
						if(bag.getType() == 0){
							
							SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
							if(schoolbag != null && schoolbag.getNumN() > 0){
								schoolbag .setNumN(schoolbag.getNumN()-1);
								schoolbagService.update(schoolbag); 
							}
						}
						
						Voucher voucher = voucherService.bingoVoucher(sbag.getId());
						voucher.setStatusN(1);
						voucherService.updateVoucher(voucher);
						model.addAttribute("money", voucher.getMoneyN());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId); 
						model.addAttribute("voucherId", voucher.getId()); 
						
						Temp temp = new Temp();
						temp.setBagId(bagId);
						temp.setMoneyN(voucher.getMoneyN());
						temp.setOpenId(openId);
						temp.setVoucherId(voucher.getId());
						tempService.save(temp);
					}
					return "pkopen";
				}
			}else{
				Voucher voucher = voucherService.bingoVoucher(sbag.getId());
				
				if(users == null){
					wxApiUtils wxUtil = new wxApiUtils();
					String newUser = wxUtil.getUserInfoFromWX1(openId);
					JSONObject obj = JSONObject.parseObject(newUser);
					if(!userService.isSubscribe1(openId))
					{
						String urls = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Global.getConfig("appid")+"&redirect_uri=http%3A%2F%2F"+Global.getConfig("DOMAIN_URL")+"%2Fxdfgame%2Fpkbag%2FSuccesss?openId=(openId)%26bagId=(bagId)%26money=(money)%26voucherId=(voucherId)&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
						String url = urls.replace("(openId)", openId).replace("(bagId)", bagId).replace("(money)", voucher.getMoneyN().toString()).replace("(voucherId)", voucher.getId());
						model.addAttribute("index", url);
						return "toindex";
					}
				}
				
								
				if(baglogService.isFullBag(bagId)){
					return "wandan";
				}else{
					
					if (tempService.findTempByUserId(openId, bagId)) {
						Temp ttemp = tempService.findTemp(openId, bagId);
						model.addAttribute("money", ttemp.getMoneyN());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
					}else{
						
						if(userService.everyNum(openId, DateUtils.getDate())){
							model.addAttribute("openId",openId);
							return "msg2";
						}
						if(bag != null && bag.getNumN() > 0){
							
							bag.setNumN(bag.getNumN() - 1);
							bagService.update(bag);
						}
						if(bag.getType() == 0){
							
							SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
							if(schoolbag != null && schoolbag.getNumN() > 0){
								schoolbag .setNumN(schoolbag.getNumN()-1);
								schoolbagService.update(schoolbag); 
							}
						}
						
						voucher.setStatusN(1);
						voucherService.updateVoucher(voucher);
						Bag bag1 = new Bag();
						bag1.setUserId(users.getId());
						bag1.setSchoolBagId(bag.getSchoolBagId());
						bag1.setType(bag1.xiao_bag_type);
						bagService.save(bag1);
						model.addAttribute("money", voucher.getMoneyN());
						model.addAttribute("dbagId", bagId);
						model.addAttribute("bagId", bag1.getId());
						model.addAttribute("openId", openId); 
						BagLog bagLog = new BagLog();
						bagLog.setBagId(bagId);
						if(StringUtils.isNotBlank(voucher.getMoneyN().toString())){
							bagLog.setMoneyN(Integer.parseInt(voucher.getMoneyN().toString()));
						}
						bagLog.setUserId(user.getId());
						Date dt=new Date();
						SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
						bagLog.setDateD(matter1.format(dt));
						bagLog.setVoucherId(voucher.getId());
						baglogService.save(bagLog);
						
						Temp temp = tempService.findTemp(user.getId(), bagId);
						if(temp!=null){
							tempService.delete(temp);
						}
						
						
						Bag bag2 = bagService.findById(bagId);
						SchoolBag schools = schoolbagService.findBySchoolBagId(bag2.getSchoolBagId());
						User uu = userService.getById(bag2.getUserId());
						Map<String,String> urlParaMap = new HashMap<String, String>();
						urlParaMap.put("(openId)", openId);
						wxApiUtils w = new wxApiUtils();
						w.sendQiangMsg(openId, voucher.getMoneyN().toString(), urlParaMap, bagId, bag1.getId(), schools.getHeadImage());
						
						model.addAttribute("hengimage", schools.getHeadImage());
						
						
						if(StringUtils.isBlank(user.getShareDate())){
							user.setShareDate(DateUtils.getDate());
							user.setShareNum(1);
							userService.update(user);
						}else{
							if(DateUtils.getDate().equals(user.getShareDate())){
								user.setShareNum(user.getShareNum()+1);
								userService.update(user);
							}else{
								user.setShareDate(DateUtils.getDate());
								user.setShareNum(1);
								userService.update(user);
							}
						}
					}
				}
				if(user.getShareNum()==0){
					return "pksuccess";
				}
				return "pksuccesss";
			}
		}
	}
	
	
	@RequestMapping(value="Success")
	public String Success(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String openId = request.getParameter("openId");
		String bagId = request.getParameter("bagId");
		String money = request.getParameter("money");
		String voucherId = request.getParameter("voucherId");
		Bag bags = bagService.findById(bagId);
		User users = userService.findByOpenId(openId);
		if(users == null){
			wxApiUtils wxUtil = new wxApiUtils();
			String newUser = wxUtil.getUserInfoFromWX1(openId);
			JSONObject obj = JSONObject.parseObject(newUser);
			if(!userService.isSubscribe1(openId))
			{
				String urls = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Global.getConfig("appid")+"&redirect_uri=http%3A%2F%2F"+Global.getConfig("DOMAIN_URL")+"%2Fxdfgame%2Fpkbag%2FSuccesss?openId=(openId)%26bagId=(bagId)%26money=(money)%26voucherId=(voucherId)&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
				String url = urls.replace("(openId)", openId).replace("(bagId)", bagId).replace("(money)", money).replace("(voucherId)", voucherId);
				model.addAttribute("index", url);
				return "toindex";
			}
		}
		User user = userService.findByOpenId(openId);
		
		Bag bag = new Bag();
		bag.setUserId(users.getId());
		bag.setSchoolBagId(bags.getSchoolBagId());
		bag.setType(bag.xiao_bag_type);
		bagService.save(bag);
		model.addAttribute("dbagId", bagId);
		model.addAttribute("bagId", bag.getId());
		model.addAttribute("openId", openId);
		model.addAttribute("money", money);
		
		BagLog bagLog = new BagLog();
		bagLog.setBagId(bagId);
		if(StringUtils.isNotBlank(money)){
			bagLog.setMoneyN(Integer.parseInt(money));
		}
		bagLog.setUserId(user.getId());
		Date dt=new Date();
		SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
		bagLog.setDateD(matter1.format(dt));
		bagLog.setVoucherId(voucherId);
		baglogService.save(bagLog);
		
		Temp temp = tempService.findTemp(user.getId(), bagId);
		if(temp!=null){
			tempService.delete(temp);
		}
		
		if(StringUtils.isBlank(user.getShareDate())){
			user.setShareDate(DateUtils.getDate());
			user.setShareNum(1);
			userService.update(user);
		}else{
			if(DateUtils.getDate().equals(user.getShareDate())){
				user.setShareNum(user.getShareNum()+1);
				userService.update(user);
			}else{
				user.setShareDate(DateUtils.getDate());
				user.setShareNum(1);
				userService.update(user);
			}
		}
		
		Bag bagg = bagService.findById(bagId);
		SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bagg.getSchoolBagId());
		Map<String,String> urlParaMap = new HashMap<String, String>();
		urlParaMap.put("(openId)", openId);
		wxApiUtils w = new wxApiUtils();
		w.sendQiangMsg(openId, money, urlParaMap, bagId, bag.getId(), schoolbag.getHeadImage());
		
		model.addAttribute("hengimage", schoolbag.getHeadImage());
		if(users.getShareNum()==0){
			return "pksuccess";
		}else{
			return "pkaaa";
		}
	}
	
	@RequestMapping(value="Successs")
	public String Successs(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String code = request.getParameter("code");
		String openId = request.getParameter("openId");
		String bagId = request.getParameter("bagId");
		String money = request.getParameter("money");
		String voucherId = request.getParameter("voucherId");
		Bag bags = bagService.findById(bagId);
		
		if(StringUtils.isNotBlank(code))
		{
			String userInfo=wxApiUtils.getTokenAndOpenId(code);
			JSONObject userJson = JSONObject.parseObject(userInfo);
			String openIds = userJson.getString("openid");
			if(!userService.isSubscribe(openIds))
			{
				userService.saveUser(userJson);
			}
		}
		
		User users = userService.findByOpenId(openId);
		
		if(baglogService.isOpened(openId, bagId)){   
			BagLog bl = baglogService.fingBagLog(users.getId(), bagId);
			model.addAttribute("money", bl.getMoneyN());
			model.addAttribute("bagId", bagId);
			model.addAttribute("openId", openId);
			model.addAttribute("headImg", users.getHeadImage());
			
			List<BagLog> list = baglogService.findAllDivBags(bagId);
			List newList = new ArrayList();
			if(Collections3.isNotEmpty(list)){
				for(BagLog blog : list){
					if(!blog.getUserId().equals(users.getId()))
					{
						Map map = new HashMap();
						User uu = userService.getById(blog.getUserId());
						map.put("himg", uu.getHeadImage());
						map.put("nameV", uu.getNameV());
						map.put("moneys", blog.getMoneyN());
						map.put("content", blog.getContentV());
						map.put("getDate", blog.getDateD());
						newList.add(map);
					}
				}
				model.addAttribute("list",newList);
			}
			return "pksuccess";
		}else{
			User user = userService.findByOpenId(openId);
			
			Bag bag = new Bag();
			bag.setUserId(users.getId());
			bag.setSchoolBagId(bags.getSchoolBagId());
			bag.setType(bag.xiao_bag_type);
			bagService.save(bag);
			model.addAttribute("dbagId", bagId);
			model.addAttribute("bagId", bag.getId());
			model.addAttribute("openId", openId);
			model.addAttribute("money", money);
			
			BagLog bagLog = new BagLog();
			bagLog.setBagId(bagId);
			if(StringUtils.isNotBlank(money)){
				bagLog.setMoneyN(Integer.parseInt(money));
			}
			bagLog.setUserId(user.getId());
			Date dt=new Date();
		    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
			bagLog.setDateD(matter1.format(dt));
			bagLog.setVoucherId(voucherId);
			baglogService.save(bagLog);
			
			Temp temp = tempService.findTemp(user.getId(), bagId);
			if(temp!=null){
				tempService.delete(temp);
			}
			
			if(StringUtils.isBlank(user.getShareDate())){
				user.setShareDate(DateUtils.getDate());
				user.setShareNum(1);
				userService.update(user);
			}else{
				if(DateUtils.getDate().equals(user.getShareDate())){
					user.setShareNum(user.getShareNum()+1);
					userService.update(user);
				}else{
					user.setShareDate(DateUtils.getDate());
					user.setShareNum(1);
					userService.update(user);
				}
			}
			if(users.getShareNum()==0){
				return "pksuccess";
			}else{
				return "pkaaa";
			}
		}
	}
	
	
	@RequestMapping(value="city")
	@ResponseBody
	private Map<String, String> city(String jingdu, String weidu) throws ClientProtocolException, IOException{
		Map<String, String> map = new HashMap<String, String>();
		String aa = "http://api.map.baidu.com/geocoder?location=纬度,经度&output=json";
		String bb = aa.replaceAll("纬度", weidu).replaceAll("经度", jingdu);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(bb);
		HttpResponse response = httpclient.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(), "utf-8");
		JSONObject obj = JSONObject.parseObject(result);
		String result1 = obj.getString("result");
		JSONObject obj1 = JSONObject.parseObject(result1);
		String addressComponent = obj1.getString("addressComponent");
		JSONObject obj2 = JSONObject.parseObject(addressComponent);
		String city = obj2.getString("city");
		map.put("city", city.substring(0, city.length()-1));
		School school = schoolService.findByCity(city.substring(0, city.length()-1));
		
		if(school == null){
			map.put("city", "-1");
		}else{
			String str = school.urlN;
			int cc = str.indexOf("bagId=");
			String bagId = str.substring(cc+6, cc+6+32);
			map.put("bagId", bagId);
		} 
		return map;
	}
	
	
	@RequestMapping(value="xiaofenxiangsuccess")
	public String xiaofenxiangsuccess(HttpServletRequest request, HttpServletResponse response, Model model){
		String xbagId = request.getParameter("xbagId");
		String dbagId = request.getParameter("dbagId");
		String openId = request.getParameter("openId");
		Bag bag = bagService.findById(xbagId);
		bag.setYesorno(1);
		bagService.update(bag);
		model.addAttribute("openId", openId);
		model.addAttribute("bagId", dbagId);
		return "pkfenxiangSuccess";
	}
	
	
	@RequestMapping(value="mybag")
	public String mybag(HttpServletRequest request, HttpServletResponse response, Model model){
		String opId = request.getParameter("openId");
		String bagId = request.getParameter("bagId");
		String dbagId = request.getParameter("dbagId");
		String qufen = request.getParameter("qufen");
		if(StringUtils.isNotBlank(opId)){
			List<BagLog> list = baglogService.findMybags(opId,0,9);
			if(list.size() == 0){
				model.addAttribute("openId", opId);
				model.addAttribute("bagId", bagId);
				model.addAttribute("qufen", qufen);
				return "pkhaimeilinghongbao";
			}else{
				List newList = new ArrayList();
				if(Collections3.isNotEmpty(list)){
					for(BagLog bagLog : list){
						Map map = new HashMap();
						Bag bag  = bagService.findById(bagLog.getBagId());
						map.put("getDate", bagLog.getDateD());
						map.put("money", bagLog.getMoneyN());	
						map.put("voucherId", bagLog.getVoucherId());
						map.put("bagId", bag.getId());
						User user = userService.getById(bag.getUserId());
						map.put("userName", user.getNameV());
						newList.add(map);
					}
					model.addAttribute("list",newList);
					model.addAttribute("openId",opId);
					if(dbagId==null){
						model.addAttribute("bagIds", bagId);
					}else if(dbagId != null){
						model.addAttribute("bagIds", dbagId);
					}
					model.addAttribute("qufen", qufen);
				}
			}
		}
		return "pkmybags";
	}
	
	
	@RequestMapping(value="bagDetail")
	public String bagDetail(HttpServletRequest request,HttpServletResponse response, Model model){
		String voucherId = request.getParameter("voucherId");
		String bagType = request.getParameter("bagType");
		String openId = request.getParameter("openId");
		String bagId  = request.getParameter("bagId");
		String bagIds  = request.getParameter("bagIds");
		String qufen = request.getParameter("qufen");
		model.addAttribute("qufen", qufen);
		if(StringUtils.isNotBlank(voucherId)){
			 Voucher voucher = voucherService.getVoucherById(voucherId);
			 if(voucher!=null){
				model.addAttribute("money",voucher.getMoneyN());
				model.addAttribute("code",voucher.getCodeV());
			 }
		}
		model.addAttribute("bagId", bagId);
		model.addAttribute("openId",openId);
		model.addAttribute("bagType",bagType);
		model.addAttribute("bagIds", bagIds);
		if(StringUtils.isNotBlank(bagId)){
			Bag bag = bagService.findById(bagId);
			if(bag!=null){
				model.addAttribute("oName",userService.getById(bag.getUserId()).getNameV());
			}
		}
		return "pkmybagDetail";
	}
	
	
	@RequestMapping(value="allcity")
	public String alcity(HttpServletRequest request,HttpServletResponse response, Model model){
		String type = request.getParameter("type");
		List<School> list = schoolService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("type", type);
		return "allcity";
	}
	
	
	@RequestMapping(value="bagdetail")
	@ResponseBody
	public Map<String, String> bagdetail(HttpServletRequest request,HttpServletResponse response, Model model){
		Map<String, String> map = new HashMap<String, String>();
		String bagId = request.getParameter("bagId");
		Bag bag = bagService.findById(bagId);
		SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
		map.put("beijing", schoolbag.getBgtype());
		map.put("hendInage", schoolbag.getHeadImage());
		map.put("msg", schoolbag.getMsgV());
		return map;
	}
}
