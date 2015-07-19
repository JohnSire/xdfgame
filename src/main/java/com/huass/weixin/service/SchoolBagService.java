package com.huass.weixin.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.persistence.Parameter;
import com.huass.common.service.BaseService;
import com.huass.common.utils.Collections3;
import com.huass.weixin.dao.BagDao;
import com.huass.weixin.dao.BingoDao;
import com.huass.weixin.dao.SchoolBagDao;
import com.huass.weixin.dao.SchoolBingoDao;
import com.huass.weixin.entity.Bag;
import com.huass.weixin.entity.Bingo;
import com.huass.weixin.entity.SchoolBag;
import com.huass.weixin.entity.SchoolBingo;
import com.huass.weixin.utils.SchoolBagBingo;

@Component
@Transactional(readOnly = true)
public class SchoolBagService extends BaseService 
{
	@Autowired
	private SchoolBagDao schoolBagDao;
	@Autowired
	private BingoDao bingoDao;
	@Autowired
	private SchoolBingoDao schoolBingoDao;
	@Autowired
	private BagDao bagDao;
	
	
	@Transactional(readOnly=false)
	public String save(SchoolBag schoolBag,Map<Object,Object> map,int sum)
	{
		schoolBagDao.save(schoolBag);
		String hql = "from Bingo where schoolId=:p1";
		List<Bingo> list = bingoDao.find(hql, new Parameter(schoolBag.getSchoolId()));
		List<SchoolBingo> schoolBingos = new ArrayList<SchoolBingo>();
	     Iterator<Object> iter1 = map.keySet().iterator();
		 while (iter1.hasNext()) {
			String obj = iter1.next().toString();
			if("userId".equals(obj)||"schoolId".equals(obj)){
				continue;
			}
			Object[] objs= (Object[])map.get(obj);
			for (int i = 0; i < objs.length; i++) {
				if("0".equals(objs[i])){
					continue;
				}
				for (Bingo bingo : list) {
					if(obj.equals(bingo.getId())){
						SchoolBingo schoolbingo = new SchoolBingo();
						schoolbingo.setSchoolId(schoolBag.getSchoolId());
						schoolbingo.setSchoolbagId(schoolBag.getId());
						schoolbingo.setMoneyN(bingo.getMoneyN());
						schoolbingo.setNumN(Integer.parseInt(objs[i].toString()));
						schoolBingos.add(schoolbingo);
						Integer integer = bingo.getNumN()-Integer.parseInt(objs[i].toString());
						String hql1 = "update Bingo set numN=:p1 where id=:p2";
						bingoDao.update(hql1,new Parameter(integer,bingo.getId()));
					}
				}
			}
		}
		 if(Collections3.isNotEmpty(schoolBingos)){
			 schoolBingoDao.save(schoolBingos);
		 }
		 Bag bag = new Bag();
		 bag.setNumN(sum);
		 bag.setSchoolBagId(schoolBag.getId());
		 bag.setUserId(schoolBag.getUserId());
		 bag.setType(Bag.da_bag_type);
		 bagDao.save(bag);
		 return bag.getId();
	}
	
	public List<SchoolBagBingo> findSchoolId(String schoolId,int pageNo,int rows)
	{
		String sql = "SELECT tt.id,tt.date_n,tt.head_image,(SELECT SUM(sums)-SUM(num_n)) num_n,tt.sums leavess FROM (SELECT bag.id,bag.head_image,bag.num_n,bag.date_n,SUM(bingo.num_n) sums FROM t_school_bag bag,t_school_bingo bingo WHERE bag.id=bingo.school_bag_id AND bag.school_id=:p1 GROUP BY bag.id) tt GROUP BY tt.id ORDER BY date_n DESC";
		int start = (pageNo -1) * rows;
		List list = schoolBagDao.findBySql(sql,new Parameter(schoolId),start,rows);
		
		List<SchoolBagBingo> lists = new ArrayList<SchoolBagBingo>();
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[])list.get(i);
			SchoolBagBingo schoolBagBingo = new SchoolBagBingo();
			try {schoolBagBingo.setId(obj[0].toString());} catch (Exception e) {}
			try {schoolBagBingo.setDate(obj[1].toString());} catch (Exception e) {}
			try {schoolBagBingo.setHeadImage(obj[2].toString());} catch (Exception e) {}
			try {schoolBagBingo.setNumN(obj[3].toString());} catch (Exception e) {}
			try {schoolBagBingo.setLeavess(obj[4].toString());} catch (Exception e) {}
			lists.add(schoolBagBingo);
		}
		return lists;
	}
	public int xxhongbao()
	{
		String sql = "SELECT s.NAME_V,COUNT(*) FROM t_school_bag b,t_school s WHERE b.SCHOOL_ID = s.ID GROUP BY b.SCHOOL_ID";
		List list = schoolBagDao.findBySql(sql);
		
		return list.size();
	}
	
	public List<Object[]> getSchoolShareNum()
	{
		String sql = "select city,share_num from t_school";
		List<Object[]> list = schoolBagDao.findBySql(sql);
		return list;
	}
	
	
	public SchoolBag findById(String schoolBagId) {
		String sql = "from SchoolBag where id=:p1";
		return schoolBagDao.getByHql(sql, new Parameter(schoolBagId));
	}
	
	public SchoolBag findBySchoolBagId(String schoolbagId){
		String hql = "from SchoolBag where id = :p1";
		return schoolBagDao.getByHql(hql, new Parameter(schoolbagId));
	}
	
	
	@Transactional(readOnly = false)
	public void update(SchoolBag schoolbag){
		schoolBagDao.update(schoolbag);
	}
}
