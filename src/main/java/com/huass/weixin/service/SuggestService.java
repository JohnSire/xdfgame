package com.huass.weixin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.service.BaseService;
import com.huass.weixin.dao.SuggestDao;
import com.huass.weixin.entity.Suggest;


@Component
@Transactional(readOnly = true)
public class SuggestService extends BaseService {
	@Autowired
	private SuggestDao suggestDao;

	@Transactional(readOnly = false)
	public void save(Suggest suggest) {
		suggestDao.save(suggest);
	}

	public List<Suggest> findAll() {
		String hql = "select new Suggest(s.contentV,s.dateD,u.nameV) from User u,Suggest s where u.id=s.userId";
		List<Suggest> list = suggestDao.find(hql);
		return list;
	}
	
	public List<Object> suggestAll() {
		String sql = "SELECT t.id,u.name_v,t.CONTENT_V,t.date_d FROM t_suggest t,t_user u WHERE t.user_id=u.id ORDER BY date_d DESC";
		List<Object> list = suggestDao.findBySql(sql);
		return list;
	}
}
