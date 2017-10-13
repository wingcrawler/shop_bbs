package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

public class Likes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7930089955978629867L;
	
	private Long threadId;
	private Long newsId;
	private Long productId;
	private Date createTime;
	private Long shopId;
	private Long id;
	private Long userId;
	
	public Long getThreadId() {
		return threadId;
	}
	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}