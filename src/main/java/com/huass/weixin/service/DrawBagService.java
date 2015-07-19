package com.huass.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.service.BaseService;
import com.huass.weixin.dao.DrawBagDao;
import com.huass.weixin.entity.BagLog;
import com.huass.weixin.entity.Temp;

@Component
@Transactional(readOnly = true)
public class DrawBagService  extends BaseService {
	
	@Autowired
	private DrawBagDao drawBagDao;
	
	public Temp getTemp(String openId, String bagId) {
		return drawBagDao.getTemp(openId, bagId);
	}

	public int getAllBagMoney(String userId) {
		return drawBagDao.getAllBagMoney(userId);
	}

	public void drawBag(Temp temp, BagLog baglog) {
		drawBagDao.save(baglog);
		drawBagDao.delete(temp);
	}
	
}
