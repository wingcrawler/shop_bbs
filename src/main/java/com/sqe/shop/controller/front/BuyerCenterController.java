package com.sqe.shop.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sqe.shop.service.UserService;

@Controller
@RequestMapping("/front/buy")
public class BuyerCenterController {
	
	private static final Logger logger = LoggerFactory.getLogger(BuyerCenterController.class);
	
	@Autowired
	UserService userService;

}
