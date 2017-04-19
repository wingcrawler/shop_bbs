package com.sqe.shop.controller.backend;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseController;

@Controller
@RequestMapping("/backend/product")
public class ProductController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/product/list");
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView model = new ModelAndView("backend/product/edit");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doEdit", method = RequestMethod.GET)
	public Map<String, Object> doEdit() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		return resMap;
	}

}
