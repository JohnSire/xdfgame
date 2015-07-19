package com.huass.weixin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.huass.common.config.Global;
import com.huass.common.utils.StringUtils;
import com.huass.weixin.service.BagService;
import com.huass.weixin.service.HuDongService;
import com.huass.weixin.service.SchoolBingoService;
import com.huass.wxsdk.httpclient.wxUtils;
/**
 * 定时任务类
 * @author iibm
 *
 */
@Component
public class wxJob
{
	@Autowired
	private HuDongService hdService;
	
	@Autowired
	private BagService bagService;
	
	@Autowired
	private SchoolBingoService sbService;
	@Scheduled(fixedDelay = 5400000L, initialDelay = 30000L)
	public void getAccessToken()
	{
		try
		{
			String value = wxUtils.getInstance().getAccessToken(
					Global.getConfig("appid"), Global.getConfig("appsecret"));
			if (StringUtils.isNotEmpty(value))
			{
				GlobalToken.getInstance().saveAccessToken(value);
				System.out.println("refresh accesstoken..");
			} else
			{
				value = wxUtils.getInstance().getAccessToken(
						Global.getConfig("appid"),
						Global.getConfig("appsecret"));
				if (StringUtils.isNotEmpty(value))
				{
					GlobalToken.getInstance().saveAccessToken(value);
					System.out.println("refresh22 accesstoken..");
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedDelay = 300000L, initialDelay = 30000L)
	public void updateHDNum()
	{
		System.out.println("update HDNum:" + System.currentTimeMillis());
		hdService.addHuDongNum();
		WXConst.HD_NUM = 0L;
		WXConst.SHARE_NUM = 0L;
	}
	
	@Scheduled(cron="0 0 3 * * ?")
	public void delLittleBag()
	{
		System.out.println("delete temp small bag...:" + System.currentTimeMillis());
		//bagService.delLittleBag();
	}
	
	@Scheduled(fixedDelay = 1800000L, initialDelay = 90000L)
	public void gcBingoCache()
	{
		System.out.println("clear cache:" + System.currentTimeMillis());
		sbService.gcBingCache();
	}
	
}

