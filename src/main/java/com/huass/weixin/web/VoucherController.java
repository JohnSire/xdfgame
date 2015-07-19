package com.huass.weixin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huass.weixin.service.BagService;

@RequestMapping(value="vou")
public class VoucherController {
	@Autowired
	private BagService bagService;
	
	@RequestMapping(value="list")
	public String voucher(Model model,String openId){
		openId = "odhRouLrppNMRgStS6FltNVCb-OA";
		List list = bagService.bag(openId);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		model.addAttribute("list",list);
		return "myvoucher";
		
	}
}
