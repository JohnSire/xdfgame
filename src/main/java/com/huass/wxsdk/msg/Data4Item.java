package com.huass.wxsdk.msg;

public class Data4Item
{
  private String title;
  private String description;
  private String picUrl;
  private String url;

  public Data4Item()
  {
  }

  public Data4Item(String title, String description, String picUrl, String url)
  {
    this.title = title;
    this.description = description;
    this.picUrl = picUrl;
    this.url = url;
  }

  public String getTitle()
  {
    return this.title; }

  public void setTitle(String title) {
    this.title = title; }

  public String getDescription() {
    return this.description; }

  public void setDescription(String description) {
    this.description = description; }

  public String getPicUrl() {
    return this.picUrl; }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl; }

  public String getUrl() {
    return this.url; }

  public void setUrl(String url) {
    this.url = url;
  }
}