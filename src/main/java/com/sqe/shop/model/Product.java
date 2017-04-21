package com.sqe.shop.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productDescripton;
	private Integer productUrlClick;
	private Integer productStatus;
	private Long id;
	private String productUrl;
	private Integer productCount;
	private Integer productView;
	private Integer productRank;
	private Long productTypeId;
	private String productName;
	
	public String getProductDescripton() {
		return productDescripton;
	}
	public void setProductDescripton(String productDescripton) {
		this.productDescripton = productDescripton;
	}
	public Integer getProductUrlClick() {
		return productUrlClick;
	}
	public void setProductUrlClick(Integer productUrlClick) {
		this.productUrlClick = productUrlClick;
	}
	public Integer getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
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
	
	
	
}