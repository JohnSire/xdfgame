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
import com.huass.common.utils.SpringContextHolder;
import com.huass.common.utils.StringUtils;
import com.huass.common.utils.wxApiUtils;
import com.huass.common.web.BaseController;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.BagLog;
import com.huass.weixin.entity.School;
import com.huass.weixin.entity.SchoolBag;
import com.huass.weixin.entity.Temp;
import com.huass.weixin.entity.Url;
import com.huass.weixin.entity.User;
import com.huass.weixin.entity.Voucher;
import com.huass.weixin.service.BagLogService;
import com.huass.weixin.service.BagService;
import com.huass.weixin.service.HuDongService;
import com.huass.weixin.service.SchoolBagService;
import com.huass.weixin.service.SchoolService;
import com.huass.weixin.service.TempService;
import com.huass.weixin.service.UrlService;
import com.huass.weixin.service.UserService;
import com.huass.weixin.service.VoucherService;
import com.huass.weixin.utils.HDAdd;
import com.huass.weixin.utils.WXConst;

@Controller
@RequestMapping(value="bag")
public class BagController extends BaseController 
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
	private SchoolBagService schoolbagService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private HuDongService hudongService;
	@Autowired
	private UrlService urlService;
	
	/**
	 * 用户打开分享的红包
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@HDAdd
	@RequestMapping(value="open")
	public String open(HttpServletRequest request,	HttpServletResponse response, Model model) throws Exception {
		String dbagId = request.getParameter("dbagId");
		/** 获取红包ID */
		String bagId = request.getParameter("bagId");  
		/** 获得点击链接用户的openId */
		String openId = request.getParameter("openId");
		/** 根据点击链接的用户的openID查询用户信息 */
		User user = userService.findByOpenId(openId);  
		String qufen = request.getParameter("qufen");
		/** 根据红包ID查询红包 */
		Bag bag = bagService.findById(bagId); 
		Bag b = bagService.findById(dbagId);
		User users = userService.getById(bag.getUserId());
		SchoolBag sbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
		if(sbag.getNumN() <= 0){
			if(StringUtils.isBlank(dbagId)){
				model.addAttribute("bagId", bagId);
			}else{
				model.addAttribute("bagId", dbagId);
			}
			model.addAttribute("qufen", qufen);
			model.addAttribute("openId", openId);
			return "wandan";
		}
		/** 判断点击的是大红包还是小红包    如果是小红包   进入下面方法 */
		if(bag.getType() == 1){
				/** 判断红包是否领完 */
				if(baglogService.isFullBag(bagId)){
					/** 判断临时表是否有记录 */
					model.addAttribute("bagId", dbagId);
					model.addAttribute("openId", openId);
					model.addAttribute("qufen", qufen);
					return "wandan";
				}else{
					/** 判断是否已经领过红包 */
					if(baglogService.isOpened(openId, bagId)){ 
						BagLog bl = baglogService.fingBagLog(user.getId(), bagId);
						model.addAttribute("money", bl.getMoneyN());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("headImg", user.getHeadImage());
						model.addAttribute("dbagId", dbagId);
						/** 红包领取list*/
						List<BagLog> list = baglogService.findAllDivBags(bagId);
						List newList = new ArrayList();
						if(Collections3.isNotEmpty(list)){
							for(BagLog blog : list){
								if(!user.getId().equals(blog.getUserId()))//!blog.getUserId().equals(users.getId())
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
							model.addAttribute("schoolId", sbag.getId());
							model.addAttribute("openId", openId);
						}
						return "getDiySuccess";
					}
					/** 判断临时表是否存储未领取的红包 */
					if (tempService.findTempByUserId(openId, bagId)) {
						Temp ttemp = tempService.findTemp(openId, bagId);
						model.addAttribute("money", ttemp.getMoneyN());
						model.addAttribute("voucherId", ttemp.getVoucherId());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("userName", users.getNameV());
						model.addAttribute("img", users.getHeadImage());
					}else{
						/** 红包次数减1- */
						if(bag != null){
							if(bag.getNumN()==0){
								model.addAttribute("bagId", dbagId);
								model.addAttribute("openId", openId);
								model.addAttribute("qufen", qufen);
								return "wandan";
							}
							bag.setNumN(bag.getNumN()-1);
							bagService.update(bag);
						}
						if(b != null && b.getNumN() > 0){
							b.setNumN(b.getNumN()-1);
							bagService.update(b);
						}
						/** 大红包减1 */
						if(sbag != null && sbag.getNumN() > 0){
							sbag .setNumN(sbag.getNumN()-1);
							schoolbagService.update(sbag); 
						}
						/** 更改每天领取红包次数开始*/
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
						/** 获取兑换码   改变兑换码状态 */
						
						Voucher voucher = voucherService.bingoVoucher(sbag.getId());
						if(voucher == null){
							model.addAttribute("bagId", dbagId);
							model.addAttribute("openId", openId);
							model.addAttribute("qufen", qufen);
							return "wandan";
						}
						voucher.setStatusN(1);
						voucherService.updateVoucher(voucher);
						model.addAttribute("money", voucher.getMoneyN());
						model.addAttribute("voucherId", voucher.getId());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
						model.addAttribute("userName", users.getNameV());
						model.addAttribute("img", users.getHeadImage());
						model.addAttribute("dbagId", dbagId);
						model.addAttribute("headImg", user.getHeadImage());
						model.addAttribute("schoolId", sbag.getId());
						/** 红包日志表添加记录 */
						BagLog bagLog = new BagLog();
						bagLog.setBagId(bagId);
						if(StringUtils.isNotBlank(voucher.getMoneyN().toString())){
							bagLog.setMoneyN(Integer.parseInt(voucher.getMoneyN().toString()));
						}
						bagLog.setUserId(user.getId());
						Date dt=new Date(); 
					    SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						bagLog.setDateD(String.valueOf(System.currentTimeMillis()));
						bagLog.setVoucherId(voucher.getId());
						baglogService.save(bagLog);			
						
						/** 推送消息 */
						Bag bag1 = bagService.findById(bagId);
						SchoolBag sb = schoolbagService.findBySchoolBagId(bag1.getSchoolBagId());
						User uuu = userService.getById(bag.getUserId());
						Map<String,String> urlParaMap = new HashMap<String, String>();
						urlParaMap.put("(bagId)", bagId);
						urlParaMap.put("(dbagId)", dbagId);
						urlParaMap.put("(openId)", openId);
						wxApiUtils wxUtil = new wxApiUtils();
						wxUtil.sendMyBagFirstMsg(uuu.getOpenId(), user.getNameV(), sb.getHeadImage(), voucher.getMoneyN().toString(), urlParaMap);
						
						wxUtil.sendFriendBagMsg(openId, uuu.getNameV(), sb.getHeadImage(), voucher.getMoneyN().toString(), urlParaMap);
						
						model.addAttribute("hengimage", sb.getHeadImage());
						/** 红包领取list*/
						List<BagLog> list = baglogService.findAllDivBags(bagId);
						List newList = new ArrayList();
						if(Collections3.isNotEmpty(list)){
							for(BagLog blog : list){
								if(!user.getId().equals(blog.getUserId()))
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
				return "getDiySuccess";
		}else{
			/** 不是小红包   进入下面方法 */
			/** 判断是否是第一次进入    如果是第一次    进下面方法    进入红包打开页*/
				/** 如果不是第一次进入    进下面方法     直接领取红包成功 */
				/** 判断红包是否被领完 */
				if(baglogService.isFullBag(bagId)){
					model.addAttribute("bagId", bagId);
					model.addAttribute("openId", openId);
					model.addAttribute("qufen", qufen);
					return "wandan";
				}else{
					/** 判断临时表是否存储未领取的红包 */
					if (tempService.findTempByUserId(openId, bagId)) {
						Temp ttemp = tempService.findTemp(openId, bagId);
						model.addAttribute("money", ttemp.getMoneyN());
						model.addAttribute("bagId", bagId);
						model.addAttribute("openId", openId);
					}else{
						/** 红包次数减1- */
						if(bag != null){
							if(bag.getNumN()==0){
								model.addAttribute("bagId", bagId);
								model.addAttribute("openId", openId);
								model.addAttribute("qufen", qufen);
								return "wandan";
							}
							bag.setNumN(bag.getNumN() - 1);
							bagService.update(bag);
						}
						/** 如果是大红包的话再给学校红包表的次数减1 */
						SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
						if(schoolbag != null && schoolbag.getNumN() > 0){
							schoolbag .setNumN(schoolbag.getNumN()-1);
							schoolbagService.update(schoolbag); 
						}
						/** 获取兑换码,改变兑换状态 */
						Voucher voucher = voucherService.bingoVoucher(schoolbag.getId());
						if(voucher == null){
							if(StringUtils.isBlank(dbagId)){
								model.addAttribute("qufen", "1");
							}
							model.addAttribute("bagId", bagId);
							model.addAttribute("openId", openId);
							return "wandan";
						}
						voucher.setStatusN(1);
						voucherService.updateVoucher(voucher);
						Bag bag1 = new Bag();
						bag1.setUserId(user.getId());
						bag1.setSchoolBagId(bag.getSchoolBagId());
						bag1.setType(bag1.xiao_bag_type);
						bagService.save(bag1);
						model.addAttribute("money", voucher.getMoneyN());
						model.addAttribute("dbagId", bagId);
						model.addAttribute("bagId", bag1.getId());
						model.addAttribute("openId", openId); 
						model.addAttribute("schoolId", sbag.getId());
						/** 红包类型-----页面上显示的红包模板 */ 
						model.addAttribute("moban", schoolbag.getMoban());
						BagLog bagLog = new BagLog();
						bagLog.setBagId(bagId);
						if(StringUtils.isNotBlank(voucher.getMoneyN().toString())){
							bagLog.setMoneyN(Integer.parseInt(voucher.getMoneyN().toString()));
						}
						bagLog.setUserId(user.getId());
						Date dt=new Date();
						SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						bagLog.setDateD(String.valueOf(System.currentTimeMillis()));
						bagLog.setVoucherId(voucher.getId());
						baglogService.save(bagLog);
						/** 插入红包日志 */
						Temp temp = tempService.findTemp(user.getId(), bagId);
						if(temp!=null){
							tempService.delete(temp);
						}
						
						/** 推送消息 */
						Bag bag2 = bagService.findById(bagId);
						SchoolBag schools = schoolbagService.findBySchoolBagId(bag2.getSchoolBagId());
						User uu = userService.getById(bag2.getUserId());
						Map<String,String> urlParaMap = new HashMap<String, String>();
						urlParaMap.put("(openId)", openId);
						wxApiUtils w = new wxApiUtils();
						w.sendQiangMsg(openId, voucher.getMoneyN().toString(), urlParaMap, bagId, bag1.getId(), schools.getHeadImage());
						
						model.addAttribute("hengimage", schools.getHeadImage());
						
						/** 更改每天领取红包次数开始*/
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
					return "success";
				}
				return "successs";
			}
		}
	
	/**
	 * 分享成功返回的方法（红包打开页）
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@HDAdd
	@RequestMapping(value="Success")
	public String Success(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String bagId = request.getParameter("bagId");
		String openId = request.getParameter("openId");
		String money = request.getParameter("money");
		String voucherId = request.getParameter("voucherId");
		Bag bags = bagService.findById(bagId);
		User user = userService.findByOpenId(openId);
		/** 生成小红包 */
		Bag bag = new Bag();
		bag.setUserId(user.getId());
		bag.setSchoolBagId(bags.getSchoolBagId());
		bag.setType(bag.xiao_bag_type);
		bagService.save(bag);
		model.addAttribute("dbagId", bagId);
		model.addAttribute("bagId", bag.getId());
		model.addAttribute("openId", openId);
		model.addAttribute("money", money);
		/** 添加领取红包日志 */
		BagLog bagLog = new BagLog();
		bagLog.setBagId(bagId);
		if(StringUtils.isNotBlank(money)){
			bagLog.setMoneyN(Integer.parseInt(money));
		}
		bagLog.setUserId(user.getId());
		Date dt=new Date();
		SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bagLog.setDateD(String.valueOf(System.currentTimeMillis()));
		bagLog.setVoucherId(voucherId);
		baglogService.save(bagLog);
		/** 清空临时表中未领取的红包*/
		Temp temp = tempService.findTemp(user.getId(), bagId);
		if(temp!=null){
			tempService.delete(temp);
		}
		/** 更改每天领取红包次数开始*/
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
		/** 推送消息 */
		Bag bagg = bagService.findById(bagId);
		SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bagg.getSchoolBagId());
		Map<String,String> urlParaMap = new HashMap<String, String>();
		urlParaMap.put("(openId)", openId);
		wxApiUtils w = new wxApiUtils();
		w.sendQiangMsg(openId, money, urlParaMap, bagId, bag.getId(), schoolbag.getHeadImage());
		
		model.addAttribute("hengimage", schoolbag.getHeadImage());
		
		model.addAttribute("schoolId", schoolbag.getId());
		
		School school = schoolService.findById(bag.getSchoolBagId());
		if(school != null){
			hudongService.addSchoolMemShareNum(bagId);
		}
		return "success";
	}
	
	/**
	 * 根据html5获取用户当前的地理位置
	 * @param jingdu
	 * @param weidu
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping(value="city")
	@ResponseBody
	private Map<String, String> city(HttpServletRequest request, String jingdu, String weidu) throws ClientProtocolException, IOException{
		String openId = request.getParameter("openId");
		User user = userService.findByOpenId(openId);
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
		user.setCity(city.substring(0, city.length()-1));
		userService.update(user);
		School school = schoolService.findByCity(city.substring(0, city.length()-1));
		/** 判断城市是否参加活动 */
		if(school == null){
			map.put("city", "-1");
		}else{
			map.put("url", school.getUrlN());
		} 
		return map;
	}
	
	/**
	 * 领取完大红包生成小红包后分享后执行的方法
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@HDAdd
	@RequestMapping(value="xiaofenxiangsuccess")
	public String xiaofenxiangsuccess(HttpServletRequest request, HttpServletResponse response, Model model){
		String xbagId = request.getParameter("xbagId");
		String dbagId = request.getParameter("dbagId");
		String openId = request.getParameter("openId");
		Bag bag = bagService.findById(xbagId);
		if(bag != null){
			bag.setYesorno(1);
			bagService.update(bag);
		}
		if(dbagId != null){
			Bag bag1 = bagService.findById(dbagId);
			SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag1.getSchoolBagId());
			model.addAttribute("hengimage", schoolbag.getHeadImage());
		}
		model.addAttribute("openId", openId);
		model.addAttribute("bagId", dbagId);
		model.addAttribute("schoolId", dbagId);
		School school = schoolService.findById(bag.getSchoolBagId());
		if(school != null){
			hudongService.addSchoolMemShareNum(school.getId());
		}
		return "fenxiangSuccess";
	}
	
	/**
	 * 我的红包
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@HDAdd
	@RequestMapping(value="mybag")
	public String mybag(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String code = request.getParameter("code");
		String bagId = request.getParameter("bagId");
		String dbagId = request.getParameter("dbagId");
		String qufen = request.getParameter("qufen");
		if(StringUtils.isNotBlank(code)){
			String opId = wxApiUtils.getOpenId(code);
			if(StringUtils.isNotBlank(opId)){
				List<BagLog> list = baglogService.findMybags(opId,0,4);
				if(list.size() == 0){
					model.addAttribute("openId", opId);
					model.addAttribute("bagId", bagId);
					model.addAttribute("qufen", qufen);
					return "haimeilinghongbao";
				}else{
					List newList = new ArrayList();
					if(Collections3.isNotEmpty(list)){
						for(BagLog bagLog : list){
							Map map = new HashMap();
							Bag bag  = bagService.findById(bagLog.getBagId());
							SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
							School school = schoolService.findById(schoolbag.getSchoolId());
							map.put("getDate", bagLog.getDateD());
							map.put("money", bagLog.getMoneyN());	
							map.put("voucherId", bagLog.getVoucherId());
							map.put("bagName", schoolbag.getHeadImage());
//							User user = userService.getById(bag.getUserId());
//							map.put("userName", user.getNameV()); 
							map.put("schoolName", school.getNameV());
							map.put("bagId", bag.getId());
							newList.add(map);
						}
						model.addAttribute("bagId",bagId);
						model.addAttribute("list",newList);
						model.addAttribute("openId",opId);
						/** 判断用户从课间操点击我的红包时，user表里是否以后地址  开始*/
						User user = userService.findByOpenId(opId);
						School school1 = schoolService.findByCity(user.getCity());
						if(school1 != null){
							model.addAttribute("url", school1.getUrlN());
							model.addAttribute("schoolId", school1.getId());
						}
						/** 结束 */
						if(dbagId==null){
							model.addAttribute("bagIds", bagId);
						}else if(dbagId != null){
							model.addAttribute("bagIds", dbagId);
						}
						model.addAttribute("qufen", qufen);
					}
				}
			}
			return "mybags";
		}else{
			String opId = request.getParameter("openId");
			if(StringUtils.isNotBlank(opId)){
				List<BagLog> list = baglogService.findMybags(opId,0,4);
				if(list.size() == 0){
					model.addAttribute("openId", opId);
					model.addAttribute("bagId", bagId);
					model.addAttribute("qufen", qufen);
					return "haimeilinghongbao";
				}else{
					List newList = new ArrayList();
					if(Collections3.isNotEmpty(list)){
						for(BagLog bagLog : list){
							Map map = new HashMap();
							Bag bag  = bagService.findById(bagLog.getBagId());
							SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
							School school = schoolService.findById(schoolbag.getSchoolId());
							map.put("getDate", bagLog.getDateD());
							map.put("money", bagLog.getMoneyN());	
							map.put("voucherId", bagLog.getVoucherId());
							map.put("bagName", schoolbag.getHeadImage());
//							User user = userService.getById(bag.getUserId());
//							map.put("userName", user.getNameV());
							map.put("schoolName", school.getNameV());
							map.put("bagId", bag.getId());
							newList.add(map);
						}
						model.addAttribute("bagId",bagId);
						model.addAttribute("list",newList);
						model.addAttribute("openId",opId);
						/** 判断用户从课间操点击我的红包时，user表里是否以后地址  开始*/
						User user = userService.findByOpenId(opId);
						School school1 = schoolService.findByCity(user.getCity());
						if(school1 != null){
							model.addAttribute("schoolId", school1.getId());
						}
						if(dbagId==null){
							model.addAttribute("bagIds", bagId);
						}else if(dbagId != null){
							model.addAttribute("bagIds", dbagId);
						}
						model.addAttribute("qufen", qufen);
					}
				}
			}
			return "mybags";
		}
	}
	
	@RequestMapping(value="findBagNextPage")
	@ResponseBody
	public String findBagNextPage(HttpServletRequest request)
	{
		String openId = request.getParameter("openId");
		String pageNum = request.getParameter("pageNum");
		String bagIds = request.getParameter("bagIds");
		int qufen = Integer.parseInt(request.getParameter("qufen"));
		List<BagLog> list = baglogService.findMybags(openId,Integer.parseInt(pageNum), 4);
		if(list.size() == 0){
			return "1";
		}
		StringBuffer sb = new StringBuffer(200);
		String li = "<p><a href=\"#href#\">#bagsource##money#元</a></p>";
		if(Collections3.isNotEmpty(list))
		{
			if(qufen==1){
				String url = "/xdfgame/bag/bagDetail?voucherId=#voucherId#&bagType=#bagType#&bagId=#bagId#&openId=#openId#&bagIds=#bagIds#&school=#school#&qufen=#qufen#";
				for(BagLog bagLog : list)
				{
					 Bag bag  = bagService.findById(bagLog.getBagId());
					 SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
					 School school = schoolService.findById(schoolbag.getSchoolId());
					 User user = userService.getById(bag.getUserId());
					 String href = url.replace("#voucherId#", bagLog.getVoucherId()).replace("#bagType#", bagLog.getTypeN()==null?"0":bagLog.getTypeN().toString()).replace("#bagId#", bag.getId()).replace("#openId#", openId).replace("#bagIds#", bagIds).replace("#school#", school.getCityV()).replace("#qufen#", "1");
					 String bagsource = schoolbag.getHeadImage()+ ",来自" + school.getNameV() + "哒红包," ;
					 String money = bagLog.getMoneyN().toString();
					 String row = li.replace("#href#", href).replace("#bagsource#", bagsource).replace("#money#", money);
					 sb.append(row);
				}
			}else{
				String url = "/xdfgame/bag/bagDetail?voucherId=#voucherId#&bagType=#bagType#&bagId=#bagId#&openId=#openId#&school=#school#&bagIds=#bagIds#";
				for(BagLog bagLog : list)
				{
					 Bag bag  = bagService.findById(bagLog.getBagId());
					 SchoolBag schoolbag = schoolbagService.findBySchoolBagId(bag.getSchoolBagId());
					 School school = schoolService.findById(schoolbag.getSchoolId());
					 User user = userService.getById(bag.getUserId());
					 String href = url.replace("#voucherId#", bagLog.getVoucherId()).replace("#bagType#", bagLog.getTypeN()==null?"0":bagLog.getTypeN().toString()).replace("#bagId#", bag.getId()).replace("#openId#", openId).replace("#school#", school.getCityV()).replace("#bagIds#", bagIds);
					 String bagsource = schoolbag.getHeadImage()+ ",来自" + school.getNameV() + "哒红包," ;
					 String money = bagLog.getMoneyN().toString();
					 String row = li.replace("#href#", href).replace("#bagsource#", bagsource).replace("#money#", money);
					 sb.append(row);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 红包详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@HDAdd
	@RequestMapping(value="bagDetail")
	public String bagDetail(HttpServletRequest request,HttpServletResponse response, Model model){
		String voucherId = request.getParameter("voucherId");
		String bagType = request.getParameter("bagType");
		String openId = request.getParameter("openId");
		String bagId  = request.getParameter("bagId");
		String bagIds  = request.getParameter("bagIds");
		String qufen = request.getParameter("qufen");
		model.addAttribute("qufen", qufen);
		model.addAttribute("oName",request.getParameter("school"));
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
		model.addAttribute("schoolId", bagIds);
		return "mybagDetail";
	}
	
	/**
	 * 查询所有的城市
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@HDAdd
	@RequestMapping(value="allcity")
	public String alcity(HttpServletRequest request,HttpServletResponse response, Model model){
		String type = request.getParameter("type");
		List<School> list = schoolService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("type", type);
		return "allcity";
	}
	
	/**
	 * 查询所有的城市
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@HDAdd
	@RequestMapping(value="allcitys")
	public String alcitys(HttpServletRequest request,HttpServletResponse response, Model model) throws ClientProtocolException, IOException{
		String openId = request.getParameter("openId");
		String type = request.getParameter("type");
		model.addAttribute("type", type);
		User user = userService.findByOpenId(openId);
		if(user == null){
			userService.isSubscribe(openId);
			List<School> list = schoolService.findAll();
			model.addAttribute("list", list);
			return "allcity";
		}else{
			if(user.getCity() == null){
				List<School> list = schoolService.findAll();
				model.addAttribute("list", list);
				return "allcity";
			}else{
				School school = schoolService.findByCity(user.getCity());
				return null;
			}
		}
	}
	
	/**
	 * 获取学校红包的详情
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
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
	
	/**
	 * 好友领取到红包推送URL
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping ("pushBagInfo")
	public String pushBagInfo(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception{
		String code = request.getParameter("code");
		String openId = null;
		if (StringUtils.isNotBlank(code)) {
			openId = wxApiUtils.getOpenId(code);
		}else{
			return "error";
		}
		String bagId = request.getParameter("bagId");
		String dbagId = request.getParameter("dbagId");
		model.addAttribute("dbagId", dbagId);
		model.addAttribute("bagtype", "diy"); //红包类型
		if(StringUtils.isNotBlank(openId)&&StringUtils.isNotBlank(bagId)){
			User user = userService.findByOpenId(openId);
			Bag bag = bagService.findById(bagId);
			if(bag!=null){
				BagLog bagLog1 = baglogService.fingBagLog(user.getId(), bagId);
				List<BagLog> list = baglogService.findAllDivBags(bagId);
				List newList = new ArrayList();
				if(Collections3.isNotEmpty(list)){
					for(BagLog blog : list){
						if(!user.getId().equals(blog.getUserId()))
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
				if(bagLog1!=null){
					model.addAttribute("money",bagLog1.getMoneyN());
				}
				model.addAttribute("headImg",user.getHeadImage());
				model.addAttribute("openId",openId);
			}
			return "getDiySuccess";
		}
		return "error";
	}
	
	@RequestMapping(value="cishu")
	@ResponseBody
	public Map<String, String> cishu(HttpServletRequest request,HttpServletResponse response, Model model){
		Map<String, String> map = new HashMap<String, String>();
		String openId = request.getParameter("openId");
		Date dt3=new Date();
	    SimpleDateFormat matter3=new SimpleDateFormat("yyyy-MM-dd");
		User uuser = userService.getDateNum(matter3.format(dt3), openId);
		/** 判断当天是否领够5次 */
		if(uuser != null){
			Integer num = Integer.parseInt(Global.getConfig(WXConst.QIANG_EVERYNUM).toString());
			if(uuser.getShareNum() >= num){
				map.put("fanhui", "-1");
				return map;
			}
		}
		/** 更新红包个数 */
//		String bagId = request.getParameter("bagId");
//		String dbagId = request.getParameter("dbagId");
//		String qufen = request.getParameter("qufen");
//		System.out.println("----------------"+bagId);
//		Bag bag = bagService.findById(bagId);
//		Bag dbag = bagService.findById(dbagId);
//		SchoolBag schoolbag = schoolbagService.findById(bag.getSchoolBagId());
		/** 小红包 */
//		if(StringUtils.isNotBlank(dbagId)){
//			/** 当前小红包减一 */
//			bag.setNumN(bag.getNumN()-1);
//			bagService.update(bag);
//			/** 大红包减一 */
//			dbag.setNumN(dbag.getNumN()-1);
//			bagService.update(dbag);
//			/** 学校红包减一 */
//			schoolbag.setNumN(schoolbag.getNumN()-1);
//			schoolbagService.update(schoolbag);
//		}
//		/** 大红包 */
//		else{
//			/** 当前红包减一 */
//			bag.setNumN(bag.getNumN()-1);
//			bagService.update(bag);
//			/** 学校红包减一 */
//			schoolbag.setNumN(schoolbag.getNumN()-1);
//			schoolbagService.update(schoolbag);
//		}
		map.put("fanhui", "1");
		return map;
	}
	
	/**
	 * 
	 * @param schoolBagId
	 * 根据School_bag表id查Bag表，返回bag表id
	 * wujinyi
	 * 
	 */
	@RequestMapping(value="findBySchoolBagId")
	@ResponseBody
	public String findBySchoolBagId(String schoolBagId){
		try {
			Bag bag = bagService.findSchoolBagId(schoolBagId);
			return bag.getId().toString();
		} catch (Exception e) {
			return "";
		}
	}
	
	@RequestMapping(value="saveUrl")
	public String saveUrl(HttpServletRequest request, Model model){
		String openId = request.getParameter("openId");
		String bagId = request.getParameter("bagId");
		String dbagId = request.getParameter("dbagId");
		String urlN = SpringContextHolder.getValue(WXConst.XDF_KEJIANCAO).replace("(bagId)", bagId).replace("(dbagId)", dbagId);
		Url url = new Url();
		url.setUrlN(urlN);
		urlService.save(url);
		model.addAttribute("id", url.getId());
		model.addAttribute("type", 1);
		model.addAttribute("openId", openId);
		request.getSession().setAttribute("type", 1);
		return "redirect:/bag/getUrl";
	}
	
	@RequestMapping(value="getUrl")
	public String getUrl(String id, Model model, String openId, HttpServletRequest request){
		Url url = urlService.findById(id);
		model.addAttribute("url", url.getUrlN());
		model.addAttribute("type", request.getSession().getAttribute("type"));
		int number = url.getUrlN().indexOf("bagId=");
		int numbers = url.getUrlN().indexOf("dbagId=");
		model.addAttribute("bagId", url.getUrlN().substring(number+6, number+6+32));
		model.addAttribute("dbagId", url.getUrlN().substring(numbers+7, numbers+7+32));
		model.addAttribute("openId", openId);
		String aaa = SpringContextHolder.getValue(WXConst.XDF_TWOSHARE).replace("(bagId)", url.getUrlN().substring(numbers+7, numbers+7+32));
		model.addAttribute("bbb", aaa);
		return "twosuccess";
	}
	
	@RequestMapping(value="tiaozhuansuccess")
	public String tiaozhuansuccess(String bagId, HttpServletRequest request, Model model) throws Exception{
		String code = request.getParameter("code");
		String openId=wxApiUtils.getOpenId(code);
		model.addAttribute("openId", openId);
		model.addAttribute("bagId", bagId);
		return "fenxiangSuccess";
	}
}
