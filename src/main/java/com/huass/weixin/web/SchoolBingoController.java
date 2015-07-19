package com.huass.weixin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huass.common.web.BaseController;
import com.huass.weixin.service.SchoolBingoService;

@Controller
@RequestMapping(value="schoolBingo")
public class SchoolBingoController extends BaseController 
{
	
	@Autowired
	private SchoolBingoService schoolBingoService;
	
	

}
