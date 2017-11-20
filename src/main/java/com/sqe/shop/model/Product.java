package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sqe.shop.common.BaseModel;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Product extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productDescripton;
	private String productEnName;
	private String productDim;
	/**
	 * 产品状态
	 */
	private Integer productStatus;
	/**
	 * 产品数量
	 */
	private Integer productCount;
	private Integer productView;
	private Integer productRank;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 父分类
	 */
	private Long productTypeId;
	private String productName;
	/**
	 * 子分类
	 */
	private Long productSubtypeId;
	private Date createTime;
	private Integer productUrlClick;
	private String productEnDescription;
	private String productEnDim;
	private Long shopId;
	private Long id;
	private String productUrl;
	private Double productPrice;
	private String productTag;
	
	public String getProductDescripton() {
		return productDescripton;
	}
	public void setProductDescripton(String productDescripton) {
		this.productDescripton = productDescripton;
	}
	public String getProductEnName() {
		return productEnName;
	}
	public void setProductEnName(String productEnName) {
		this.productEnName = productEnName;
	}
	public String getProductDim() {
		return productDim;
	}
	public void setProductDim(String productDim) {
		this.productDim = productDim;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Long getProductSubtypeId() {
		return productSubtypeId;
	}
	public void setProductSubtypeId(Long productSubtypeId) {
		this.productSubtypeId = productSubtypeId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getProductUrlClick() {
		return productUrlClick;
	}
	public void setProductUrlClick(Integer productUrlClick) {
		this.productUrlClick = productUrlClick;
	}
	public String getProductEnDescription() {
		return productEnDescription;
	}
	public void setProductEnDescription(String productEnDescription) {
		this.productEnDescription = productEnDescription;
	}
	public String getProductEnDim() {
		return productEnDim;
	}
	public void setProductEnDim(String productEnDim) {
		this.productEnDim = productEnDim;
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
	public String getProductTag() {
		return productTag;
	}
	public void setProductTag(String productTag) {
		this.productTag = productTag;
	}
	
	
	
}