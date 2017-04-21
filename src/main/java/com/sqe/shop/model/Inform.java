package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

public class Inform implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date infromDate;
	private String informRemark;
	private String informTitle;
	private String informContext;
	private Long userPostId;
	private Long userReportedId;
	private Integer infromStatus;
	private Long id;
	
	public Date getInfromDate() {
		return infromDate;
	}
	public void setInfromDate(Date infromDate) {
		this.infromDate = infromDate;
	}
	public String getInformRemark() {
		return informRemark;
	}
	public void setInformRemark(String informRemark) {
		this.informRemark = informRemark;
	}
	public String getInformTitle() {
		return informTitle;
	}
	public void setInformTitle(String informTitle) {
		this.informTitle = informTitle;
	}
	public String getInformContext() {
		return informContext;
	}
	public void setInformContext(String informContext) {
		this.informContext = informContext;
	}
	public Long getUserPostId() {
		return userPostId;
	}
	public void setUserPostId(Long userPostId) {
		this.userPostId = userPostId;
	}
	public Long getUserReportedId() {
		return userReportedId;
	}
	public void setUserReportedId(Long userReportedId) {
		this.userReportedId = userReportedId;
	}
	public Integer getInfromStatus() {
		return infromStatus;
	}
	public void setInfromStatus(Integer infromStatus) {
		this.infromStatus = infromStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}