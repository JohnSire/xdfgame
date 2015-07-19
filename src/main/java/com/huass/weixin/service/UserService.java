package com.huass.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.common.utils.Collections3;
import com.huass.common.utils.StringUtils;
import com.huass.common.utils.wxApiUtils;
import com.huass.weixin.dao.UserDao;
import com.huass.weixin.entity.User;
import com.huass.weixin.utils.WXConst;


@Component
@Transactional(readOnly = true)
public class UserService extends BaseService 
{
	@Autowired
	private UserDao userDao;
	
	public String getAccessToken()
	{ 
		String token = null;
		String hql = "from User where nameV = :p1";
		User user = userDao.findOne(hql, new Parameter(WXConst.GLOBAL_ACCESS_TOKEN));
		if(user != null)
		{
			token = user.getHeadImage();
		}
		return token;
	}
	
	@Transactional(readOnly=false)
	public void saveAccessToken(String token)
	{
		String hql = "from User where nameV = :p1";
	    User user = (User)this.userDao.findOne(hql, new Parameter(new Object[] {WXConst.GLOBAL_ACCESS_TOKEN}));
	    if (user == null)
	    {
	      user = new User();
	      user.setNameV(WXConst.GLOBAL_ACCESS_TOKEN);
	      user.setHeadImage(token);
	      this.userDao.save(user);
	    }
	    else
	    {
	      user.setHeadImage(token);
	      this.userDao.update(user);
	    }
	}
	
	
	@Transactional(readOnly=false)
	public void saveUser(JSONObject userJson) throws Exception
	{
		Object errorcode = userJson.get("errcode");
		if(errorcode == null)
		{
			String openId = userJson.getString("openid");
			if(StringUtils.isNotBlank(openId))
			{
				User user = this.findByOpenId(openId);
				if(user == null)
				{
					Object token = userJson.get("access_token");
					if(token != null)
					{
						wxApiUtils wxapiutil = new wxApiUtils();
						User newuser = wxapiutil.getUserInfoFromWeb(token.toString(), openId);
						if(newuser != null)
						{
							this.save(newuser);
						}
					}
				}
			}
		}
	}
	
	
	@Transactional(readOnly=false)
	public boolean isSubscribe(String openId)
	{
		boolean flag = false; 
		String hql = "from User where openId = :p1";
		List<User> users = userDao.find(hql, new Parameter(openId));
		if(Collections3.isNotEmpty(users))
		{
			for(User user : users)
			{
				if(StringUtils.isEmpty(user.getNameV()) && StringUtils.isEmpty(user.getHeadImage()))
				{
					userDao.delete(user);
				}
			}
			flag = true;
		}
		else
		{
			wxApiUtils apiUtil = new wxApiUtils();
			try {
				User user = apiUtil.getUserInfoFromWX(openId);
				System.out.println("userService user:"+user+"=="+user.getNameV());
				if(user != null && StringUtils.isNotEmpty(user.getNameV()))
				{
					this.save(user);
					flag = true;
				}
				else if(user != null && StringUtils.isEmpty(user.getNameV()))
				{
					User user2 = apiUtil.getUserInfoFromWX(openId);
					System.out.println("userService user2:"+user+"=="+user.getNameV());
					if(user2 != null && StringUtils.isNotEmpty(user2.getNameV()))
					{
						this.save(user2);
						flag = true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	@Transactional(readOnly=false)
	public boolean isSubscribe1(String openId)
	{
		boolean flag = false; 
		String hql = "from User where openId = :p1";
		List<User> users = userDao.find(hql, new Parameter(openId));
		if(Collections3.isNotEmpty(users))
		{
			for(User user : users)
			{
				if(StringUtils.isEmpty(user.getNameV()) && StringUtils.isEmpty(user.getHeadImage()))
				{
					userDao.delete(user);
				}
			}
			flag = true;
		}
		return flag;
	}
	public User findByOpenId(String openId)
	{
		String hql = "from User where openId = :p1";
		List<User> users = userDao.find(hql, new Parameter(openId));
		if(Collections3.isNotEmpty(users))
		{
			return users.get(0);
		}
		return null;
	}
	public User getDateNum(String dateN,String openId){
		String hql = "from User where shareDate = :p1 and openId = :p2";
		return userDao.getByHql(hql, new Parameter(dateN,openId));
	}
	
	@Transactional(readOnly=false)
	public User save(String openId)
	{
		User user = new User();
		user.setOpenId(openId);
		userDao.save(user);
		return user;
	}
	
	@Transactional(readOnly=false)
	public User save(User user)
	{	
		userDao.save(user);
		return user;
	}
	
	@Transactional(readOnly=false)
	public User update(User user)
	{
		userDao.update(user);
		return user;
	}
	
	public User getById(String userId)
	{
		return userDao.get(userId);
	}
	
	public Integer getMaxNum(){
		String sql = "SELECT MAX(BOATNUM) FROM t_user ";
		List<Object> list = userDao.findBySql(sql);
		Integer aa = (Integer) list.get(0);
		return aa;
	}
	
	public String getCountUser(){
		String sql = "select count(*) from t_user";
		List<Object> list = userDao.findBySql(sql);
		String aa = list.get(0).toString();
		return aa;
	}
	public List<User> getUserEmail(){
		String hql = "from User where nameV <>'"+WXConst.GLOBAL_ACCESS_TOKEN+"'";
		return userDao.find(hql);
	}
	
	@Transactional(readOnly=false)
	public void updateUserBysql(String sql){
		userDao.update(sql, null);
	}
	@Transactional(readOnly=false)
	public void deleteUserBysql(String sql){
		userDao.executeHQL(sql,null);
	}

	public String getOpenIdByuserId(String userId) {
		String hql = "select openId from User where id = :p1 and statusV = '0'";
		List<Object> list = userDao.find(hql, new Parameter(userId));
		if (list != null && list.size() > 0) {
			return list.get(0).toString();
		}
		return null;
	}
	public String getToken(){
		String hql = "from User where nameV = '204idkdkaldkaldACCESS_TOKENjalkdfjlkajlefjafuosdufiuo3'";
		User user = userDao.getByHql(hql);
		if(user!=null){
			return user.getHeadImage();
		}
		return null;
	}
	
	public boolean everyNum(String openId,String date){
		boolean flag = false;
		String hql = "from User u where u.openId = :p1 and u.shareDate = :p2";
		User user = userDao.getByHql(hql, new Parameter(openId,date));
		if(user!=null){
			if(user.getShareNum()>=5){
				return true;
			}
		}
		return flag;
	}
	
	
	@Transactional(readOnly=false)
	public void updateBySchoolId(String schoolid,String id){
		String hql = "update User set schoolId=:p1 where id=:p2";
		userDao.update(hql,new Parameter(schoolid,id));
	}
	
	
	public User getUserByopenId(String openId) {
		return userDao.getUser(openId);
	}
	public int userCount(){
		String sql = "select count(*) from t_user";
		int i = userDao.getCount(sql);
		return i;
	}
	
}
