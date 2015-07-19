package com.huass.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.weixin.dao.UrlDao;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.Url;

@Component
@Transactional(readOnly = true)
public class UrlService extends BaseService 
{
	@Autowired
	private UrlDao urlDao;
	
	@Transactional(readOnly = false)
	public void save(Url url){
		urlDao.save(url);
	}
	public Url findById(String id){
		String hql = "from Url where id = :p1";
		Url url = urlDao.getByHql(hql,new Parameter(id));
		return url;
	}
}
