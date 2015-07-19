package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 红包
 * 
 * @author admin
 *
 */
@Entity
@Table(name="T_URL")
public class Url
{
	
	private String id;
	
	private String urlN;

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

	@Column(name = "URL_N", length = 500)
	public String getUrlN() {
		return urlN;
	}

	public void setUrlN(String urlN) {
		this.urlN = urlN;
	}
}
