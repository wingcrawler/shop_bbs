package com.sqe.shop.model;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer messageStatus;
	private Long postId;
	private Long id;
	private String messageContext;
	private Long receiveId;
	
	public Integer getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(Integer messageStatus) {
		this.messageStatus = messageStatus;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessageContext() {
		return messageContext;
	}
	public void setMessageContext(String messageContext) {
		this.messageContext = messageContext;
	}
	public Long getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(Long receiveId) {
		this.receiveId = receiveId;
	}
	
	
	
}