package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_TEMP")
public class Temp 
{
	private String id;
	
	private String bagId;
	
	private String userId;
	
	private Integer typeN;
	
	private String voucherId;
	
	private Integer moneyN;
	
	private String openId; 
	
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 64)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "BAG_ID", length = 64)
	public String getBagId() {
		return bagId;
	}

	public void setBagId(String bagId) {
		this.bagId = bagId;
	}

	@Column(name = "USER_ID", length = 64)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "TYPE_N")
	public Integer getTypeN() {
		return typeN;
	}

	public void setTypeN(Integer typeN) {
		this.typeN = typeN;
	}

	@Column(name = "MONEY_D")
	public Integer getMoneyN() {
		return moneyN;
	}

	public void setMoneyN(Integer moneyN) {
		this.moneyN = moneyN;
	}
	@Column(name = "VOUCHER_ID")
	public String getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	@Column(name = "OPENID", length = 64)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
