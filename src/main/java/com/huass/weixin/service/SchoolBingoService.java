package com.huass.weixin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.common.utils.Collections3;
import com.huass.weixin.dao.SchoolBingoDao;
import com.huass.weixin.entity.Bingo;
import com.huass.weixin.entity.SchoolBingo;

@Component
@Transactional(readOnly = true)
public class SchoolBingoService extends BaseService 
{
	@Autowired
	private SchoolBingoDao schoolBingoDao;
	
	@Transactional(readOnly=false)
	public void save(List<SchoolBingo> list)
	{
		schoolBingoDao.save(list);
	}
	
	public SchoolBingo findByMoney(Integer moneyN)
	{
		String hql = "from SchoolBingo where moneyN=:p1";
		return schoolBingoDao.findOne(hql,new Parameter(moneyN));
	}
	@Transactional(readOnly=false)
	public void updateBymoneyN(Integer numN,Integer moneyN)
	{
		String hql = "update SchoolBingo set numN=:p1 where moneyN=:p2";
		schoolBingoDao.update(hql,new Parameter(numN,moneyN));
	}
	
	
	public void findAllBingo()
	{
		String sql1 = "select distinct(sb.SCHOOL_BAG_ID) from t_school_bingo sb group by sb.SCHOOL_BAG_ID";
		String sql2 = "select sb.SCHOOL_BAG_ID, sb.MONEY_N, sb.NUM_N,COUNT(SB.SCHOOL_BAG_ID) from t_school_bingo sb group by sb.SCHOOL_BAG_ID, sb.MONEY_N, sb.NUM_N";
		List<String> bagIds = schoolBingoDao.findBySql(sql1);
		List<Object[]> bingoData = schoolBingoDao.findBySql(sql2);
		for(Object[] objArr : bingoData)
		{
			if(bagIds.contains(objArr[0]))
			{
				List<Integer> bings = Bingo.BINGO_MAP.get(objArr[0]);
				if(Collections3.isEmpty(bings))
				{
					bings = new ArrayList<Integer>();
					Bingo.BINGO_MAP.put(objArr[0].toString(), bings);
				}
				bings.addAll(Collections.nCopies(Integer.parseInt(objArr[2].toString()), Integer.parseInt(objArr[1].toString())));
			}
		}
	}
	
	
	public List<Integer> addBingoByBagId(String bagId)
	{
		List<Integer> bings = null;
		String sql = "select sb.SCHOOL_BAG_ID, sb.MONEY_N, sb.NUM_N from t_school_bingo sb  where sb.SCHOOL_BAG_ID = :p1";
		List<Object[]> bingoData = schoolBingoDao.findBySql(sql, new Parameter(bagId));
		if(Collections3.isNotEmpty(bingoData))
		{
			for(Object[] objArr : bingoData)
			{
				bings = Bingo.BINGO_MAP.get(objArr[0]);
				if(Collections3.isEmpty(bings))
				{
					bings = new ArrayList<Integer>();
					Bingo.BINGO_MAP.put(objArr[0].toString(), bings);
				}
				bings.addAll(Collections.nCopies(Integer.parseInt(objArr[2].toString()), Integer.parseInt(objArr[1].toString())));
			}
		}
		return bings;
	}
	
	public void gcBingCache()
	{
		if(Bingo.BING_TIMEOUT.size() > 0)
		{
			for(String bagId : Bingo.BING_TIMEOUT.keySet())
			{
				Long lastTime = Bingo.BING_TIMEOUT.get(bagId);
				long sub = (System.currentTimeMillis() - lastTime)/3600000;
				if(sub >0)
				{
					Bingo.BING_TIMEOUT.remove(bagId);
					Bingo.BINGO_MAP.remove(bagId);
				}
			}
		}
	}
}
