package com.sqe.shop.controller.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseController;

@Controller
@RequestMapping("/backend")
public class BackendController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BackendController.class);
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model = new ModelAndView("backend/index");
		return model;
	}

}
