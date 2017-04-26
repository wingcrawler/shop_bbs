package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

import com.sqe.shop.common.BaseModel;

public class Shop extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer shopStatus;
	private Date createTime;
	private String shopQualifies;
	private String shopDescription;
	private Integer shopLevel;
	private Integer shopRank;
	private String shopTitle;
	private Long id;
	private String shopLicenes;
	private Long userId;
	
	public Integer getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getShopQualifies() {
		return shopQualifies;
	}
	public void setShopQualifies(String shopQualifies) {
		this.shopQualifies = shopQualifies;
	}
	public String getShopDescription() {
		return shopDescription;
	}
	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}
	public Integer getShopLevel() {
		return shopLevel;
	}
	public void setShopLevel(Integer shopLevel) {
		this.shopLevel = shopLevel;
	}
	public Integer getShopRank() {
		return shopRank;
	}
	public void setShopRank(Integer shopRank) {
		this.shopRank = shopRank;
	}
	public String getShopTitle() {
		return shopTitle;
	}
	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShopLicenes() {
		return shopLicenes;
	}
	public void setShopLicenes(String shopLicenes) {
		this.shopLicenes = shopLicenes;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}