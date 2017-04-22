package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

import com.sqe.shop.common.BaseModel;

public class News extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer newsUp;
	private String newsTitle;
	private String newsContext;
	private Integer newsReaded;
	private Long id;
	private Date newsDate;
	private Integer newsType;
	
	public Integer getNewsUp() {
		return newsUp;
	}
	public void setNewsUp(Integer newsUp) {
		this.newsUp = newsUp;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContext() {
		return newsContext;
	}
	public void setNewsContext(String newsContext) {
		this.newsContext = newsContext;
	}
	public Integer getNewsReaded() {
		return newsReaded;
	}
	public void setNewsReaded(Integer newsReaded) {
		this.newsReaded = newsReaded;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}
	public Integer getNewsType() {
		return newsType;
	}
	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}
	
	
	
}