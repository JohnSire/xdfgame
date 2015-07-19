package com.huass.wxsdk.msg;

import com.google.common.collect.Maps;
import com.huass.wxsdk.util.JsonMapper;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Msg4TextClient
{
  private String touser;
  private String content;

  public String toJson()
  {
    Map<String,Object> map = Maps.newLinkedHashMap();
    map.put("touser", this.touser);
    map.put("msgtype", "text");
    Map<String,Object> subMap = Maps.newHashMap();
    subMap.put("content", this.content);
    map.put("text", subMap);
    return JsonMapper.getInstance().toJson(map);
  }

  public String getTouser()
  {
    return this.touser;
  }

  public void setTouser(String touser)
  {
    this.touser = touser;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }
}