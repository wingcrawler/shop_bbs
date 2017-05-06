package com.sqe.shop.controller.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.BaseFrontController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Product;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/product")
public class FrontProductController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(FrontProductController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private ImageService imageService;
	
	
	/**
	 * 商品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="single", method = RequestMethod.GET)
	public ModelAndView single(ModelAndView model, Long productId) {
		//查询单个商品
		Product product = productService.getById(productId);
		if(product==null){
			model.setViewName("shop/404");
			return model;
		}
		model.addObject("product", product);
		//查询商品图片
		Image image = new Image();
		image.setProductId(productId);
		PageUtil<Image> imgPage = imageService.getBeanListByParm(image, 0, -1);
		model.addObject("imgList", imgPage.getList());
		
		model.setViewName("shop/single");
		return model;
	}
	
}
