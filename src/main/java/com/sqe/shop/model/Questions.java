package com.sqe.shop.model;

import java.io.Serializable;

public class Questions implements Serializable {

	private static final long serialVersionUID = 1L;

	private String question;
	private Long id;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}