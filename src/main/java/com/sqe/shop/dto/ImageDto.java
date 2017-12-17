package com.sqe.shop.dto;

import java.sql.Date;

import com.sqe.shop.model.Image;

public class ImageDto extends Image {

	private String ImageName;

	private String ImageURL;	

	/**
	 * 上传用户登录账户
	 */
	private String UserName;

	/**
	 * 上传时间
	 */
	private Date UpLoadDate;

	/**
	 * 更新时间
	 */
	
	private Date UpdateDate;
	
	
	
	
	
	

}
