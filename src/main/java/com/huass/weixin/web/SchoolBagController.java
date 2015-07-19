package com.huass.weixin.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.huass.common.utils.DateUtils;
import com.huass.common.web.BaseController;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.SchoolBag;
import com.huass.weixin.service.BagService;
import com.huass.weixin.service.SchoolBagService;
import com.huass.weixin.utils.SchoolBagBingo;

@Controller
@RequestMapping(value="schoolBag")
public class SchoolBagController extends BaseController 
{
	
	@Autowired
	private SchoolBagService schoolBagService;
	@Autowired
	private BagService bagService;
	
	
	@RequestMapping(value="upload")
	public String upload(@RequestParam("fileupload") MultipartFile file,String guanming,String sum,SchoolBag schoolBag,HttpServletRequest request,HttpServletResponse response, Model model) throws IOException{
		
		String imgPath = null;
		System.out.println("模板："+schoolBag.getMoban());
		System.out.println("冠名："+guanming);
		System.out.println("总和："+sum);
		if(schoolBag.getMoban().equals("3")){
			 if (!file.isEmpty()) {
				 String image = null;   
				 try {  
		            	String name = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
		            	image = UUID.randomUUID().toString()+name;
		            	imgPath = request.getSession().getServletContext().getRealPath("/views/headImg")+"/"+image;
		                file.transferTo(new File(imgPath));
		            } catch (Exception e) { 
		            	return "error";
		            }  
		            schoolBag.setBgtype("/views/headImg/"+image);
		     }  
		}
		 try {
			 Map<Object,Object> map = request.getParameterMap();
			 schoolBag.setNumN(Integer.parseInt(sum));
			 schoolBag.setHeadImage(guanming);
			 schoolBag.setDateN(DateUtils.getDate("yyyy-MM-dd"));
			 String bagId = schoolBagService.save(schoolBag,map,Integer.parseInt(sum));
			 System.out.println("生成的红包Id:"+bagId);
			 model.addAttribute("bagId",bagId);
			 model.addAttribute("schoolbagId",schoolBag.getId());
			 return "redirect:/schoolBag/schoolBagOK";
		 }catch (Exception e) {
			 if(imgPath != null){
				 File f = new File(imgPath);
				 if(f.exists()){
					 f.delete();
				 }
			 }
			 return "error";
		 }
		
	}
	
	@RequestMapping(value="schoolBagOK")
	public String schoolBagOK(String bagId,String schoolbagId,Model model){
		
		SchoolBag schoolbag = schoolBagService.findById(schoolbagId);
		
		model.addAttribute("msgV",schoolbag.getMsgV());
		model.addAttribute("guanming",schoolbag.getHeadImage());
		model.addAttribute("image",schoolbag.getBgtype());
		model.addAttribute("moban",schoolbag.getMoban());
		
		model.addAttribute("bagId",bagId);
		return "hongbaoOK";
	}
	
	@RequestMapping(value="findAll")
	@ResponseBody
	public List<SchoolBagBingo> findBySchoolId(String page,String schoolId){
		
		List<SchoolBagBingo> lists = schoolBagService.findSchoolId(schoolId,Integer.parseInt(page),6);
		return lists;
	}
	
	
	@RequestMapping(value="showBag")
	public String showBag(String schoolId,String openId, String url,String userId, Model model){
		List<SchoolBagBingo> lists = schoolBagService.findSchoolId(schoolId,1,6);
		model.addAttribute("schoolId",schoolId);
		model.addAttribute("userId",userId);
		model.addAttribute("lists",lists);
		return "showBag";
	}
	
	@RequestMapping(value="findById")
	public String findById(String openId, String url, Model model){
		int cc = url.indexOf("bagId=");
		String bagId = url.substring(cc+6, cc+6+32);
		Bag bag = bagService.findById(bagId);
		SchoolBag schoolbag = schoolBagService.findBySchoolBagId(bag.getSchoolBagId());
		model.addAttribute("hengimage", schoolbag.getHeadImage());
		model.addAttribute("msg", schoolbag.getMsgV());
		model.addAttribute("beijing", schoolbag.getBgtype());
		model.addAttribute("bagId", bag.getId());
		model.addAttribute("openId",openId);
		return "home";
	}
	
}
