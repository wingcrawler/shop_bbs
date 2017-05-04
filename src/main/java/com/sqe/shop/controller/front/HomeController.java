package com.sqe.shop.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseFrontController;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/")
public class HomeController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private AdvertisementService advertisementService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("shop/index");
		
		//轮播图
		Advertisement advertisement = new Advertisement();
		advertisement.setType(0);
		PageUtil<Advertisement> page = advertisementService.getBeanListByParm(advertisement, 1, -1);
		model.addObject("adList", page.getList());
		
		return model;
	}
	
	@RequestMapping(value="single", method = RequestMethod.GET)
	public ModelAndView single(ModelAndView model) {
		model = new ModelAndView("shop/single");
		return model;
	}

}
