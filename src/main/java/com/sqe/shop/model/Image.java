package com.sqe.shop.model;

import java.io.Serializable;

public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long newsId;
	private String imageLocalurl;
	private Long productId;
	private Long psotId;
	private String imagePath;
	private Long shopId;
	private Long id;
	private String imageTitle;
	
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public String getImageLocalurl() {
		return imageLocalurl;
	}
	public void setImageLocalurl(String imageLocalurl) {
		this.imageLocalurl = imageLocalurl;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getPsotId() {
		return psotId;
	}
	public void setPsotId(Long psotId) {
		this.psotId = psotId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	public String getImageTitle() {
		return imageTitle;
	}
	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}
	
	
	
}