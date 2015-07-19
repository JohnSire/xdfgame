package com.huass.wxsdk.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Msg
{
  public static final String MSG_TYPE_TEXT = "text";
  public static final String MSG_TYPE_IMAGE = "image";
  public static final String MSG_TYPE_MUSIC = "music";
  public static final String MSG_TYPE_LOCATION = "location";
  public static final String MSG_TYPE_LINK = "link";
  public static final String MSG_TYPE_IMAGE_TEXT = "news";
  public static final String MSG_TYPE_EVENT = "event";
  public static final String MSG_TYPE_VOICE = "voice";
  public static final String MSG_TYPE_VIDEO = "video";
  public static final String MSG_TYPE_CUSTOMER = "text";
  protected Msg4Head head;

  public abstract void write(Document paramDocument);

  public abstract void read(Document paramDocument);

  protected String getElementContent(Document document, String element)
  {
    if (document.getElementsByTagName(element).getLength() > 0)
      return document.getElementsByTagName(element).item(0).getTextContent();

    return null;
  }

  public Msg4Head getHead()
  {
    return this.head;
  }

  public void setHead(Msg4Head head) {
    this.head = head;
  }

  public String getToUserName() {
    return this.head.getToUserName();
  }

  public void setToUserName(String toUserName) {
    this.head.setToUserName(toUserName);
  }

  public String getFromUserName() {
    return this.head.getFromUserName();
  }

  public void setFromUserName(String fromUserName) {
    this.head.setFromUserName(fromUserName);
  }

  public String getCreateTime() {
    return this.head.getCreateTime();
  }

  public void setCreateTime(String createTime) {
    this.head.setCreateTime(createTime);
  }
}