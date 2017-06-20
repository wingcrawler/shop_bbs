package com.sqe.shop.controller.front;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.User;
import com.sqe.shop.service.LoginService;
import com.sqe.shop.service.RegisterService;

@Controller
@RequestMapping("/user")
public class FrontRegisterController extends BaseFrontController {
	@Autowired
	private RegisterService registerservice;
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("shop/register");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public Map<String, Object> doRegister(HttpServletRequest request, @Validated User user,
			BindingResult bindingResult) {
		// 获取校验错误信息
		if (bindingResult.hasErrors()) {
			final Map<String, Object> resMap = new HashMap<String, Object>();
			resMap.put(Constants.ERROR_NO, -1);
			resMap.put(Constants.ERROR_INFO, bindingResult.getFieldErrors().get(0).getDefaultMessage());
			return resMap;
		} else {
			Map<String, Object> resMap = registerservice.register(user);
			return resMap;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		
		if(isLogin()){
			String url = getSavedRequestUrl();
			if(StringUtils.isBlank(url)){
				modelAndView.setViewName("shop/login");
				return modelAndView;
			}
			if(url.indexOf("login")<=0){
				return new ModelAndView("redirect:"+url);
			} else {
				modelAndView.setViewName("shop/login");
				return modelAndView;
			}
		}
		
		modelAndView.setViewName("shop/login");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public Map<String, Object> doLogin(HttpServletRequest request, User user) {
		Map<String, Object> resMap = loginService.login(user);
		if (resMap.get(Constants.ERROR_NO).equals(0)) {
			User userInfo = this.getCurrentUser();
			if (userInfo.getUserRole().equals(Constants.ROLE_ADMIN)) {
				this.logout();
				return responseError(-1, "error_twopwd_not_match");
			}
		}
		return resMap;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/shopIndex");
		this.logout();
		return modelAndView;
	}

}
