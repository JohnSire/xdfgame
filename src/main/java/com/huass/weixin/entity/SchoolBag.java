package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SCHOOL_BAG")
public class SchoolBag
{	
	private String id;
	
	private String userId;
	
	private String headImage;
	
	private String msgV;
	
	private String bgtype;
	
	private Integer numN;
	
	private String schoolId;
	
	private String dateN;
	
	private String moban;
	

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
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "HEAD_IMAGE")
	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	@Column(name = "MSG_V")
	public String getMsgV() {
		return msgV;
	}

	public void setMsgV(String msgV) {
		this.msgV = msgV;
	}

	@Column(name = "BGTYPE")
	public String getBgtype() {
		return bgtype;
	}

	public void setBgtype(String bgtype) {
		this.bgtype = bgtype;
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

	@Column(name = "DATE_N")
	public String getDateN() {
		return dateN;
	}

	public void setDateN(String dateN) {
		this.dateN = dateN;
	}
	
	@Column(name = "MOBAN")
	public String getMoban() {
		return moban;
	}

	public void setMoban(String moban) {
		this.moban = moban;
	}
}
