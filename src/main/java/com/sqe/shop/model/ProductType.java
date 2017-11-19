package com.sqe.shop.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ProductType implements Serializable {

	private static final long serialVersionUID = 1L;

	private String typeNameCh;
	private String typeName;
	private Integer typeLevel;
	private Integer typeRank;
	private String typeDescriptionCh;
	private String typeDescription;
	private Long id;
	private Long parentId;
	
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
	public Integer getTypeLevel() {
		return typeLevel;
	}
	public void setTypeLevel(Integer typeLevel) {
		this.typeLevel = typeLevel;
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
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}