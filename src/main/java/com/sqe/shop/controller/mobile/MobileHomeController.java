package com.sqe.shop.controller.mobile;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.controller.base.BaseFrontController;

@Controller
@RequestMapping("/h5")
public class MobileHomeController extends BaseFrontController {

	@ResponseBody
	@RequestMapping("/index")
	public Map<String, Object> index(ModelAndView model) {
		return this.responseOK1("");
	}
	
}
