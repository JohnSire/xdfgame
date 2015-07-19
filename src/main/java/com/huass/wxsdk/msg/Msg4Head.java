package com.huass.wxsdk.msg;

import com.huass.wxsdk.Session;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Msg4Head
{
  private String toUserName;
  private String fromUserName;
  private String createTime;
  private String msgType;

  public Msg4Head()
  {
    this.createTime = Session.DATE_FORMAT.format(new Date());
  }

  public void write(Element root, Document document) {
    Element toUserNameElement = document.createElement("ToUserName");

    toUserNameElement.setTextContent(this.toUserName);
    Element fromUserNameElement = document.createElement("FromUserName");

    fromUserNameElement.setTextContent(this.fromUserName);
    Element createTimeElement = document.createElement("CreateTime");

    createTimeElement.setTextContent(this.createTime);
    Element msgTypeElement = document.createElement("MsgType");

    msgTypeElement.setTextContent(this.msgType);

    root.appendChild(toUserNameElement);
    root.appendChild(fromUserNameElement);
    root.appendChild(createTimeElement);
    root.appendChild(msgTypeElement);
  }

  public void read(Document document) {
    this.toUserName = document.getElementsByTagName("ToUserName").item(0).getTextContent();

    this.fromUserName = document.getElementsByTagName("FromUserName").item(0).getTextContent();

    this.createTime = document.getElementsByTagName("CreateTime").item(0).getTextContent();

    this.msgType = document.getElementsByTagName("MsgType").item(0).getTextContent();
  }

  public String getToUserName()
  {
    return this.toUserName;
  }

  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  public String getFromUserName() {
    return this.fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  public String getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getMsgType() {
    return this.msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }
}