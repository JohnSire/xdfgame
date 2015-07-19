package com.huass.wxsdk.msg;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Msg4ImageText extends Msg
{
  private String articleCount;
  private List<Data4Item> items = new ArrayList(3);

  public Msg4ImageText()
  {
    this.head = new Msg4Head();
    this.head.setMsgType("news");
  }

  public void write(Document document)
  {
    Element root = document.createElement("xml");
    this.head.write(root, document);
    Element articleCountElement = document.createElement("ArticleCount");
    articleCountElement.setTextContent(this.articleCount);

    Element articlesElement = document.createElement("Articles");
    int size = Integer.parseInt(this.articleCount);
    for (int i = 0; i < size; ++i) {
      Data4Item currentItem = (Data4Item)this.items.get(i);
      Element itemElement = document.createElement("item");
      Element titleElement = document.createElement("Title");
      titleElement.setTextContent(currentItem.getTitle());
      Element descriptionElement = document.createElement("Description");
      descriptionElement.setTextContent(currentItem.getDescription());
      Element picUrlElement = document.createElement("PicUrl");
      picUrlElement.setTextContent(currentItem.getPicUrl());
      Element urlElement = document.createElement("Url");
      urlElement.setTextContent(currentItem.getUrl());
      itemElement.appendChild(titleElement);
      itemElement.appendChild(descriptionElement);
      itemElement.appendChild(picUrlElement);
      itemElement.appendChild(urlElement);

      articlesElement.appendChild(itemElement);
    }

    root.appendChild(articleCountElement);
    root.appendChild(articlesElement);

    document.appendChild(root);
  }

  public void read(Document document)
  {
  }

  public void addItem(Data4Item item)
  {
    this.items.add(item);
    reflushArticleCount();
  }

  public void removeItem(int index) {
    this.items.remove(index);
    reflushArticleCount();
  }

  private void reflushArticleCount()
  {
    this.articleCount = "" + this.items.size();
  }
}