package com.huass.weixin.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="T_BINGO")
public class Bingo
{
	
	public static List<Integer> LIST_COMMON;
	
	public static List<Integer> LIST_TEACHER;
	
	public static List<Integer> LIST_SUPER;
	
	
	public static Map<String, List<Integer>> BINGO_MAP = new HashMap<String, List<Integer>>();
	
	
	public static Map<String, Long> BING_TIMEOUT = new HashMap<String, Long>();
	
	public static final Integer TYPE_COMMON = 0;
	
	public static final Integer TYPE_TEACHER = 2;
	
	public static final Integer TYPE_SUPER = 3;
	
	private String id;
	
	private Integer moneyN;
	
	private Integer numN;
	
	private String schoolId;
	
	private Integer percentN;
	
	private Integer typeN;



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

	@Column(name = "MONEY_N")
	public Integer getMoneyN()
	{
		return moneyN;
	}

	public void setMoneyN(Integer moneyN)
	{
		this.moneyN = moneyN;
	}

	@Column(name = "NUM_N")
	public Integer getNumN() {
		return numN;
	}

	public void setNumN(Integer numN) {
		this.numN = numN;
	}

	@Column(name = "SCHOOL_ID", length = 64)
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "PRECENT_N")
	public Integer getPercentN() {
		return percentN;
	}

	public void setPercentN(Integer percentN) {
		this.percentN = percentN;
	}
	
	@Column(name = "TYPE_N")
	public Integer getTypeN() {
		return typeN;
	}
	public void setTypeN(Integer typeN) {
		this.typeN = typeN;
	}
}
