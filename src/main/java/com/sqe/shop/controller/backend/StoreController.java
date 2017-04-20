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
@RequestMapping("/backend/store")
public class StoreController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/store/list");
		return model;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView detail() {
		ModelAndView model = new ModelAndView("backend/store/store_detail");
		return model;
	}
	
	@RequestMapping(value="/msg", method = RequestMethod.GET)
	public ModelAndView msg() {
		ModelAndView model = new ModelAndView("backend/store/store_msg");
		return model;
	}
	
	@RequestMapping(value="/productVerify", method = RequestMethod.GET)
	public ModelAndView productVerify() {
		ModelAndView model = new ModelAndView("backend/store/store_product_verify");
		return model;
	}
	
	@RequestMapping(value="/storeVerify", method = RequestMethod.GET)
	public ModelAndView storeVerify() {
		ModelAndView model = new ModelAndView("backend/store/store_erify");
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList() {
		Map<String, Object> resMap = new HashMap<String, Object>();
		return resMap;
	}
	
}
