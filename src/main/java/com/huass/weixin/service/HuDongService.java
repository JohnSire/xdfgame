package com.huass.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.huass.common.service.BaseService;
import com.huass.common.utils.StringUtils;
import com.huass.weixin.dao.HuDongDao;
import com.huass.weixin.entity.HuDong;
import com.huass.weixin.entity.School;
import com.huass.weixin.utils.WXConst;


@Component
@Transactional(readOnly=true)
public class HuDongService extends BaseService
{
	@Autowired
	private HuDongDao hdDao;
	
	@Autowired
	private SchoolService schoolService;
	
	@Transactional(readOnly=false)
	public void addHuDongNum()
	{
		HuDong hd = hdDao.get(HuDong.PK_HD_ID);
		hd.setHdNums(hd.getHdNums()+WXConst.HD_NUM);
		hd.setShareNums(hd.getShareNums() + WXConst.SHARE_NUM);
		hdDao.update(hd);
		this.saveSchoolHDNum();
	}
	
	
	public void addSchoolMemShareNum(String schoolId)
	{
		if(StringUtils.isBlank(schoolId))
		{
			WXConst.SHARE_NUM++;
		}
		else
		{
			Integer num = HuDong.SHARE_MAP.get(schoolId);
			if(num == null)
			{
				School school = schoolService.findById(schoolId);
				if(school != null)
				{
					HuDong.SHARE_MAP.put(schoolId, 1);
				}
			}
			else
			{
				num++;
				HuDong.SHARE_MAP.put(schoolId, num);
			}
		}
	}
	
	@Transactional(readOnly=false)
	public void saveSchoolHDNum()
	{
		if(HuDong.SHARE_MAP.size() > 0)
		{
			for(String schoolId : HuDong.SHARE_MAP.keySet())
			{
				School school = schoolService.findById(schoolId);
				if(school != null)
				{
					Integer allShareNum = HuDong.SHARE_MAP.get(schoolId) + (school.getSHARE_NUM() == null ? 0 : school.getSHARE_NUM()) ;
					school.setSHARE_NUM(allShareNum);
					schoolService.updateSchool(school);
					HuDong.SHARE_MAP.put(schoolId, 0);
				}
				else
				{
					HuDong.SHARE_MAP.remove(schoolId);
				}
			}
		}
	}
	
	
	public HuDong get()
	{
		return hdDao.get(HuDong.PK_HD_ID);
	}
	
	
	
	
	public Object[] countSchoolNum()
	{
		String sql = "select s.school, count(b.id) nums from t_muser m, T_BAG b, t_secret s where  m.secret_id is not null and m.id=b.USER_ID and m.secret_id=s.id group by s.school order by nums desc";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		String sql2 = "select s.school, count(b.id) nums from t_muser m, BAG_LOG b, t_secret s where  m.secret_id is not null and m.id=b.USER_ID and m.secret_id=s.id group by s.school order by nums desc";
		List<Object[]> bagrec = hdDao.findBySql(sql2);
		
		Object[] objArr = new Object[2];
		objArr[0] = bagsend;
		objArr[1] = bagrec;
		return  objArr;
	}
	
	
	public List<Object[]> countTeacherNum()
	{
		String sql = "select ID, CITY, NAME_V , sum(nums) allnums from BAG_ALL group by id,CITY,name_v order by allnums desc limit 0, 100";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		return  bagsend;
	}
	public List<Object[]> countTecSuperNum()
	{
		String sql = "select m.ID, m.CITY, m.NAME_V , count(b.id) allnums from t_bag b, t_muser m where m.secret_id is not null and b.type_n = 2 and m.id = b.user_id  group by m.id, m.city,m.name_v  order by allnums desc limit 0, 100";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		return  bagsend;
	}
	
	public List<Object[]> countTecCommonNum()
	{
		String sql = "select m.ID, m.CITY, m.NAME_V , count(b.id) allnums from t_bag b, t_muser m where m.secret_id is not null and b.type_n = 1 and m.id = b.user_id  group by m.id, m.city,m.name_v  order by allnums desc limit 0, 100";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		return  bagsend;
	}
	
	public List<Object[]> countTecXXNum()
	{
		String sql = "select m.ID, m.CITY, m.NAME_V , count(b.id) allnums from t_bag b, t_muser m where m.secret_id is not null and b.type_n = 0 and m.id = b.user_id  group by m.id, m.city,m.name_v  order by allnums desc limit 0, 100";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		return  bagsend;
	}
	public List<Object[]> countTecunLingum()
	{
		String sql = "select u.id, u.CITY, u.NAME_V, count(l.id) allnums from t_muser u , t_bag b, bag_log l where b.user_id = u.id and b.id = l.bag_id group by u.id, u.NAME_V,u.CITY order by allnums desc";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		return  bagsend;
	}
	public List<Object[]> countTecLingum()
	{
		String sql = "select m.ID, m.CITY, m.NAME_V , count(b.id) allnums from bag_log b, t_muser m where m.secret_id is not null and m.id = b.user_id  group by m.id, m.city,m.name_v  order by allnums desc limit 0, 100";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		return  bagsend;
	}
	
	public List<Object[]> countCommonVoucher()
	{
		String sql = "select  money_n, count(id)from t_voucher where status_n = 0 and type_n=0 group by money_n order by money_n";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		return  bagsend;
	}
	
	public List<Object[]> countSuperVoucher()
	{
		String sql = "select  money_n, count(id)from t_voucher where status_n = 0 and type_n=2 group by money_n order by money_n";
		List<Object[]> bagsend = hdDao.findBySql(sql);
		return  bagsend;
	}
	
}
