package com.sqe.shop.model;

import java.io.Serializable;

public class ProductType implements Serializable {

	private static final long serialVersionUID = 1L;

	private String typeNameCh;
	private String typeName;
	private Integer typeRank;
	private String typeDescriptionCh;
	private String typeDescription;
	private Long id;
	
	public String getTypeNameCh() {
		return typeNameCh;
	}
	public void setTypeNameCh(String typeNameCh) {
		this.typeNameCh = typeNameCh;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getTypeRank() {
		return typeRank;
	}
	public void setTypeRank(Integer typeRank) {
		this.typeRank = typeRank;
	}
	public String getTypeDescriptionCh() {
		return typeDescriptionCh;
	}
	public void setTypeDescriptionCh(String typeDescriptionCh) {
		this.typeDescriptionCh = typeDescriptionCh;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}