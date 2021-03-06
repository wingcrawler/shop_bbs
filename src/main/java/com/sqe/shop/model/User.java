package com.sqe.shop.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sqe.shop.common.BaseModel;

public class User extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userAddress;
	@NotEmpty(message = "{items.password.isNull}")
	@Size(min = 6, max = 20, message = "{items.password.length}")
	private String password;
	@Size(min = 3, max = 20, message = "{items.password.length}")
	@NotEmpty(message = "{items.password.isNull}")
	private String repassword;
	private Integer userStatus;
	private String userImage;
	private Date createTime;
	private String userPhone;
	private String userMail;
	private Long id;
	/**
	 * 用户角色
	 */
	private Long userRole;
	private String userIntroduce;
	@Size(min = 5, max = 20, message = "{items.username.length}")
	@Pattern(regexp = "[0-9a-zA-Z\u4E00-\u9FA5]+", message = "{items.username.pattern}")
	private String username;
	private String userQq;
	
	private Date loginTime;

	private String validataCode;

	private Date upDateTime;

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserRole() {
		return userRole;
	}

	public void setUserRole(Long userRole) {
		this.userRole = userRole;
	}

	public String getUserIntroduce() {
		return userIntroduce;
	}

	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = userIntroduce;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getUserQq() {
		return userQq;
	}

	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}

	public String getValidataCode() {
		return validataCode;
	}

	public void setValidataCode(String validataCode) {
		this.validataCode = validataCode;
	}

	public Date getUpDateTime() {
		return upDateTime;
	}

	public void setUpDateTime(Date upDateTime) {
		this.upDateTime = upDateTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	

}