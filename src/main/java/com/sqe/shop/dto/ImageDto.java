package com.sqe.shop.dto;

import com.sqe.shop.model.Image;

public class ImageDto extends Image {

	private String Base64data;

	public String getBase64data() {
		return Base64data;
	}

	public void setBase64data(String base64data) {
		Base64data = base64data;
	}
	

}
