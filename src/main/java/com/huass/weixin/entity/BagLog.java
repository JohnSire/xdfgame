package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="T_BAGLOG")
public class BagLog
{
	private String id;
	
	private String bagId;
	
	private String userId;
	
	private Integer moneyN;
	
	private String contentV;
	
	private String dateD;
	
	private String voucherId;

	private Integer typeN;
	
	private String SchoolBagId;
	
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

	@Column(name = "BAG_ID", length = 64)
	public String getBagId()
	{
		return bagId;
	}

	public void setBagId(String bagId)
	{
		this.bagId = bagId;
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

	@Column(name = "MONEY_D")
	public Integer getMoneyN()
	{
		return moneyN;
	}

	public void setMoneyN(Integer moneyN)
	{
		this.moneyN = moneyN;
	}

	@Column(name = "CONTENT_V", length = 100)
	public String getContentV()
	{
		return contentV;
	}

	public void setContentV(String contentV)
	{
		this.contentV = contentV;
	}

	@Column(name = "DATE_D")
	public String getDateD()
	{
		return dateD;
	}

	public void setDateD(String dateD)
	{
		this.dateD = dateD;
	}

	@Column(name = "VOUCHER_ID", length = 100)
	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	
	@Column(name="TYPE_N")
	public Integer getTypeN()
	{
		return typeN;
	}

	public void setTypeN(Integer typeN)
	{
		this.typeN = typeN;
	}
	
	@Column(name = "SCHOOL_BAG_ID", length = 64)
	public String getSchoolBagId() {
		return SchoolBagId;
	}

	public void setSchoolBagId(String schoolBagId) {
		SchoolBagId = schoolBagId;
	}

}
