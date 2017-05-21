package com.sqe.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.mapper.UserMapper;
import com.sqe.shop.model.User;
import com.sqe.shop.util.MD5Util;

@Component
public class RegisterService extends BaseCommon {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserService userService;

	public Map<String, Object> register(User user) {
		Map<String, Object> resMap = new HashMap<String, Object>();

		if (StringUtils.isBlank(user.getUsername())) {
			return responseError(-1, "error_empty_username");
		} else  if (user.getUsername().length() < 5 || user.getUsername().length() > 20) {
			return responseError(-1, "error_length_username");
		}
		
		User u=userService.findByName(user.getUsername());
		if (u != null) {
			return responseError(-1, "error_username_used");
		}
		if (StringUtils.isBlank(user.getPassword())) {
			return responseError(-1, "error_empty_pwd");
		}
		if (!user.getPassword().equals(user.getRepassword())) {
			return responseError(-1, "error_different_pwd");
		}
		if (StringUtils.isBlank(user.getUserMail())) {
			return responseError(-1, "error_empty_email");
		}
		if (!user.getUserMail().matches("\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}")) {
			return responseError(-1, "error_email_form");
		} else {
			MD5Util md5 = new MD5Util(MD5Util.SALT, "MD5");
			user.setPassword(md5.encode(user.getPassword()));
		}
        user.setCreateTime(new Date());
        user.setUserStatus(1);
        user.setUserRole(ROLE_BUY);
		int r = userMapper.insert(user);

		if (r == 0) {
			resMap.put("result",true);
			resMap.put("url", "/login");
		} else {
			resMap.put("result",false);
			resMap.put("url", "/register");
		}
		return resMap;
	}

}
