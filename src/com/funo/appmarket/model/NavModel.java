package com.funo.appmarket.model;

import java.io.Serializable;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "NavItem")
public class NavModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id", isId = true, autoGen = true)
	private int id;
	
	@Column(name = "parentId")
	private int parentId;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "label")
	private String label;
	
	@Column(name = "logo")
	private String logo;
	
	@Column(name = "updateTime")
	private String updateTime;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

}
