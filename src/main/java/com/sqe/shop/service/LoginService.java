/*package com.sqe.shop.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import com.sqe.shop.model.User;
import com.sqe.shop.util.MD5Util;

@Component
public class LoginService extends AdapterService implements BaseService{

	public Map<String, Object> login(User user) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(user.getUsername())){
			return responseError(-1, bundle.getString("error_empty_username"));
		} 
		if(StringUtils.isBlank(user.getPassword())){
			return responseError(-1, bundle.getString("error_empty_pwd"));
		} else {
			MD5Util md5 = new MD5Util(MD5Util.SALT, "MD5");
			user.setPassword(md5.encode(user.getPassword()));
		}
		User u = userService.findUserByUsernameAndPassword(user);
		if (u != null) {
			u.setPassword(null);
			Subject currentUser = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(true);	
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				return responseError(-1, bundle.getString("error_empty_pwd"));
			}
			if (currentUser.isAuthenticated()) {
				currentUser.getSession().setAttribute("userInfo", u);
			} else {
				return responseError(-1, bundle.getString("error_unknow"));
			}
		} else {
			return responseError(-1, bundle.getString("error_username_pwd"));
		}
		return responseOK("");
	}
	
}
*/