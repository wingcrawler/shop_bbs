package com.sqe.shop.model;

import java.io.Serializable;

import com.sqe.shop.common.BaseModel;

public class Product extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productDescripton;
	private Integer productStatus;
	private Integer productCount;
	private Integer productView;
	private Integer productRank;
	private Long productTypeId;
	private String productName;
	private Integer productUrlClick;
	private Long shopId;
	private Long id;
	private String productUrl;
	private Double productPrice;
	
	public String getProductDescripton() {
		return productDescripton;
	}
	public void setProductDescripton(String productDescripton) {
		this.productDescripton = productDescripton;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public Integer getProductView() {
		return productView;
	}
	public void setProductView(Integer productView) {
		this.productView = productView;
	}
	public Integer getProductRank() {
		return productRank;
	}
	public void setProductRank(Integer productRank) {
		this.productRank = productRank;
	}
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductUrlClick() {
		return productUrlClick;
	}
	public void setProductUrlClick(Integer productUrlClick) {
		this.productUrlClick = productUrlClick;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductUrl() {
		return productUrl;
	}
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
}