package com.huass.weixin.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huass.common.utils.StringUtils;
import com.huass.common.web.BaseController;
import com.huass.weixin.entity.School;
import com.huass.weixin.entity.User;
import com.huass.weixin.service.SchoolService;
import com.huass.weixin.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController extends BaseController 
{
	@Autowired	
	private UserService userService;
	@Autowired
	private SchoolService schoolService;
	
	
	@RequestMapping(value="secret")
	public String bySecret(HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			String openId = request.getParameter("openId");
			userService.isSubscribe(openId);
			User user = userService.findByOpenId(openId);
			if(StringUtils.isNotBlank(user.getSchoolId())){
				School school = schoolService.findById(user.getSchoolId());
				model.addAttribute("schoolId",school.getId());
				model.addAttribute("userId",user.getId());
				return "diyShow";
			}else{
				model.addAttribute("userId",user.getId());
				return "head";
			}
		} catch (Exception e) {
			return "error";
		}
		
	}
	
	
	
	@RequestMapping(value="updateBySecret")
	@ResponseBody
	public Map<String,String> updateBySecret(String secret,String userId,Model model,HttpServletRequest request)
	{
		Map<String,String> map = new HashMap<String,String>();
		try {
			School school = schoolService.findBySecret(secret);
			if(school == null){
				map.put("error","1");
				return map;
			}else{
				userService.updateBySchoolId(school.getId(),userId);
				map.put("schoolId", school.getId());
				map.put("userId", userId);
				return map;
			}
		} catch (Exception e) {
			map.put("error","error");
			return map;
		}
	}
	
}

