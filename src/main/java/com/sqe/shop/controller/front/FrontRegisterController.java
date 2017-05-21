package com.sqe.shop.controller.front;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.User;
import com.sqe.shop.service.RegisterService;
import com.sqe.shop.service.UserService;

@Controller
@RequestMapping("/user")
public class FrontRegisterController extends BaseFrontController{
	@Autowired
	RegisterService registerservice;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("shop/register");
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public Map<String, Object> doLogin(HttpServletRequest request, User user) {
		Map<String, Object> resMap = registerservice.register(user);
		return resMap;
	}

}
