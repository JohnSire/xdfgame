package com.huass.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.weixin.dao.BagDao;
import com.huass.weixin.dao.BagLogDao;
import com.huass.weixin.entity.Bag;

@Component
@Transactional(readOnly = true)
public class BagService extends BaseService 
{
	@Autowired
	private BagDao bagDao;
	@Autowired
	private BagLogDao baglogDao;
	
	
	@Transactional(readOnly = false)
	public void save(Bag bag){
		bagDao.save(bag);
	}
	
	@Transactional(readOnly = false)
	public void saveList(List<Bag> list){
		bagDao.save(list);
	}
	
	public Bag findById(String id){
		String hql = "from Bag where id = :p1";
		Bag bag = bagDao.getByHql(hql,new Parameter(id));
		return bag;
	}
	public List<Bag> getAll(){
		String hql = "from Bag where type='0'";
		return bagDao.find(hql);
	}
	@Transactional(readOnly = false)
	public void update(Bag bag){
		bagDao.update(bag);
	}
	
	public List<Bag> fingByDthb(String userId){
		String hql = "from Bag where userId = :p1";
		List newList = (List) bagDao.find(hql, new Parameter(userId));
		return newList;
	}
	
	public List<Object> bag(String openId)
	{	
		String sql = "SELECT v.CODE_V,v.MONEY_N,u.NAME_V FROM t_bag b,t_voucher v,t_baglog l,t_user u WHERE v.ID=l.VOUCHER_ID AND l.BAG_ID = b.ID AND u.OPENID=:p1 ";
		return bagDao.findBySql(sql,new Parameter(openId));
	}
	
	@Transactional(readOnly=false)
	public void delLittleBag()
	{
		String sql = "DELETE FROM V_LITTLE_BAG";
		bagDao.executeSQL(sql, null);
	}
	
	@Transactional(readOnly = false)
	public void updateBySchoolbagId(String id) {
		String sql = "update t_bag set num_n=num_n-1 where school_bag_id = :p1";
		bagDao.executeSQL(sql, new Parameter(id)); 
	}

	public Bag findSchoolBagId(String schoolBagId) {
		String hql = "from Bag where SchoolBagId = :p1";
		return bagDao.getByHql(hql,new Parameter(schoolBagId));
	}
}
