package com.sqe.shop.controller.backend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseController;
import com.sqe.shop.model.User;
import com.sqe.shop.service.UserService;
import com.sqe.shop.util.MD5Util;

@Controller
@RequestMapping("/back")
public class LoginController extends BaseController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		if(this.getCurrentUser()==null){
			modelAndView.setViewName("backend/login");
			return modelAndView;
		} 
		String url = getSavedRequestUrl();
		if(StringUtils.isBlank(url)){
			modelAndView.setViewName("backend/login");
			return modelAndView;
		}
		if(url.indexOf("login")<=0){
			return new ModelAndView("redirect:"+url);
		} else {
			modelAndView.setViewName("backend/login");
			return modelAndView;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/doLogin", method=RequestMethod.POST)
	public Map<String, Object> doLogin(HttpServletRequest request, User user) {
		user.setUserRole(3L);
		Map<String, Object> resMap = logins(user);
		return resMap;
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("backend/login");
		this.logout();
		return modelAndView;
	}

	public Map<String, Object> logins(User user) {
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
		User u = userService.findUserByUsernameAndPassword(user);
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
		resMap.put("url", "/backend/index");
		return resMap;
	}
	
}
