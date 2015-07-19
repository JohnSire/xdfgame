package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_VOUCHER")
public class Voucher
{
	public static final Integer TYPE_COMMON = 0;
	
	public static final Integer TYPE_TEACHER = 2;
	
	public static final Integer TYPE_SUPER = 3;
	
	private String id;
	
	private String codeV;
	
	private Integer moneyN;
	
	private Integer statusN;
	
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

	@Column(name = "CODE_V", length = 100)
	public String getCodeV()
	{
		return codeV;
	}

	public void setCodeV(String codeV)
	{
		this.codeV = codeV;
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

	@Column(name = "STATUS_N")
	public Integer getStatusN()
	{
		return statusN;
	}

	public void setStatusN(Integer statusN)
	{
		this.statusN = statusN;
	}

	@Column(name = "TYPE_N")
	public Integer getTypeN()
	{
		return typeN;
	}

	public void setTypeN(Integer typeN)
	{
		this.typeN = typeN;
	}
}
