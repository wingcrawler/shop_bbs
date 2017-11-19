package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sqe.shop.common.BaseModel;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Thread extends BaseModel implements Serializable {

	private static  final long serialVersionUID = 1L;

	private Date threadTime;
	private Long sectionId;
	private String threadContext;
	private Long userId;
	private Long threadView;
	private Long threadUp;
	private Long topicId;
	private Integer threadStatus;
	private Long threadIdentify;
	private String threadTitle;
	private Integer threadType;
	private Long id;
	
	public Date getThreadTime() {
		return threadTime;
	}
	public void setThreadTime(Date threadTime) {
		this.threadTime = threadTime;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
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
	public Long getThreadView() {
		return threadView;
	}
	public void setThreadView(Long threadView) {
		this.threadView = threadView;
	}
	public Long getThreadUp() {
		return threadUp;
	}
	public void setThreadUp(Long threadUp) {
		this.threadUp = threadUp;
	}
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
	public Long getThreadIdentify() {
		return threadIdentify;
	}
	public void setThreadIdentify(Long threadIdentify) {
		this.threadIdentify = threadIdentify;
	}
	public String getThreadTitle() {
		return threadTitle;
	}
	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}
	public Integer getThreadType() {
		return threadType;
	}
	public void setThreadType(Integer threadType) {
		this.threadType = threadType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}