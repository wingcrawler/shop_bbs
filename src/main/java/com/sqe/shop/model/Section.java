package com.sqe.shop.model;

import java.io.Serializable;

public class Section implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer sectionStatus;
	private Long id;
	private Long sectionParentId;
	private Integer sectionType;
	private String sectionTitle;
	
	public Integer getSectionStatus() {
		return sectionStatus;
	}
	public void setSectionStatus(Integer sectionStatus) {
		this.sectionStatus = sectionStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSectionParentId() {
		return sectionParentId;
	}
	public void setSectionParentId(Long sectionParentId) {
		this.sectionParentId = sectionParentId;
	}
	public Integer getSectionType() {
		return sectionType;
	}
	public void setSectionType(Integer sectionType) {
		this.sectionType = sectionType;
	}
	public String getSectionTitle() {
		return sectionTitle;
	}
	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}
	
	
	
}