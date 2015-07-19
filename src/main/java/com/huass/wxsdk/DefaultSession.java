package com.huass.wxsdk;

import com.huass.wxsdk.msg.Msg4Event;
import com.huass.wxsdk.msg.Msg4Image;
import com.huass.wxsdk.msg.Msg4Link;
import com.huass.wxsdk.msg.Msg4Location;
import com.huass.wxsdk.msg.Msg4Text;
import com.huass.wxsdk.msg.Msg4Video;
import com.huass.wxsdk.msg.Msg4Voice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultSession extends Session
{
  private List<HandleMessageListener> listeners = new ArrayList(3);

  public static DefaultSession newInstance()
  {
    return new DefaultSession();
  }

  public void addOnHandleMessageListener(HandleMessageListener handleMassge)
  {
    this.listeners.add(handleMassge);
  }

  public void removeOnHandleMessageListener(HandleMessageListener handleMassge)
  {
    this.listeners.remove(handleMassge);
  }

  public void onTextMsg(Msg4Text msg)
  {
    for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { HandleMessageListener currentListener = (HandleMessageListener)i$.next();
      currentListener.onTextMsg(msg);
    }
  }

  public void onImageMsg(Msg4Image msg)
  {
    for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { HandleMessageListener currentListener = (HandleMessageListener)i$.next();
      currentListener.onImageMsg(msg);
    }
  }

  public void onEventMsg(Msg4Event msg)
  {
    for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { HandleMessageListener currentListener = (HandleMessageListener)i$.next();
      currentListener.onEventMsg(msg);
    }
  }

  public void onLinkMsg(Msg4Link msg)
  {
    for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { HandleMessageListener currentListener = (HandleMessageListener)i$.next();
      currentListener.onLinkMsg(msg);
    }
  }

  public void onLocationMsg(Msg4Location msg)
  {
    for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { HandleMessageListener currentListener = (HandleMessageListener)i$.next();
      currentListener.onLocationMsg(msg);
    }
  }

  public void onErrorMsg(int errorCode)
  {
    for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { HandleMessageListener currentListener = (HandleMessageListener)i$.next();
      currentListener.onErrorMsg(errorCode);
    }
  }

  public void onVoiceMsg(Msg4Voice msg)
  {
    for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { HandleMessageListener currentListener = (HandleMessageListener)i$.next();
      currentListener.onVoiceMsg(msg);
    }
  }

  public void onVideoMsg(Msg4Video msg)
  {
    for (Iterator i$ = this.listeners.iterator(); i$.hasNext(); ) { HandleMessageListener currentListener = (HandleMessageListener)i$.next();
      currentListener.onVideoMsg(msg);
    }
  }
}