package com.huass.wxsdk.msg;

import com.huass.wxsdk.util.JsonMapper;
import java.util.LinkedHashMap;
import java.util.Map;

public class Msg4Client
{
  protected String touser;
  protected String msgtype;
  protected Map<String, Object> map;

  public Msg4Client()
  {
    this.map = new LinkedHashMap();
  }

  public String tojson() {
    Map jsonmap = new LinkedHashMap();
    jsonmap.put("touser", this.touser);
    jsonmap.put("msgtype", this.msgtype);
    jsonmap.put(this.msgtype, this.map);
    return JsonMapper.getInstance().toJson(jsonmap);
  }

  public String getTouser()
  {
    return this.touser;
  }

  public void setTouser(String touser)
  {
    this.touser = touser;
  }

  public String getMsgtype()
  {
    return this.msgtype;
  }

  public void setMsgtype(String msgtype)
  {
    this.msgtype = msgtype;
  }
}