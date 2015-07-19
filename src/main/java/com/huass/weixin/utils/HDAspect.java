package com.huass.weixin.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HDAspect
{
	@Before("within(com.huass..*) && @annotation(rl)")
	public void addHDNums(JoinPoint jp, HDAdd rl)
	{
		WXConst.HD_NUM++;
	}
}
