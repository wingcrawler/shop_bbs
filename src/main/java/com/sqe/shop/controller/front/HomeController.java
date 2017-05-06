package com.sqe.shop.controller.front;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseFrontController;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.model.Product;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.ImageService;
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
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("front/index");
		return model;
	}

	/**
	 * 商城首页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="shopIndex", method = RequestMethod.GET)
	public ModelAndView shopIndex(ModelAndView model) {
		//轮播图
		Advertisement advertisement = new Advertisement();
		advertisement.setType(0);
		PageUtil<Advertisement> adPage = advertisementService.getBeanListByParm(advertisement, 1, -1);
		model.addObject("adList", adPage.getList());
		
		//商品列表
		Product product = new Product();
		product.setProductStatus(1);
		PageUtil<Map<String, Object>> hotProductPage = productService.getHotProductList(product, 0, 4); 
		model.addObject("hotProductList", hotProductPage.getList());
		
		model.setViewName("shop/index");
		return model;
	}
	
	
	/**
	 * 关于我们
	 * @param model
	 * @param productId
	 * @return
	 */
	@RequestMapping(value="about", method = RequestMethod.GET)
	public ModelAndView about(ModelAndView model) {
		model.setViewName("shop/about");
		return model;
	}
	/**
	 * 帮助
	 * @param model
	 * @return
	 */
	@RequestMapping(value="help", method = RequestMethod.GET)
	public ModelAndView help(ModelAndView model) {
		model.setViewName("shop/help");
		return model;
	}
	/**
	 * 404页面
	 */
	@RequestMapping(value="404", method = RequestMethod.GET)
	public ModelAndView error404(ModelAndView model) {
		model.setViewName("shop/404");
		return model;
	}
	/**
	 * 500页面
	 */
	@RequestMapping(value="500", method = RequestMethod.GET)
	public ModelAndView error500(ModelAndView model) {
		model.setViewName("shop/500");
		return model;
	}

}
