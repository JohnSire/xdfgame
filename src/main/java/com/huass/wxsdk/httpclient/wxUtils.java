package com.huass.wxsdk.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.PrintStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class wxUtils
{
  private DefaultHttpClient httpclient;

  public wxUtils()
  {
    this.httpclient = new DefaultHttpClient();
    this.httpclient = ((DefaultHttpClient)HttpClientManager.getSSLInstance(this.httpclient));
  }

  public DefaultHttpClient getHttpclient()
  {
    return this.httpclient;
  }

  public static wxUtils getInstance()
  {
    return new wxUtils();
  }

  public String createMenu(String params, String accessToken)
    throws Exception
  {
    HttpPost httpost = HttpClientManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken);

    httpost.setEntity(new StringEntity(params, "UTF-8"));
    HttpResponse response = this.httpclient.execute(httpost);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    System.out.println(jsonStr);
    JSONObject object = JSON.parseObject(jsonStr);
    return object.getString("errmsg");
  }

  public String getAccessToken(String appid, String secret)
    throws Exception
  {
    System.out.println("BegingTime:" + System.currentTimeMillis() + ". get toekn....");
    HttpGet get = HttpClientManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret);

    HttpResponse response = this.httpclient.execute(get);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    JSONObject object = JSON.parseObject(jsonStr);
    return object.getString("access_token");
  }

  public String getMenuInfo(String accessToken)
    throws Exception
  {
    HttpGet get = HttpClientManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);

    HttpResponse response = this.httpclient.execute(get);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    return jsonStr;
  }

  public String delMenus(String accessToken)
    throws Exception
  {
    HttpGet get = HttpClientManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);

    HttpResponse response = this.httpclient.execute(get);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    JSONObject object = JSON.parseObject(jsonStr);
    return object.getString("errmsg");
  }

  public String getUserInfo(String accessToken, String openId)
    throws Exception
  {
    String userURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    String customURL = userURL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
    HttpGet get = HttpClientManager.getGetMethod(customURL);
    HttpResponse response = this.httpclient.execute(get);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    return jsonStr;
  }

  public static String getOpenId(String accessTokenUrl)
    throws Exception
  {
    JSONObject jsonObject = HttpUtil.getInstance().getURL(accessTokenUrl);
    return jsonObject.getString("openid");
  }
  /**
   * 获取网络授权的用户信息
   * @param access_token
   * @param openId
   * @return
   * @throws Exception
   */
  public static String getUserInfoByWeb(String access_token, String openId)
    throws Exception
  {
    String userURL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    userURL = userURL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
    JSONObject userInfo = HttpUtil.getInstance().getURL(userURL);
    return userInfo.toJSONString();
  }

  public static String getTokenAndOpenId(String accessTokenUrl)
    throws Exception
  {
    JSONObject jsonObject = HttpUtil.getInstance().getURL(accessTokenUrl);
    return jsonObject.toJSONString();
  }
  /**
   * 获取用户列表
   * @param accessToken
   * @param nextOpenId
   * @return
   * @throws Exception
   */
  public String getSubscriber(String accessToken, String nextOpenId)
    throws Exception
  {
    String userURL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;
    if (nextOpenId != null)
    {
      userURL = userURL + "&next_openid=" + nextOpenId;
    }
    HttpGet get = HttpClientManager.getGetMethod(userURL);
    HttpResponse response = this.httpclient.execute(get);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    return jsonStr;
  }

  public void sendClientMsg(String accessToken, String msg)
    throws Exception
  {
    HttpPost httpost = HttpClientManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken);

    StringEntity entity = new StringEntity(msg, ContentType.APPLICATION_JSON);
    httpost.setEntity(entity);
    HttpResponse response = this.httpclient.execute(httpost);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    System.out.println(jsonStr);
  }

  public String uploadMedia(String accessToken, String type, String path)
    throws Exception
  {
    String url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=" + accessToken + "&type=" + type;
    HttpPost httpost = new HttpPost(url);
    FileBody fileBody = new FileBody(new File(path));
    MultipartEntity entity = new MultipartEntity();
    entity.addPart("file", fileBody);
    httpost.setEntity(entity);
    HttpResponse response = this.httpclient.execute(httpost);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    JSONObject jsonObject = JSON.parseObject(jsonStr);
    String media_id = jsonObject.getString("media_id");
    return media_id;
  }
  /**
   * 获取用户分组
   * @param accessToken
   * @return
   * @throws Exception
   */
  public String getUserGroups(String accessToken) throws Exception{
	  HttpGet get = HttpClientManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/groups/get?access_token=" + accessToken);
	  HttpResponse response = this.httpclient.execute(get);
	  String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
	  return jsonStr;
  }
}