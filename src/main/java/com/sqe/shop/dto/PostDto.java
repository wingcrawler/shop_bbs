package com.sqe.shop.dto;

import java.util.Date;

import com.sqe.shop.model.Post;

public class PostDto extends Post {

	private Date createBeginDate;

	private Date postEndDate;

	public Date getCreateBeginDate() {
		return createBeginDate;
	}

	public void setCreateBeginDate(Date createBeginDate) {
		this.createBeginDate = createBeginDate;
	}

	public Date getPostEndDate() {
		return postEndDate;
	}

	public void setPostEndDate(Date postEndDate) {
		this.postEndDate = postEndDate;
	}

}
