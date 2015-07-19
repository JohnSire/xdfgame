package com.huass.wxsdk.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Msg4Link extends Msg
{
  private String title;
  private String description;
  private String url;
  private String msgId;

  public Msg4Link()
  {
    this.head = new Msg4Head();
    this.head.setMsgType("link");
  }

  public Msg4Link(Msg4Head head)
  {
    this.head = head;
  }

  public void write(Document document)
  {
  }

  public void read(Document document) {
    this.title = document.getElementsByTagName("Title").item(0).getTextContent();
    this.description = document.getElementsByTagName("Description").item(0).getTextContent();
    this.url = document.getElementsByTagName("Url").item(0).getTextContent();
    this.msgId = document.getElementsByTagName("MsgId").item(0).getTextContent();
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

  public String getUrl() {
    return this.url; }

  public void setUrl(String url) {
    this.url = url; }

  public String getMsgId() {
    return this.msgId; }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }
}