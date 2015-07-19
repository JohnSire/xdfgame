package com.huass.wxsdk;

import com.huass.wxsdk.msg.Msg4Event;
import com.huass.wxsdk.msg.Msg4Image;
import com.huass.wxsdk.msg.Msg4Link;
import com.huass.wxsdk.msg.Msg4Location;
import com.huass.wxsdk.msg.Msg4Text;
import com.huass.wxsdk.msg.Msg4Video;
import com.huass.wxsdk.msg.Msg4Voice;

public class HandleMessageAdapter
  implements HandleMessageListener
{
  public void onTextMsg(Msg4Text msg)
  {
  }

  public void onImageMsg(Msg4Image msg)
  {
  }

  public void onEventMsg(Msg4Event msg)
  {
  }

  public void onLinkMsg(Msg4Link msg)
  {
  }

  public void onLocationMsg(Msg4Location msg)
  {
  }

  public void onErrorMsg(int errorCode)
  {
  }

  public void onVoiceMsg(Msg4Voice msg)
  {
  }

  public void onVideoMsg(Msg4Video msg)
  {
  }
}