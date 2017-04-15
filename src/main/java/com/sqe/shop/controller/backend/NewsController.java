package com.sqe.shop.controller.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseController;

@Controller
@RequestMapping("/backend/news")
public class NewsController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/news/list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView model = new ModelAndView("backend/news/edit");
		return model;
	}

}
