package com.huass.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.weixin.dao.SuggestDao;
import com.huass.weixin.entity.Suggest;


@Component
@Transactional(readOnly = true)
public class SService extends BaseService {
	@Autowired
	private SuggestDao suggestDao;

	@Transactional(readOnly = false)
	public void save(Suggest suggest) {
		suggestDao.save(suggest);
	}
	public List voucher(String openId)
	{
		String sql = "SELECT v.MONEY_N,v.CODE_V,u.NAME_V FROM t_bag b,t_voucher v,t_user u,t_baglog l WHERE v.ID = l.VOUCHER_ID AND l.BAG_ID =b.ID AND l.BAG_ID = b.ID AND u.ID = b.USER_ID AND u.OPENID =:p1";
		List list = suggestDao.findBySql(sql,new Parameter(openId));
		
		return list;
	}

}
