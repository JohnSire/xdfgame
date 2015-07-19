package com.huass.wxsdk.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huass.wxsdk.msg.Msg4GroupText;
import com.huass.wxsdk.msg.Msg4TextClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil
{
  private DefaultHttpClient httpclient;

  public HttpUtil()
  {
    this.httpclient = new DefaultHttpClient();
    this.httpclient = ((DefaultHttpClient)HttpClientManager.getSSLInstance(this.httpclient));
  }

  public static HttpUtil getInstance()
  {
    HttpUtil hu = new HttpUtil();
    return hu;
  }

  public JSONObject getURL(String url)
    throws Exception
  {
    HttpGet get = HttpClientManager.getGetMethod(url);
    HttpResponse response = this.httpclient.execute(get);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    JSONObject object = JSON.parseObject(jsonStr);
    return object;
  }

  public void sendClientMsg(String accessToken, Msg4TextClient client)
    throws Exception
  {
    String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
    String realUrl = url.replace("ACCESS_TOKEN", accessToken);
    HttpPost post = HttpClientManager.getPostMethod(realUrl);
    StringEntity entity = new StringEntity(JSONObject.toJSONString(client));
    post.setEntity(entity);
    this.httpclient.execute(post);
  }
  
  public void sendGroupMsg(String accessToken, Msg4GroupText client) throws Exception{
	  String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	  String realUrl = url.replace("ACCESS_TOKEN", accessToken);
	  HttpPost post = HttpClientManager.getPostMethod(realUrl);
	  StringEntity entity = new StringEntity(JSONObject.toJSONString(client.toJson()));
	  post.setEntity(entity);
	  this.httpclient.execute(post);
  }
}