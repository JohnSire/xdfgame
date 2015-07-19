package com.huass.weixin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huass.common.persistence.BaseDao;
import com.huass.common.persistence.Parameter;
import com.huass.common.utils.StringUtils;
import com.huass.weixin.entity.BagLog;
import com.huass.weixin.entity.Temp;
import com.huass.weixin.entity.User;

@Repository
public class DrawBagDao extends BaseDao {
	
	public Temp getTemp(String openId, String bagId){
		String sql1 = "FROM Temp WHERE openid = :p1 and bag_Id = :p2";
		Object obj = this.getByHql(sql1, new Parameter(openId, bagId)); 
		if(obj!=null && obj instanceof Temp)
			return (Temp)obj;
		return null;
	}

	public int getAllBagMoney(String userId) {
		String sql = "select sum(money_d) from t_baglog where user_Id= :P1";
		List list = this.findBySql(sql, new Parameter(userId));
		if(list.get(0)!=null){
			String res = list.get(0).toString();
			return Integer.parseInt(res);  
		}
		return 0;
	} 
}
