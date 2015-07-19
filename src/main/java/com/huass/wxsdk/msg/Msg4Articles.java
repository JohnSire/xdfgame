package com.huass.wxsdk.msg;

public class Msg4Articles
{
  private String title;
  private String description;
  private String url;
  private String picurl;

  public Msg4Articles()
  {
  }

  public Msg4Articles(String title, String description, String url, String picurl)
  {
    this.title = title;
    this.description = description;
    this.url = url;
    this.picurl = picurl;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public String getDescription()
  {
    return this.description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getUrl()
  {
    return this.url;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public String getPicurl()
  {
    return this.picurl;
  }

  public void setPicurl(String picurl)
  {
    this.picurl = picurl;
  }
}