package com.sqe.shop.service;

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
public class LoginService extends BaseCommon{
	
	@Autowired
	private UserMapper userMapper;

	public Map<String, Object> login(User user) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if(StringUtils.isBlank(user.getUsername())){
			return responseError(-1, "error_empty_username");
		} 
		if(StringUtils.isBlank(user.getPassword())){
			return responseError(-1, "error_empty_pwd");
		} else {
			MD5Util md5 = new MD5Util(MD5Util.SALT, "MD5");
			user.setPassword(md5.encode(user.getPassword()));
		}
		
		User u = userMapper.findUserByUsernameAndPassword(user);
		if (u != null) {
			u.setPassword(null);
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(true);	
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				return responseError(-1, "error_empty_pwd");
			}
			if (currentUser.isAuthenticated()) {
				currentUser.getSession().setAttribute("userInfo", u);
			} else {
				return responseError(-1, "error_unknow");
			}
		} else {
			return responseError(-1, "error_username_pwd");
		}
		
		resMap = responseOK1("");
		if(u.getUserRole().equals(3L)){
			resMap.put("url", "/backend/index");	
		} else {
			resMap.put("url", "/shopIndex");
		}
		return resMap;
	}

	public void autoLogin() {
		Subject subject = SecurityUtils.getSubject();
		 if(!subject.isAuthenticated() && subject.isRemembered()){
			String username = subject.getPrincipal().toString();
			User user = userMapper.findByName(username);
			if(user==null){
				return;
			}
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(true);	
			subject.login(token);
			subject.getSession().setAttribute("userInfo", user);
		 }
	}
	
}
