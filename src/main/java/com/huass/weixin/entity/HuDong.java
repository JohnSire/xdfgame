package com.huass.weixin.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_HUDONG")
public class HuDong
{	
	public static final String PK_HD_ID = "1";
	
	
	public static Map<String, Integer> SHARE_MAP = new HashMap<String, Integer>();
	
	private String id;
	
	private Long hdNums;
	
	private Long shareNums;
	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 64)
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Column(name = "HD_NUMS")
	public Long getHdNums()
	{
		return hdNums;
	}

	public void setHdNums(Long hdNums)
	{
		this.hdNums = hdNums;
	}

	@Column(name = "SHARE_NUMS")
	public Long getShareNums()
	{
		return shareNums;
	}

	public void setShareNums(Long shareNums)
	{
		this.shareNums = shareNums;
	}
}
