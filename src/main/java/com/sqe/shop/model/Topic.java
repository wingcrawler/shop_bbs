package com.sqe.shop.model;

import java.io.Serializable;

import com.sqe.shop.common.BaseModel;

public class Topic extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer topicStatus;
	private Long sectionId;
	private Long id;
	private String topicDescription;
	private String topicTitle;
	
	public Integer getTopicStatus() {
		return topicStatus;
	}
	public void setTopicStatus(Integer topicStatus) {
		this.topicStatus = topicStatus;
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
	public String getTopicDescription() {
		return topicDescription;
	}
	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	
	
	
}