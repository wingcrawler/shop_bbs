package com.sqe.shop.dto;

import com.sqe.shop.model.Image;
import com.sqe.shop.model.Shop;
import com.sqe.shop.util.PageUtil;

/**
 * 
 * @author Administrator
 *
 */
public class shopDto {

	private Shop shop;

	private PageUtil<Image> images;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public PageUtil<Image> getImages() {
		return images;
	}

	public void setImages(PageUtil<Image> images) {
		this.images = images;
	}

	

}
