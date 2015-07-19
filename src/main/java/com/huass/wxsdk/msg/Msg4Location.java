package com.huass.wxsdk.msg;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Msg4Location extends Msg
{
  private String location_X;
  private String location_Y;
  private String scale;
  private String label;
  private String msgId;

  public Msg4Location()
  {
    this.head = new Msg4Head();
    this.head.setMsgType("location");
  }

  public Msg4Location(Msg4Head head)
  {
    this.head = head;
  }

  public void write(Document document)
  {
  }

  public void read(Document document)
  {
    this.location_X = document.getElementsByTagName("Location_X").item(0).getTextContent();
    this.location_Y = document.getElementsByTagName("Location_Y").item(0).getTextContent();
    this.scale = document.getElementsByTagName("Scale").item(0).getTextContent();
    this.label = document.getElementsByTagName("Label").item(0).getTextContent();
    this.msgId = document.getElementsByTagName("MsgId").item(0).getTextContent();
  }

  public String getLocation_X()
  {
    return this.location_X; }

  public void setLocation_X(String location_X) {
    this.location_X = location_X; }

  public String getLocation_Y() {
    return this.location_Y; }

  public void setLocation_Y(String location_Y) {
    this.location_Y = location_Y; }

  public String getScale() {
    return this.scale; }

  public void setScale(String scale) {
    this.scale = scale; }

  public String getLabel() {
    return this.label; }

  public void setLabel(String label) {
    this.label = label; }

  public String getMsgId() {
    return this.msgId; }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }
}