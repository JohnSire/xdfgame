package com.huass.weixin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.service.BaseService;
import com.huass.common.utils.StringUtils;
import com.huass.weixin.dao.VoucherDao;
import com.huass.weixin.entity.Voucher;
import com.huass.weixin.utils.BingUtil;

@Component
@Transactional(readOnly=true)
public class VoucherService extends BaseService
{
	@Autowired
	private VoucherDao voucherDao;

	
	
	public Voucher bingoVoucher(String bagId)
	{
		BingUtil bing = new BingUtil();
		Integer moneyD = bing.bingMoneyByBagId(bagId);
		List<Integer> moneyList = new ArrayList<Integer>();
		Voucher v = voucherDao.findByMoney(moneyD);
		while(v == null && moneyD != -1)
		{
			moneyList.add(moneyD);
			moneyD = bing.bingNoMoney(bagId, moneyList);
			v = voucherDao.findByMoney(moneyD);
		}
		return v;
	}
	public Voucher getVoucherById(String id){
		if(StringUtils.isNotBlank(id)){
			return voucherDao.get(id);
		}
		return null;
	}
	
	
	@Transactional(readOnly=false)
	public void updateVoucher(Voucher voucher)
	{
		voucherDao.update(voucher);
	}
	
	
	
	public Voucher findById(String id){
		return voucherDao.get(id);
	}
	
}
