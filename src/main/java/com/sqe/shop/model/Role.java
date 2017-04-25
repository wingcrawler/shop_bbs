package com.sqe.shop.model;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleName;
	private Long id;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}