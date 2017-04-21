package com.sqe.shop.model;

import java.io.Serializable;

public class Topic implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String topicDescription;
	private String topicTitle;
	
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