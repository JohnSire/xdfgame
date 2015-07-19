package com.huass.weixin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "T_USER")
public class User implements java.io.Serializable
{
	
	public static final String STATUS_NORMAL = "0";
	
	
	public static final String STATUS_DELETE = "1";

	private String id;
	private String nameV;
	private String openId;
	private String headImage;
	private String lotteryDate;
	private Integer dateNum = 0;
	private String statusV = "0";
	private String city;
	private Integer shareNum = 0;
	private String shareDate;
	private String schoolId;
	private Integer psoiion;

	
	public User()
	{
	}

	
	public User(String nameV, String headImage, String lotteryDate,Integer dateNum,Integer shareNum,String shareDate)
	{
		this.nameV = nameV;
		this.headImage = headImage;
		this.lotteryDate = lotteryDate;
		this.dateNum = dateNum;
		this.shareNum = shareNum;
		this.shareDate = shareDate;
	}
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 64)
	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	@Column(name = "NAME_V", length = 100)
	public String getNameV()
	{
		return this.nameV;
	}

	public void setNameV(String nameV)
	{
		this.nameV = nameV;
	}
	
	@Column(name = "OPENID", length = 100)
	public String getOpenId()
	{
		return openId;
	}

	public void setOpenId(String openId)
	{
		this.openId = openId;
	}

	@Column(name = "HEAD_IMAGE", length = 200)
	public String getHeadImage()
	{
		return this.headImage;
	}

	public void setHeadImage(String headImage)
	{
		this.headImage = headImage;
	}

	@Column(name = "STATUS_V")
	public String getStatusV()
	{
		return statusV;
	}

	public void setStatusV(String statusV)
	{
		this.statusV = statusV;
	}

	@Column(name = "LOTTERY_DATE")
	public String getLotteryDate() {
		return lotteryDate;
	}

	public void setLotteryDate(String lotteryDate) {
		this.lotteryDate = lotteryDate;
	}

	@Column(name = "DATE_NUM")
	public Integer getDateNum() {
		return dateNum;
	}

	public void setDateNum(Integer dateNum) {
		this.dateNum = dateNum;
	}

	@Column(name="CITY", length = 50)
	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}
	
	@Column(name="SHARE_NUM")
	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}
	
	@Column(name="SHARE_DATE")
	public String getShareDate() {
		return shareDate;
	}

	public void setShareDate(String shareDate) {
		this.shareDate = shareDate;
	}
	
	@Column(name = "SCHOOL_ID", length = 64)
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	@Column(name="POSITION")
	public Integer getPsoiion() {
		return psoiion;
	}

	public void setPsoiion(Integer psoiion) {
		this.psoiion = psoiion;
	}

}
