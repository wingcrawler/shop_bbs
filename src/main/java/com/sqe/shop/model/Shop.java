package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

import com.sqe.shop.common.BaseModel;

public class Shop extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer shopLevel;
	private Integer shopRank;
	private String shopLogoImg;
	private String shopLicenes;
	private String shopAddress;
	private Long userId;
	private String workTo;
	private String shopLicenesImg;
	private Integer shopStatus;
	private Date createTime;
	private String workFrom;
	private String shopQualifies;
	private String shopDescription;
	private String shopImg;
	private String shopTitle;
	private String shopPhone;
	private Long id;
	private String shopQq;
	private String shopLicenesDesc;
	
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
	public String getShopLicenes() {
		return shopLicenes;
	}
	public void setShopLicenes(String shopLicenes) {
		this.shopLicenes = shopLicenes;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getWorkTo() {
		return workTo;
	}
	public void setWorkTo(String workTo) {
		this.workTo = workTo;
	}
	public String getShopLicenesImg() {
		return shopLicenesImg;
	}
	public void setShopLicenesImg(String shopLicenesImg) {
		this.shopLicenesImg = shopLicenesImg;
	}
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
	public String getWorkFrom() {
		return workFrom;
	}
	public void setWorkFrom(String workFrom) {
		this.workFrom = workFrom;
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
	public String getShopImg() {
		return shopImg;
	}
	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}
	public String getShopTitle() {
		return shopTitle;
	}
	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShopQq() {
		return shopQq;
	}
	public void setShopQq(String shopQq) {
		this.shopQq = shopQq;
	}
	public String getShopLicenesDesc() {
		return shopLicenesDesc;
	}
	public void setShopLicenesDesc(String shopLicenesDesc) {
		this.shopLicenesDesc = shopLicenesDesc;
	}
	public String getShopLogoImg() {
		return shopLogoImg;
	}
	public void setShopLogoImg(String shopLogoImg) {
		this.shopLogoImg = shopLogoImg;
	}
	
	
	
}