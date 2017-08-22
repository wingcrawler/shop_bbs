package com.sqe.shop.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.service.biz.BizBBSService;

@RequestMapping("/h5")
public class MobileBBSController extends BaseFrontController {

	@Autowired
	private BizBBSService bizBBSService;

	@ResponseBody
	@RequestMapping("/bbs/index")
	public Map<String, Object> index(ModelAndView model) {
		return bizBBSService.getHomeIndexData();
	}

}
