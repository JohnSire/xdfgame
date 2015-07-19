package com.huass.wxsdk.msg;

import java.util.Map;

import com.google.common.collect.Maps;
import com.huass.wxsdk.util.JsonMapper;

public class Msg4GroupText {
	private String group_id;
	private String content;
	
	public String toJson(){
		 Map<String,Object> map = Maps.newLinkedHashMap();
			 Map<String,Object> filterMap = Maps.newHashMap();
			 filterMap.put("is_to_all", false);
			 filterMap.put("group_id", this.group_id);
		     map.put("filter", filterMap);
		     
		     Map<String,Object> textMap = Maps.newHashMap();
		     textMap.put("content", this.content);
		     map.put("text", textMap);
		     map.put("msgtype", "text");
		    
		return JsonMapper.getInstance().toJson(map);
	}
	
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
