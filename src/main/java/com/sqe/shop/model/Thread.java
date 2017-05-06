package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

public class Thread implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long topicId;
	private Integer threadStatus;
	private String threadTitle;
	private Date threadTime;
	private Integer threadType;
	private Long sectionId;
	private Long id;
	private String threadContext;
	private Long userId;
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public Integer getThreadStatus() {
		return threadStatus;
	}
	public void setThreadStatus(Integer threadStatus) {
		this.threadStatus = threadStatus;
	}
	public String getThreadTitle() {
		return threadTitle;
	}
	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}
	public Date getThreadTime() {
		return threadTime;
	}
	public void setThreadTime(Date threadTime) {
		this.threadTime = threadTime;
	}
	public Integer getThreadType() {
		return threadType;
	}
	public void setThreadType(Integer threadType) {
		this.threadType = threadType;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getThreadContext() {
		return threadContext;
	}
	public void setThreadContext(String threadContext) {
		this.threadContext = threadContext;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}