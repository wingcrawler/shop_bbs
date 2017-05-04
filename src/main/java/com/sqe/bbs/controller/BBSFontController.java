package com.sqe.bbs.controller;

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
@RequestMapping("/bbs")
public class BBSFontController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BBSFontController.class);
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("bbs/index");
		String message = "这个是要传递的数据";
		model.addObject("message", message);
		return model;
	}
	
	@RequestMapping(value="/plate", method = RequestMethod.GET)
	public ModelAndView plate() {
		ModelAndView model = new ModelAndView("bbs/plate");
		return model;
	}
	
	@RequestMapping(value="/topic", method = RequestMethod.GET)
	public ModelAndView topic() {
		ModelAndView model = new ModelAndView("bbs/topic");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		return resMap;
	}
	
}
