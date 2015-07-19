package com.huass.weixin.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SUGGEST")
public class Suggest
{
	private String id;
	
	private String userId;
	
	private String contentV;
	
	private String dateD;
    
    private String dateStrD;
    
    private String userName;
    
	public Suggest() {
		super();
	}
	
	public Suggest(String contentV, String dateD, String userName) {
		super();
		
		this.contentV = contentV;
		this.dateD = dateD;
		this.userName = userName;
	}

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

	@Column(name = "CONTENT_V", length = 300)
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

	@Column(name = "USER_ID", length = 300)
	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	@Transient
	public String getDateStrD() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("2014-10-25 18:10:10");
		String date = formatter.format(calendar.getTime());
		return date;
	}

	public void setDateStrD(String dateStrD) {
		this.dateStrD = dateStrD;
	}
	
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
