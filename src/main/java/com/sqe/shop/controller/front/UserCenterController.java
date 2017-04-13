package com.sqe.shop.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.service.UserService;

@Controller
@RequestMapping("/user")
public class UserCenterController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserCenterController.class);
	
	@Autowired
	UserService userService;

	@RequestMapping(value="index/{userId}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable Long userId) {
		ModelAndView model = new ModelAndView();
		model = new ModelAndView("front/user/index");
		model.addObject("userId", userId);
		return model;
	}
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model = new ModelAndView("front/login");
		return model;
	}

}
