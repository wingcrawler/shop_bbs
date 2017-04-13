package com.sqe.shop.model;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productDescripton;
	private String productStatus;
	private Integer id;
	private Integer productCount;
	private Integer productView;
	private Integer productRank;
	private String productName;
	
	public String getProductDescripton() {
		return productDescripton;
	}
	public void setProductDescripton(String productDescripton) {
		this.productDescripton = productDescripton;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
}