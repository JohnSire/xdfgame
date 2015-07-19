package com.huass.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.weixin.dao.TempDao;
import com.huass.weixin.entity.Temp;

@Component
@Transactional(readOnly = true)
public class TempService extends BaseService {
	
	@Autowired
	private TempDao tempDao;
	
	
	@Transactional(readOnly = false)
	public boolean findTempByUserId(String OpenId,String bagId){
		boolean flag = false;
		String hql = "select count(t.id) from Temp t where t.userId = :p1 and t.bagId = :p2";
		String count = tempDao.findCount(hql, new Parameter(OpenId, bagId));
		Integer countInt = Integer.parseInt(count);
		if(countInt>0){
			return true;
		}
		return flag;
	}
	
	@Transactional(readOnly = false)
	public Temp findTemp(String openId,String bagId){
		String hql = " from Temp t where t.userId = :p1 and t.bagId = :p2";
		return tempDao.findOne(hql, new Parameter(openId, bagId));
	}
	
	@Transactional(readOnly = false)
	public void save(Temp temp){
		tempDao.save(temp);
	}
	
	@Transactional(readOnly = false)
	public void delete(Temp temp){
		tempDao.delete(temp);
	}

}
