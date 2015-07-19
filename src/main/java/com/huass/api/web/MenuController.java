package com.huass.api.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huass.common.config.Global;
import com.huass.common.utils.Encodes;
import com.huass.common.web.BaseController;
import com.huass.weixin.utils.WXConst;
import com.huass.wxsdk.httpclient.wxUtils;

@Controller
@RequestMapping(value = "${adminPath}/api/menu")
public class MenuController extends BaseController{
	
	@RequestMapping(value="index")
	public String index(String body,String access_token ,Model model){
		access_token = Global.getConfig(WXConst.GLOBAL_ACCESS_TOKEN);
		model.addAttribute("access_token", access_token);
		try {
			model.addAttribute("menus",wxUtils.getInstance().getMenuInfo(access_token));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("menus","");
		}
		return "modules/api/menus";
	}
	@RequestMapping(value="create")
	public String create(String body,String access_token ,Model model,RedirectAttributes redirectAttributes){
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(body);
		body = m.replaceAll("").replace("&quot;", "'");
		body = body.replace("'", "\"");
		try {
			String result = wxUtils.getInstance().createMenu(Encodes.unescapeHtml(body),
					access_token);
			addMessage(redirectAttributes, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addMessage(redirectAttributes, "创建菜单失败："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/api/menu/index/?repage";
	}
	
}
