package com.huass.weixin.web;

import java.io.IOException;
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
import com.huass.weixin.service.CityService;
import com.huass.weixin.service.DrawBagService;
import com.huass.weixin.service.SchoolBagService;
import com.huass.weixin.service.SchoolService;
import com.huass.weixin.service.TempService;
import com.huass.weixin.service.UserService;
import com.huass.weixin.service.VoucherService;

@Controller
@RequestMapping(value = "drawbag")
public class DrawBagController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private DrawBagService drawBagService;
	@Autowired
	private VoucherService voucherService;
	@Autowired
	private TempService tempService;
	@Autowired
	private BagLogService baglogService;
	@Autowired
	private BagService bagService;
	@Autowired
	private SchoolBagService schoolBagService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value = "schoolbag")
	public String schoolBag(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		String kejiancao = request.getParameter("kejiancao");
		if(StringUtils.isNotBlank(kejiancao)){
			request.getSession().setAttribute("kejiancao", kejiancao);
		}
		
		String openId = "";
		if (StringUtils.isNotBlank(code)) {
			openId = wxApiUtils.getOpenId(code);
		}else{
			openId = request.getParameter("openId");
		}
		User user = userService.findByOpenId(openId);
		if(user==null){
			userService.isSubscribe(openId);
		}
		
		School school = null;
		Bag bag = null;
		SchoolBag schoolbag = null;
		if(StringUtils.isBlank(bagId)){
			user = userService.findByOpenId(openId);
			String city = request.getParameter("city");
			if(city != null){
				String cn = cityService.findByCity(city);
				user.setCity(cn);
				userService.update(user);
			}
			
			if(StringUtils.isBlank(user.getCity())){
				model.addAttribute("openId", openId);
				return "nocity_";
			}
			school = schoolService.findByCity(user.getCity());
			
			if(school==null){
				model.addAttribute("openId", openId);
				return "nocity_";
			}
			
			String str = school.urlN;
			int cc = str.indexOf("bagId=");
			bagId = str.substring(cc+6, cc+6+32);
			bag = bagService.findById(bagId);
			schoolbag = schoolBagService.findBySchoolBagId(bag.getSchoolBagId());
		}else{
			bag = bagService.findById(bagId);
			schoolbag = schoolBagService.findById(bag.getSchoolBagId());
		}
		
		model.addAttribute("openId",openId);
		model.addAttribute("bagId", bag.getId());
		model.addAttribute("schoolbag", schoolbag);
		String kjc = request.getSession().getAttribute("kejiancao").toString();
		if(StringUtils.isNotBlank(kjc)){
			return "schoolBag_kjc";
		}
		return "schoolBag_";
	}
	@RequestMapping(value = "xbag")
	public String xbag(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception{
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		String dbagId = request.getParameter("dbagId");
		String openId = wxApiUtils.getOpenId(code);
		User user = userService.findByOpenId(openId);
		if(user==null){
			userService.isSubscribe(openId);
		}
		Bag bag = bagService.findById(bagId);
		SchoolBag schoolbag = schoolBagService.findById(bag.getSchoolBagId());
		
		model.addAttribute("openId",openId);
		model.addAttribute("bagId", bag.getId());
		model.addAttribute("schoolbag", schoolbag);
		model.addAttribute("dbagId", dbagId);
		
		return "schoolBag_";
	}

	
	@RequestMapping(value = "openbag")
	public String openBag(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String openId = request.getParameter("openId");
		String bagId = request.getParameter("bagId");
		String title = request.getParameter("title");
		String dbagId = request.getParameter("dbagId");
		
		if(StringUtils.isNotBlank(dbagId)){
			return openxbag( request, response, model); 
		}
		model.addAttribute("title", title);
		Bag bag = bagService.findById(bagId);
		SchoolBag schoolbag = schoolBagService.findById(bag.getSchoolBagId());
		Temp temp = drawBagService.getTemp(openId, bagId);	
		if (temp != null) {
			model.addAttribute("temp", temp);
		}else{
			if(bag.getType()==0){
				if(bag.getNumN()<1){
					model.addAttribute("openId", openId);
					model.addAttribute("bagId", bagId);
					return "late";
				}
			}else{
				if(bag.getNumN()<1 || schoolbag.getNumN()<1){
					model.addAttribute("openId", openId);
					model.addAttribute("bagId", bagId);
					return "late";
				}
			}
			temp = new Temp();
			Voucher voucher = voucherService.bingoVoucher(bag.getSchoolBagId());
			voucher.setStatusN(1);
			voucherService.updateVoucher(voucher);
			temp.setMoneyN(voucher.getMoneyN());
			temp.setTypeN(voucher.getTypeN());
			temp.setOpenId(openId);
			temp.setBagId(bagId);
			temp.setVoucherId(voucher.getId());
			tempService.save(temp);
			
			model.addAttribute("temp", temp);
			if(bag.getType()==0){
				bag.setNumN(bag.getNumN()-1);
				bagService.update(bag);
				schoolbag.setNumN(schoolbag.getNumN()-1);
				schoolBagService.update(schoolbag);
			}else{
				schoolbag.setNumN(schoolbag.getNumN()-1);
				schoolBagService.update(schoolbag);
				bagService.updateBySchoolbagId(schoolbag.getId());
			}
		}
		String ss = isFirst(request, response, model);
		if("0".equals(ss)){
			return "openBag_";
		}
		return drawBag(request, response, model); 
	}
	@RequestMapping(value = "openxbag")
	public String openxbag(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String code = request.getParameter("code");
		String openId = request.getParameter("openId");
		String bagId = request.getParameter("bagId");
		String title = request.getParameter("title");
		String dbagId = request.getParameter("dbagId");
		
		if(StringUtils.isNotBlank(code)){
			String userInfo = wxApiUtils.getTokenAndOpenId(code);
			JSONObject userJson = JSONObject.parseObject(userInfo);
			String openId1 = userJson.getString("openid");
			if(!userService.isSubscribe(openId1)){
				userService.saveUser(userJson);
			}
		}
		User user = userService.findByOpenId(openId);
		if(user==null){
			boolean isSubscribe = userService.isSubscribe(openId);
			if(!isSubscribe){
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Global.getConfig("appid")+"&redirect_uri" +
						"=http%3A%2F%2F"+Global.getConfig("DOMAIN_URL")+"%2Fxdfgame%2Fdrawbag%2Fopenxbag?openId=(openId)%26title=(title)%26" +
						"dbagId=(dbagId)%26bagId=(bagId)&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
				url = url.replace("(openId)", openId).replace("(bagId)", bagId).replace("(dbagId)", dbagId).replace("(title)", title);
				model.addAttribute("index", url);
				return "toindex";
			}
		}
		
		model.addAttribute("title", title);
		model.addAttribute("dbagId", dbagId);
		model.addAttribute("openId", openId);
		Bag bag = bagService.findById(bagId);
		SchoolBag schoolbag = schoolBagService.findById(bag.getSchoolBagId());
		
		boolean isOpend = baglogService.isOpened(openId, bagId);
		if(isOpend){
			BagLog baglog = baglogService.fingBagLog(user.getId(), bagId);
			model.addAttribute("baglog", baglog);
			model.addAttribute("headImg", user.getHeadImage());
			
			List<BagLog> list = baglogService.findAllDivBags(bagId);
			List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
			if(Collections3.isNotEmpty(list)){
				for(BagLog blog : list){
					if(!blog.getUserId().equals(user.getId()))
					{
						Map<String, String> map = new HashMap<String, String>();
						User uu = userService.getById(blog.getUserId());
						map.put("himg", uu.getHeadImage());
						map.put("nameV", uu.getNameV());
						map.put("moneys", blog.getMoneyN()+"");
						map.put("content", blog.getContentV());
						map.put("getTime", blog.getDateD());
						newList.add(map);
					}
				}
				model.addAttribute("list",newList);
			}
			return "lingquxhb";
		}else{
			if(bag.getNumN()<1 || schoolbag.getNumN()<1){
				model.addAttribute("openId", openId);
				model.addAttribute("bagId", bagId);
				return "late";
			}
			Voucher voucher = voucherService.bingoVoucher(bag.getSchoolBagId());
			voucher.setStatusN(1);
			voucherService.updateVoucher(voucher);
			BagLog baglog = new BagLog();
			baglog.setMoneyN(voucher.getMoneyN());
			baglog.setTypeN(voucher.getTypeN());
			baglog.setUserId(user.getId());
			baglog.setBagId(bag.getId());
			baglog.setDateD(DateUtils.getDate("yyyy-MM-dd"));
			baglog.setVoucherId(voucher.getId());
			baglog.setSchoolBagId(bag==null?null:bag.getSchoolBagId());
			baglogService.save(baglog);
			model.addAttribute("baglog", baglog);
			model.addAttribute("headImg", user.getHeadImage());
			
			List<BagLog> list = baglogService.findAllDivBags(bagId);
			List<Map<String, String>> newList = new ArrayList<Map<String, String>>();
			if(Collections3.isNotEmpty(list)){
				for(BagLog blog : list){
					if(!blog.getUserId().equals(user.getId()))
					{
						Map<String, String> map = new HashMap<String, String>();
						User uu = userService.getById(blog.getUserId());
						map.put("himg", uu.getHeadImage());
						map.put("nameV", uu.getNameV());
						map.put("moneys", blog.getMoneyN()+"");
						map.put("content", blog.getContentV());
						map.put("getTime", blog.getDateD());
						newList.add(map);
					}
				}
				model.addAttribute("list",newList);
			}
			schoolbag.setNumN(schoolbag.getNumN()-1);
			schoolBagService.update(schoolbag);
			bagService.updateBySchoolbagId(schoolbag.getId());
			
			return "lingquxhb"; 
		}
	}

	
	@RequestMapping(value = "mybag")
	public String myBag(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		String page = request.getParameter("page");
		String title = request.getParameter("title");
		
		String openId = "";
		if(StringUtils.isNotBlank(code)){
			openId = wxApiUtils.getOpenId(code);
		}else{
			openId = request.getParameter("openId");
		}
		model.addAttribute("title", title);
		model.addAttribute("openId", openId);
		model.addAttribute("bagId", bagId);
		if (page == null)
			page = "1";
		
		List<BagLog> list = baglogService.findMybags(openId, 0, 
				Integer.parseInt(page) * 4);
		if (list == null || list.size() < 1) {
			return "emptyBag_";
		}
		
		List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
		for(BagLog baglog:list){
			Map<String, String> map = new HashMap<String, String>();
			map.put("voucher_id", baglog.getVoucherId());
			map.put("moneyN", baglog.getMoneyN()+"");
			map.put("bagId", baglog.getBagId());
			Bag bag  = bagService.findById(baglog.getBagId());
			SchoolBag schoolbag = schoolBagService.findBySchoolBagId(bag.getSchoolBagId());
			map.put("bagName", schoolbag.getHeadImage());
			if(schoolbag!=null){
				School school = schoolService.findById(schoolbag.getSchoolId());
				map.put("schoolName", school.getNameV());
			}
			list1.add(map);
		}
		model.addAttribute("page", page);
		model.addAttribute("bagloglist", list1);
		return "myBag_";
	}
	
	@RequestMapping(value = "mybag1")
	@ResponseBody
	public String myBag1(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		String page = request.getParameter("page");
		String title = request.getParameter("title");
		
		String openId = "";
		if(StringUtils.isNotBlank(code)){
			openId = wxApiUtils.getOpenId(code);
		}else{
			openId = request.getParameter("openId");
		}
		model.addAttribute("title", title);
		model.addAttribute("openId", openId);
		model.addAttribute("bagId", bagId);
		if (page == null)
			page = "1";
		
		List<BagLog> list = baglogService.findMybags(openId, 0,
				Integer.parseInt(page) * 4);
		if (list == null || list.size() < 1) {
			return "emptyBag_";
		}
		
		List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
		for(BagLog baglog:list){
			Map<String, String> map = new HashMap<String, String>();
			map.put("voucher_id", baglog.getVoucherId());
			map.put("moneyN", baglog.getMoneyN()+"");
			map.put("bagId", baglog.getBagId());
			Bag bag  = bagService.findById(baglog.getBagId());
			SchoolBag schoolbag = schoolBagService.findBySchoolBagId(bag.getSchoolBagId());
			map.put("bagName", schoolbag.getHeadImage());
			if(schoolbag!=null){
				School school = schoolService.findById(schoolbag.getSchoolId());
				map.put("schoolName", school.getNameV());
			}
			list1.add(map);
		}
		model.addAttribute("page", page);
		model.addAttribute("bagloglist", list1);
		return JSONObject.toJSONString(list1);
	}

	
	@RequestMapping(value = "drawbag")
	public String drawBag(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		String code = request.getParameter("code");
		String openId = request.getParameter("openId");
		String bagId = request.getParameter("bagId");
		String title = request.getParameter("title");
		model.addAttribute("title", title);
		model.addAttribute("openId", openId);
		model.addAttribute("bagId", bagId);
		if(StringUtils.isNotBlank(code)){
			String userInfo = wxApiUtils.getTokenAndOpenId(code);
			JSONObject userJson = JSONObject.parseObject(userInfo);
			String openId1 = userJson.getString("openid");
			if(!userService.isSubscribe(openId1)){
				userService.saveUser(userJson);
			}
		}
		User user = userService.findByOpenId(openId);
		if(user==null){
			boolean isSubscribe = userService.isSubscribe(openId);
			if(!isSubscribe){
				String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Global.getConfig("appid")+"&redirect_uri" +
						"=http%3A%2F%2F"+Global.getConfig("DOMAIN_URL")+"%2Fxdfgame%2Fdrawbag%2Fdrawbag?openId=(openId)%26" +
						"bagId=(bagId)&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
				url = url.replace("(openId)", openId).replace("(bagId)", bagId);
				model.addAttribute("index", url);
				return "toindex";
			}
		}
		boolean bb = userService.everyNum(openId, DateUtils.getDate("yyyy-MM-dd"));
		
		if(bb){
			model.addAttribute("num", true);
			return "schoolBag_";
		}
		String isFirst = isFirst(request, response, model);
		
		Temp temp = drawBagService.getTemp(openId, bagId);
		Bag bag = bagService.findById(bagId);
		Bag xbag = new Bag();
		xbag.setUserId(user.getId());
		xbag.setSchoolBagId(bag.getSchoolBagId());
		xbag.setType(Bag.xiao_bag_type);
		bagService.save(xbag);
		model.addAttribute("xbag", xbag);
		BagLog baglog = new BagLog();
		baglog.setMoneyN(temp.getMoneyN());
		baglog.setTypeN(temp.getTypeN());
		baglog.setUserId(temp.getUserId());
		baglog.setBagId(temp.getBagId());
		baglog.setDateD((new Date().toLocaleString()));
		baglog.setVoucherId(temp.getVoucherId());
		baglog.setUserId(user==null?null:user.getId());
		baglog.setSchoolBagId(bag==null?null:bag.getSchoolBagId());
		baglogService.save(baglog);
		tempService.delete(temp);
		if(DateUtils.getDate("yyyy-MM-dd").equals(user.getShareDate().trim())){ 
			user.setShareNum(user.getShareNum()+1);
		}else{
			user.setShareNum(1);
			user.setShareDate(DateUtils.getDate("yyyy-MM-dd")); 
		}
		userService.update(user);
		model.addAttribute("money", temp.getMoneyN());
		
		if("0".equals(isFirst)){
			return "drawBagsuccess_";
		}
		return "drawBagsuccess_2";
	}
	

	
	@RequestMapping(value = "isFirst")
	@ResponseBody
	public String isFirst(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String openId = request.getParameter("openId");
		if (StringUtils.isNotBlank(openId)) {
			User user = userService.findByOpenId(openId);
			if(user!=null){
				int money = drawBagService.getAllBagMoney(user.getId());
				return money + "";
			}
		}
		return "0";
	}

	
	@RequestMapping(value = "voucherdetail")
	public String getVoucherDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String openId = request.getParameter("openId");
		String bagId = request.getParameter("bagId");
		String voucher_id = request.getParameter("voucher_id");
		String schoolName = request.getParameter("schoolName");
		String title = request.getParameter("title");
		model.addAttribute("title", title);
		model.addAttribute("schoolName", schoolName);
		model.addAttribute("openId", openId);
		model.addAttribute("bagId", bagId);
		if (StringUtils.isNotBlank(voucher_id)) {
			Voucher vc = voucherService.getVoucherById(voucher_id);
			
			if (vc != null) {
				model.addAttribute("voucher", vc);
				return "voucherDetail_";
			}
		}
		return "";
	}
	@RequestMapping(value="xbagsuccess")
	public String fenxiangxhb(HttpServletRequest request,
			HttpServletResponse response, Model model){
		String xbagId = request.getParameter("xbagId");
		String dbagId = request.getParameter("dbagId");
		String openId = request.getParameter("openId");
		String title = request.getParameter("title");
		Bag bag = bagService.findById(xbagId);
		bag.setYesorno(1);
		bagService.update(bag);
		model.addAttribute("title", title);
		model.addAttribute("openId", openId);
		model.addAttribute("dbagId", dbagId);
		return "fenxiangchenggong";
	}
	@RequestMapping(value="allcity")
	public String alcity(HttpServletRequest request,HttpServletResponse response, Model model){
		String type = request.getParameter("type");
		String title = request.getParameter("title");
		String bagId = request.getParameter("bagId");
		model.addAttribute("title", title);
		model.addAttribute("bagId", bagId);
		List<School> list = schoolService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("type", type);
		return "allcity_";
	}
	

	
	@RequestMapping(value="city")
	@ResponseBody
	public Map<String, String> city(String jingdu, String weidu) throws ClientProtocolException, IOException{
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
			map.put("urls", school.getUrlN());
		} 
		return map;
	}
	
}
