package com.huass.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.common.utils.StringUtils;
import com.huass.weixin.dao.CityDao;
import com.huass.weixin.entity.City;


@Component
@Transactional(readOnly = true)
public class CityService extends BaseService {
	@Autowired
	private CityDao cityDao;

	
	public List<City> findAll() {
		String hql = "from City";
		List list = cityDao.find(hql);
		return list;
	}
	
	
	
	public String findByCity(String city){
		
		String cn = City.CITY_CACHE.get(city);
		if(StringUtils.isBlank(cn))
		{
			String hql = "from City where spell = :p1";
			City citys = cityDao.getByHql(hql, new Parameter(city));
			cn = citys.getChinese();
			City.CITY_CACHE.put(city, cn);
		}
		return cn;
	}
}
