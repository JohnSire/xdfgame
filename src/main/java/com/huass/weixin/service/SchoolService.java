package com.huass.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.weixin.dao.SchoolDao;
import com.huass.weixin.entity.School;

@Component
@Transactional(readOnly = true)
public class SchoolService extends BaseService 
{
	@Autowired
	private SchoolDao schoolDao;
	
	
	
	public School findByCity(String id){
		String hql = "from School where cityV = :p1";
		School school = schoolDao.getByHql(hql,new Parameter(id));
		return school;
	}
	
	public School findBySecret(String secret){
		String hql = "from School where Secret = :p1";
		School school = schoolDao.getByHql(hql,new Parameter(secret));
		return school;
	}
	
	
	public List<School> findAll(){
		String hql = "from School";
		List<School> list = schoolDao.find(hql);
		return list;
	}
	
	
	public School findById(String schoolId){
		return schoolDao.get(schoolId);
	}
	
	
	public School findByUrl(String url){
		String hql = "from School where urlN = :p1";
		return schoolDao.getByHql(hql, new Parameter(url));
	}
	
	public void updateSchool(School school)
	{
		schoolDao.update(school);
	}
	
	public School findCity(String city){
		String hql = "from School where city = :p1";
		School school = schoolDao.getByHql(hql, new Parameter(city));
		return school;
	}
}
