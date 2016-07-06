package com.funo.appmarket.bean;

public class NavItem {

	private String name;
	private String image;
	private String category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public NavItem(String name, String image, String category) {
		this.name = name;
		this.image = image;
		this.category = category;
	}

}
