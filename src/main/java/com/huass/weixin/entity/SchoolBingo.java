package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SCHOOL_BINGO")
public class SchoolBingo
{	
	private String id;
	
	private String schoolId;
	
	private String schoolbagId;
	
	private Integer moneyN;
	
	private Integer numN;
	
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

	@Column(name = "SCHOOL_ID", length = 64)
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "SCHOOL_BAG_ID", length = 64)
	public String getSchoolbagId() {
		return schoolbagId;
	}

	public void setSchoolbagId(String schoolbagId) {
		this.schoolbagId = schoolbagId;
	}

	@Column(name = "MONEY_N")
	public Integer getMoneyN() {
		return moneyN;
	}

	public void setMoneyN(Integer moneyN) {
		this.moneyN = moneyN;
	}

	@Column(name = "NUM_N")
	public Integer getNumN() {
		return numN;
	}

	public void setNumN(Integer numN) {
		this.numN = numN;
	}
}
