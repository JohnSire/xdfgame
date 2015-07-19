package com.huass.wxsdk.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Msg4Voice extends Msg
{
  private String mediaId;
  private String format;
  private String recognition;
  private String msgId;

  public Msg4Voice()
  {
    this.head = new Msg4Head();
    this.head.setMsgType("voice");
  }

  public Msg4Voice(Msg4Head head)
  {
    this.head = head;
  }

  public void write(Document document)
  {
    Element root = document.createElement("xml");
    this.head.write(root, document);
    Element voiceElement = document.createElement("Voice");
    Element mediaIdElement = document.createElement("MediaId");
    voiceElement.appendChild(mediaIdElement);
    root.appendChild(voiceElement);
    document.appendChild(root);
  }

  public void read(Document document)
  {
    this.mediaId = getElementContent(document, "MediaId");
    this.format = getElementContent(document, "Format");
    this.recognition = getElementContent(document, "Recognition");
    this.msgId = getElementContent(document, "MsgId");
  }

  public String getMediaId()
  {
    return this.mediaId;
  }

  public void setMediaId(String mediaId)
  {
    this.mediaId = mediaId;
  }

  public String getFormat()
  {
    return this.format;
  }

  public void setFormat(String format)
  {
    this.format = format;
  }

  public String getRecognition()
  {
    return this.recognition;
  }

  public void setRecognition(String recognition)
  {
    this.recognition = recognition;
  }

  public String getMsgId()
  {
    return this.msgId;
  }

  public void setMsgId(String msgId)
  {
    this.msgId = msgId;
  }
}