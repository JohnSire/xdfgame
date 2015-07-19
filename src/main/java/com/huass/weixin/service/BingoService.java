package com.huass.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.weixin.dao.BingoDao;
import com.huass.weixin.entity.Bingo;

@Component
@Transactional(readOnly=true)
public class BingoService extends BaseService
{
	@Autowired
	private BingoDao bingoDao;
	
	
	public List<Bingo> findByType(Integer type)
	{
		String hql = "from Bingo where typeN =:p1";
		return bingoDao.find(hql, new Parameter(type)); 
	}
	
	
	public List<Bingo> findBySchoolId(String schoolId)
	{
		String hql = "from Bingo where schoolId=:p1 ORDER BY moneyN ASC";
		List<Bingo> list = bingoDao.find(hql, new Parameter(schoolId));
		return list;
	}
	
	@Transactional(readOnly=false)
	public void updateById(Integer numN,String id)
	{
		String hql = "update Bingo set numN=:p1 where id=:p2";
		bingoDao.update(hql,new Parameter(numN,id));
	}
	
}
