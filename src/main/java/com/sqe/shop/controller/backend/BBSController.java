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
@RequestMapping("/backend/bbs")
public class BBSController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BBSController.class);
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/bbs/list");
		return model;
	}
	
	@RequestMapping(value="/plate", method = RequestMethod.GET)
	public ModelAndView plate() {
		ModelAndView model = new ModelAndView("backend/bbs/plate");
		return model;
	}
	
	@RequestMapping(value="/topic", method = RequestMethod.GET)
	public ModelAndView topic() {
		ModelAndView model = new ModelAndView("backend/bbs/topic");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		return resMap;
	}
	
}
