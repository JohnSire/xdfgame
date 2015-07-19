package com.huass.weixin.web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.huass.common.utils.BaseImageUtil;
import com.huass.common.utils.Collections3;
import com.huass.common.utils.DateUtils;
import com.huass.common.utils.StringUtils;
import com.huass.common.utils.UploadUtil;
import com.huass.common.utils.wxApiUtils;
import com.huass.common.web.BaseController;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.BagLog;
import com.huass.weixin.entity.Temp;
import com.huass.weixin.entity.User;
import com.huass.weixin.entity.Voucher;
import com.huass.weixin.service.BagLogService;
import com.huass.weixin.service.BagService;
import com.huass.weixin.service.TempService;
import com.huass.weixin.service.UserService;
import com.huass.weixin.service.VoucherService;
import com.huass.weixin.utils.HDAdd;

@Controller
@RequestMapping(value="diyBag")
public class DiyBagController extends BaseController {
	@Autowired
	private BagService bagService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BagLogService BagLogService;
	
	@Autowired
	private VoucherService voucherService;
	
	@Autowired
	private TempService tempService;
	
	
	@HDAdd
	@RequestMapping(value="dbIndex")
	public String toDiyBagIndex(HttpServletRequest request,	HttpServletResponse response, Model model){
		String code = request.getParameter("code");
		String oo = request.getParameter("openid");
		if(StringUtils.isNotBlank(code))
		{
			try {
				String openId = wxApiUtils.getOpenId(code);
				if(StringUtils.isNotBlank(openId)){
					if(!userService.isSubscribe(openId)){
						return "guanzhu";
					}
					User user = userService.findByOpenId(openId);
					model.addAttribute("openId", openId);
					model.addAttribute("headImage", user.getHeadImage());
					model.addAttribute("userName", StringUtils.dellastdot(user.getNameV()));
				}else{
					return "error";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			User user = userService.findByOpenId(oo);
			model.addAttribute("openId", oo);
			model.addAttribute("headImage", user.getHeadImage());
			model.addAttribute("userName", StringUtils.dellastdot(user.getNameV()));
		}
		return "diyBagIndex";
	}
}
