package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="T_BAG")
public class Bag
{
	
	public static final Integer da_bag_type = 0;
	
	public static final Integer xiao_bag_type = 1;
	
	private String id;
	
	private String userId;
	
	private Integer numN = 5;
	
	private String SchoolBagId;
	
	private Integer type;
	
	private Integer yesorno;

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

	@Column(name = "USER_ID", length = 64)
	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	@Column(name = "NUM_N")
	public Integer getNumN()
	{
		return numN;
	}

	public void setNumN(Integer numN)
	{
		this.numN = numN;
	}

	@Column(name = "SCHOOL_BAG_ID", length = 64)
	public String getSchoolBagId() {
		return SchoolBagId;
	}

	public void setSchoolBagId(String schoolBagId) {
		SchoolBagId = schoolBagId;
	}

	@Column(name = "TYPE_N")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "YESORNO")
	public Integer getYesorno() {
		return yesorno;
	}

	public void setYesorno(Integer yesorno) {
		this.yesorno = yesorno;
	}


}
