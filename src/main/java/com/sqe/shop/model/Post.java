package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long threadId;
	private String postContext;
	private Integer postStatus;
	private Date postDate;
	private Long id;
	private Long userId;
	
	public Long getThreadId() {
		return threadId;
	}
	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}
	public String getPostContext() {
		return postContext;
	}
	public void setPostContext(String postContext) {
		this.postContext = postContext;
	}
	public Integer getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(Integer postStatus) {
		this.postStatus = postStatus;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
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