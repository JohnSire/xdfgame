package com.huass.wxsdk.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Msg4Image extends Msg
{
  private String picUrl;
  private String msgId;
  private String mediaId;

  public Msg4Image()
  {
    this.head = new Msg4Head();
    this.head.setMsgType("image");
  }

  public Msg4Image(Msg4Head head)
  {
    this.head = head;
  }

  public void write(Document document)
  {
    Element root = document.createElement("xml");
    this.head.write(root, document);
    Element imageElement = document.createElement("Image");
    Element mediaIdElement = document.createElement("MediaId");
    imageElement.appendChild(mediaIdElement);
    root.appendChild(imageElement);
    document.appendChild(root);
  }

  public void read(Document document)
  {
    this.picUrl = document.getElementsByTagName("PicUrl").item(0).getTextContent();
    this.mediaId = getElementContent(document, "MediaId");
    this.msgId = document.getElementsByTagName("MsgId").item(0).getTextContent();
  }

  public String getPicUrl()
  {
    return this.picUrl;
  }

  public void setPicUrl(String picUrl)
  {
    this.picUrl = picUrl;
  }

  public String getMsgId()
  {
    return this.msgId;
  }

  public void setMsgId(String msgId)
  {
    this.msgId = msgId;
  }

  public String getMediaId()
  {
    return this.mediaId;
  }

  public void setMediaId(String mediaId)
  {
    this.mediaId = mediaId;
  }
}