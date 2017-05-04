package com.sqe.shop.controller.backend;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseBackendController;
import com.sqe.shop.model.User;
import com.sqe.shop.service.LoginService;
import com.sqe.shop.service.UserService;

@Controller
@RequestMapping("/back")
public class LoginController extends BaseBackendController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;

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
		Map<String, Object> resMap = loginService.login(user);
		return resMap;
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("backend/login");
		this.logout();
		return modelAndView;
	}
	
}
