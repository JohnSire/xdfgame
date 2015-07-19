package com.huass.wxsdk.msg;

import com.huass.wxsdk.util.JsonMapper;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Msg4ImageClient extends Msg4Client
{
  public Msg4ImageClient(String image)
  {
    this.map.put("media_id", image);
  }

  public static void main(String[] args)
  {
    Map map = new LinkedHashMap();
    map.put("touser", "jalsjdflajsdlkfjasdfj");
    map.put("msgtype", "news");
    Msg4Articles a1 = new Msg4Articles("图文1", "图文描述1", "http://www.baidu.com", "http://www.baidu.com/1111.png");
    Msg4Articles a2 = new Msg4Articles("图文2", "图文描述2", "http://www.baidu.com2", "http://www.baidu.com/2222.png");
    Map listMap = new LinkedHashMap();
    List alist = new ArrayList();
    alist.add(a1);
    alist.add(a2);
    listMap.put("articles", alist);
    map.put("news", listMap);
    String str = JsonMapper.getInstance().toJson(map);
    System.out.println(str);
  }
}