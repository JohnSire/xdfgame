package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SCHOOL")
public class School
{	
	private String id;
	
	private String nameV;
	
	private String cityV;
	
	private String Secret;
	
	public String urlN;
	
	public Integer HD_NUM=0;
	
	public Integer SHARE_NUM=0;
	
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

	@Column(name = "NAME_V")
	public String getNameV() {
		return nameV;
	}

	public void setNameV(String nameV) {
		this.nameV = nameV;
	}

	@Column(name = "CITY")
	public String getCityV() {
		return cityV;
	}

	public Integer getHD_NUM() {
		return HD_NUM;
	}

	public void setHD_NUM(Integer hD_NUM) {
		HD_NUM = hD_NUM;
	}

	public Integer getSHARE_NUM() {
		return SHARE_NUM;
	}

	public void setSHARE_NUM(Integer sHARE_NUM) {
		SHARE_NUM = sHARE_NUM;
	}

	public void setCityV(String cityV) {
		this.cityV = cityV;
	}

	@Column(name = "SECRET")
	public String getSecret() {
		return Secret;
	}

	public void setSecret(String secret) {
		Secret = secret;
	}

	@Column(name = "URL_N")
	public String getUrlN() {
		return urlN;
	}

	public void setUrlN(String urlN) {
		this.urlN = urlN;
	}

}
