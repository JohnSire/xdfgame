package com.huass.weixin.dao;

import org.springframework.stereotype.Repository;

import com.huass.common.persistence.BaseDao;
import com.huass.weixin.entity.User;


@Repository
public class UserDao extends BaseDao<User> {
	
	@Override
	public void save(User user){
		user.setCity("深圳");
		super.save(user);
	}
	
	public User getUser(String openId){
		String sql = "from User u where u.openId = '"+openId+"'";
		return this.getByHql(sql);
	}
}

