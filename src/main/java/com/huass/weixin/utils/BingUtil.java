package com.huass.weixin.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.huass.common.utils.Collections3;
import com.huass.common.utils.SpringContextHolder;
import com.huass.common.utils.StringUtils;
import com.huass.weixin.entity.Bingo;
import com.huass.weixin.service.SchoolBingoService;
/**
 * 概率工具类
 * @author iibm
 *
 */
public class BingUtil { 

	
	public boolean isBingo(double percent)
	{
		boolean flag = false;
		Random random = new Random();
		
		int percentInt = (int) (percent * 10);
		int sub = 10 - percentInt;
		Set<String> subSet = new LinkedHashSet<String>();
		int j = 0;
		int frand = new Random().nextInt(10);
		List<String> percentList = new LinkedList<String>();
		while(j < sub)
		{
			Integer ranint = random.nextInt(10);
			if(frand != ranint)
			{
				subSet.add(ranint + "");
			}
			j = subSet.size();
		}
		percentList.addAll(subSet);
		for(int i = 0; i < percentInt; i++)
		{
			percentList.add("");
		}
		for(int i = 0; i < percentInt; i++)
		{
			Integer ranint = random.nextInt(9);
			percentList.add(ranint, frand + "");
		}
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < percentList.size(); i++)
		{
			if(StringUtils.isNotBlank(percentList.get(i)))
			{
				list.add(percentList.get(i));
			}
		}
		int bingo = random.nextInt(9);
		String newbingo = list.get(bingo);
		if(newbingo.equals(frand+""))
		{
			flag = true;
		}
		return flag;
	}
	
	
	public Integer bingMoneyByBagId(String bagId)
	{
		Integer money = null;
		Bingo.BING_TIMEOUT.put(bagId, System.currentTimeMillis());
		
		List<Integer> bings = Bingo.BINGO_MAP.get(bagId);
		if(Collections3.isNotEmpty(bings))
		{
			money = bingMoneyBag(bings);
		}
		else
		{
			if(bings != null && bings.size() == 0)
			{
				money = -1;
			}
			else
			{
				SchoolBingoService sbService = SpringContextHolder.getBean(SchoolBingoService.class);
				bings = sbService.addBingoByBagId(bagId);
				if(Collections3.isNotEmpty(bings))
				{
					money = bingMoneyBag(bings);
				}
				else
				{
					money = -1;
				}
			}
		}
		return money;
	}
	
	
	public Integer bingNoMoney(String bagId, List<Integer> moneyN)
	{
		List<Integer> bings = Bingo.BINGO_MAP.get(bagId);
		List<Integer> newbings = new ArrayList<Integer>(bings);
		newbings.removeAll(moneyN);
		return bingMoneyBag(newbings);
	}
	
	
	private Integer bingMoneyBag(List<Integer> bings)
	{
		Integer money = null;
		if(bings.size() == 0)
		{
			money = -1;
		}
		else
		{
			Random ran = new Random();
			int ranInt = ran.nextInt(bings.size());
			Collections.shuffle(bings);
			money = bings.get(ranInt);
		}
		return money;
	}
}
