package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long replyToFloor;
	private Long threadId;
	private String postContext;
	private Long replyToUserId;
	private Integer postStatus;
	private Date postDate;
	private Long postId;
	private Long id;
	private Long floor;
	private Long userId;
	
	public Long getReplyToFloor() {
		return replyToFloor;
	}
	public void setReplyToFloor(Long replyToFloor) {
		this.replyToFloor = replyToFloor;
	}
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
	public Long getReplyToUserId() {
		return replyToUserId;
	}
	public void setReplyToUserId(Long replyToUserId) {
		this.replyToUserId = replyToUserId;
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
	public Long getFloor() {
		return floor;
	}
	public void setFloor(Long floor) {
		this.floor = floor;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}