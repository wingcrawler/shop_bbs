package com.sqe.shop.model;

import java.io.Serializable;

public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String permissionName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	
	
	
}