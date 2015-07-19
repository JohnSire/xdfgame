package com.huass.weixin.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huass.common.web.BaseController;
import com.huass.weixin.entity.Bingo;
import com.huass.weixin.service.BingoService;

@Controller
@RequestMapping(value="bingo")
public class BingoController extends BaseController 
{
	
	@Autowired
	private BingoService bingoService;
	
	@RequestMapping(value="findBySchoolId")
	public String findBySchoolId(String schoolId,String userId,HttpServletRequest request,HttpServletResponse response,Model model){
		try {
			List<Bingo> bingos = bingoService.findBySchoolId(schoolId);
			model.addAttribute("bingos",bingos);
			model.addAttribute("userId",userId);
			model.addAttribute("schoolId",schoolId);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "schoolBag";
	}
	
}
