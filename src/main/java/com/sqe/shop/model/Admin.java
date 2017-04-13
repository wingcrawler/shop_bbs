package com.sqe.shop.model;

import java.io.Serializable;

public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	private String password;
	private Integer id;
	private String user;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
}