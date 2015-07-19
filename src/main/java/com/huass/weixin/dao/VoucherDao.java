package com.huass.weixin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huass.common.persistence.BaseDao;
import com.huass.common.persistence.Parameter;
import com.huass.common.utils.Collections3;
import com.huass.weixin.entity.Voucher;

@Repository
public class VoucherDao extends BaseDao<Voucher>
{
	
	public Voucher findByMoney(Integer money)
	{
		Voucher v = null;
		String hql = "from Voucher where moneyN=:p1 and statusN=0";
		List<Voucher> vs = this.find(hql, new Parameter(money), 0, 1);
		if(Collections3.isNotEmpty(vs))
		{
			v = vs.get(0);
		}
		return v;
	}
}
