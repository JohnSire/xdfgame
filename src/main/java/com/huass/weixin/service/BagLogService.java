package com.huass.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.weixin.dao.BagLogDao;
import com.huass.weixin.dao.HuDongDao;
import com.huass.weixin.entity.BagLog;
import com.huass.weixin.entity.HuDong;

@Component
@Transactional(readOnly = true)
public class BagLogService extends BaseService{
	@Autowired
	private BagLogDao bagLogDao;
	@Autowired
	private HuDongDao hudongDao;
	
	public boolean isOpened(String openId, String bagId){
		boolean flag = false;
		String hql = "select  count(bl.id) from  BagLog bl where bl.bagId = :p1 and bl.userId IN (select id from User where openId = :p2)";
		String count =  bagLogDao.findCount(hql, new Parameter(bagId, openId));
		Integer countInt = Integer.parseInt(count);
		if(countInt > 0)
		{
			return true;
		}
		return flag;
	}
	
	public boolean isOverBag(String bagId){
		boolean flag = false;
		String hql = "select count(bl.id) from BagLog bl where bl.bagId = :p1";
		String count =  bagLogDao.findCount(hql, new Parameter(bagId));
		Integer countInt = Integer.parseInt(count);
		if(countInt >= 5)
		{
			return true;
		}
		return flag;
	}
	
	public boolean isFullBag(String bagId){
		boolean flag = false;
		String hql = "select b.numN from Bag b where b.id = :p1";
		String num =  bagLogDao.findCount(hql, new Parameter(bagId));
		Integer countInt = Integer.parseInt(num);
		if(countInt <=0 )
		{
			flag = true;
		}
		return flag;
	}
	
	@Transactional(readOnly = false)
	public void save(BagLog bagLog){
		bagLogDao.save(bagLog);
	}
	
	public List<BagLog> findAllDivBags(String bagId){
		String hql = "from BagLog b where b.bagId = :p1";
		List list = bagLogDao.find(hql, new Parameter(bagId));
		return list;
	}
	
	public List<BagLog> findMybags(String openId,int a,int b){
		int startNum = a * b;
		String hql = "from BagLog b where b.userId in (select id from User where openId = :p1) order by b.dateD desc";
		List list = bagLogDao.find(hql, new Parameter(openId), startNum,b);
		return list;
	}
	
	
	public BagLog fingBagLog(String userId,String bagId){
		String hql = " from BagLog b where b.userId = :p1 and b.bagId = :p2";
		BagLog baglog = bagLogDao.getByHql(hql, new Parameter(userId,bagId));
		return baglog;
	}
	public int count(){
		String hql = "select count(*) from BagLog";
		int i = bagLogDao.getCount(hql);
		return i;
	}
	public List tongji(){
		String sql = "SELECT name_v,COUNT(name_v)hbs,(SELECT SUM(sums)-SUM(num_n)) shuliang FROM (SELECT s.name_v,(SELECT SUM(bb.num_n)) sums,b.num_n  FROM t_school s,t_school_bag b,t_school_bingo bb WHERE s.ID =b.SCHOOL_ID AND bb.SCHOOL_BAG_ID=B.ID GROUP BY bb.school_bag_id) tt GROUP BY name_v";
		List list = bagLogDao.findBySql(sql);
		return list;
	}

	public HuDong getHuDong(){
		String hql = "from HuDong where id = 1";
		HuDong hudong = hudongDao.getByHql(hql);
		return hudong;
	}


}
