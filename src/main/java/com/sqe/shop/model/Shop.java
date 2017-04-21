package com.sqe.shop.model;

import java.io.Serializable;

public class Shop implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer shopStatus;
	private String shopQualifies;
	private String shopDescription;
	private Integer shopRank;
	private String shopTitle;
	private Long id;
	private String shopLicenes;
	
	public Integer getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(Integer shopStatus) {
		this.shopStatus = shopStatus;
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
	
	
	
}