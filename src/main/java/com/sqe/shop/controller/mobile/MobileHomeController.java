package com.sqe.shop.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.service.biz.BizHomeService;

@Controller
@RequestMapping("/h5")
public class MobileHomeController extends BaseFrontController {
	
	@Autowired
	private BizHomeService bizHomeService;

	@ResponseBody
	@RequestMapping("/index")
	public Map<String, Object> index(ModelAndView model) {
		
		return bizHomeService.getHomeIndexData(Constants.AD_DEVICE_APP);
	}
	
}
