package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long receiveId;

	private Long postId;

	private String messageContext;

	private Integer messageStatus;

	private Date createTime;

	private Long productId;

	private Long messageId;

	private Boolean readFlag;

	private Boolean receiveDelFlag;

	private Boolean postDelFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Long receiveId) {
		this.receiveId = receiveId;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getMessageContext() {
		return messageContext;
	}

	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext == null ? null : messageContext.trim();
	}

	public Integer getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Boolean getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Boolean readFlag) {
		this.readFlag = readFlag;
	}

	public Boolean getReceiveDelFlag() {
		return receiveDelFlag;
	}

	public void setReceiveDelFlag(Boolean receiveDelFlag) {
		this.receiveDelFlag = receiveDelFlag;
	}

	public Boolean getPostDelFlag() {
		return postDelFlag;
	}

	public void setPostDelFlag(Boolean postDelFlag) {
		this.postDelFlag = postDelFlag;
	}
}