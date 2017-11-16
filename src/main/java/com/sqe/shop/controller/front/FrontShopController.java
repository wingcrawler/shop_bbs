package com.sqe.shop.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.dto.shopDto;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.model.Shop;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/shop")
public class FrontShopController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(FrontShopController.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private CachedService cachedService;
	@Autowired
	private ShopService shopService;

	/**
	 * 商家的产品列表
	 * 
	 * @param model
	 * @param shopId
	 * @param productTypeId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "product", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model, Long shopId, Long productTypeId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "12") int pageSize) {
		if (shopId == null) {
			model.setViewName("shop/404");
			return model;
		}
		pageSize = 12;

		// 查询shop
		Shop shop = shopService.getById(shopId);
		if (shop == null) {
			model.setViewName("shop/404");
			return model;
		}
		model.addObject("shop", shop);

		// 没有一级分类 选取默认分类
		ProductType productType = new ProductType();
		if (productTypeId == null) {
			productType.setTypeLevel(1);
			PageUtil<ProductType> typePage = productTypeService.getBeanListByParm(productType, 1, -1);
			if (typePage != null && !typePage.getList().isEmpty()) {
				productType = typePage.getList().get(0);
			}
		} else {
			// 产品类别
			productType = productTypeService.getById(productTypeId);
		}
		model.addObject("productType", productType);

		// 查询店家的产品
		Product product = new Product();
		product.setProductTypeId(productTypeId);
		product.setShopId(shopId);
		PageUtil<Map<String, Object>> productPage = productService.getMapListByParm(product, pageNo, pageSize);
		model.addObject("productPage", productPage);

		model.setViewName("shop/shop_product");
		return model;
	}

	/**
	 * 店铺产品列表
	 * @param product
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/product/list", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil<Map<String, Object>> productList(Product product,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		PageUtil<Map<String, Object>> result = new PageUtil<Map<String, Object>>();
		PageUtil<Map<String, Object>> productPage = new PageUtil<Map<String, Object>>();
		Long shopId = product.getShopId();
		// 查询shop
		Shop shop = shopService.getById(shopId);
		if (shop == null) {
			result.setList(null);
			return result;
		}
		productPage = productService.getMapListByParm(product, pageNo, pageSize);

		return productPage;
	}

	/**
	 * 商家详情
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelAndView model, Long shopId) {
		if (shopId == null) {
			model.setViewName("shop/404");
			return model;
		}

		// 查询shop
		Shop shop = shopService.getById(shopId);
		if (shop == null) {
			model.setViewName("shop/404");
			return model;
		}
		model.addObject("shop", shop);

		// 商家图片
		Image image = new Image();
		image.setShopId(shopId);
		PageUtil<Image> images = imageService.getBeanListByParm(image, 1, -1);
		model.addObject("imageList", images);

		model.setViewName("shop/shop_detail");
		return model;
	}
	
	/**
	 * 商家详情  返回太多信息给前端
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "shopdetail", method = RequestMethod.GET)
	@ResponseBody
	public shopDto details(@NotNull Long shopId) {
		shopDto result=new shopDto();
		if (shopId == null) {		
			return null;
		}

		// 查询shop
		Shop shop = shopService.getById(shopId);
		if (shop == null) {
			return null;
		}
		result.setShop(shop);

		// 商家图片
		Image image = new Image();
		image.setShopId(shopId);
		PageUtil<Image> images = imageService.getBeanListByParm(image, 1, -1);
		result.setImages(images);
		return result;
	}

}
