package com.huass.wxsdk.msg;

import java.util.List;
import java.util.Map;

public class Msg4ImageTextClient extends Msg4Client
{
  public Msg4ImageTextClient(List<Msg4Articles> artList)
  {
    this.map.put("articles", artList);
    this.msgtype = "news";
  }
}