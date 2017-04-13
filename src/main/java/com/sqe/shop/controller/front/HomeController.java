package com.sqe.shop.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.service.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model = new ModelAndView("front/index");
		model.addObject("productList", productService.getProductList());
		return model;
	}

}
