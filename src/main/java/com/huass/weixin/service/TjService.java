package com.huass.weixin.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Page;
import com.huass.weixin.dao.BagDao;
import com.huass.weixin.dao.BagLogDao;
import com.huass.weixin.dao.SchoolBagDao;
import com.huass.weixin.dao.UserDao;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.BagLog;
import com.huass.weixin.entity.SchoolBag;
import com.huass.weixin.entity.User;

@Component
@Transactional(readOnly = true)
public class TjService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BagLogDao bagLogDao;
	@Autowired
	private BagDao bagDao;
	@Autowired
	private SchoolBagDao schoolBagDao;
	
	public int getBagAmount() {
		// TODO Auto-generated method stub
		return bagLogDao.getCount("select count(*) from BagLog");
	}


	public int getUserAmount() {
		// TODO Auto-generated method stub
		return userDao.getCount("select count(*) from User where openId is not null");
	}
	
	 public Page<User> findUser(Page<User> page,User user) {
		 DetachedCriteria dc = userDao.createDetachedCriteria();
		 dc.add(Restrictions.isNotNull("openId")); 
		 if(StringUtils.isNotEmpty(user.getNameV())){
				dc.add(Restrictions.like("nameV", "%"+user.getNameV()+"%"));
			}
		 if(StringUtils.isNotEmpty(user.getId())){
				dc.add(Restrictions.eq("id", user.getId()));
			}
		 return userDao.find(page, dc);
	 }


	public Page<BagLog> findBaglog(Page<BagLog> page,BagLog bagLog) {
		 DetachedCriteria dc = bagLogDao.createDetachedCriteria();
		 if(StringUtils.isNotEmpty(bagLog.getUserId())){
				dc.add(Restrictions.eq("userId", bagLog.getUserId()));
			}
		 if(StringUtils.isNotEmpty(bagLog.getBagId())){
				dc.add(Restrictions.eq("bagId", bagLog.getBagId()));
			}
		 if(bagLog.getMoneyN() != null){
				dc.add(Restrictions.eq("bagId", bagLog.getMoneyN()));
			}
		 return bagLogDao.find(page, dc);
	}


	public Page<Bag> findBag(Page<Bag> page, Bag bag) {
		DetachedCriteria dc = bagDao.createDetachedCriteria();
		 if(StringUtils.isNotEmpty(bag.getUserId())){
				dc.add(Restrictions.eq("userId", bag.getUserId()));
			}
		 if(StringUtils.isNotEmpty(bag.getSchoolBagId())){
				dc.add(Restrictions.eq("schoolBagId", bag.getSchoolBagId()));
			}
		 return bagDao.find(page, dc);
	}


	public Page<SchoolBag> findSchoolBag(Page<SchoolBag> page,
			SchoolBag schoolBag) {
		DetachedCriteria dc = schoolBagDao.createDetachedCriteria();
		 if(StringUtils.isNotEmpty(schoolBag.getUserId())){
				dc.add(Restrictions.eq("userId", schoolBag.getUserId()));
			}
		return schoolBagDao.find(page, dc);
	}
}
