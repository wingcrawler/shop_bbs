package com.sqe.shop.controller.api;

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
import com.sqe.shop.util.Resp;

@Controller
@RequestMapping("api/shop")
public class ApiShopController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(ApiShopController.class);

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
	 * 店铺产品列表
	 * 
	 * @param product
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/product/list", method = RequestMethod.POST)
	@ResponseBody
	public Resp<?> productList(Product product,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		PageUtil<Map<String, Object>> result = new PageUtil<Map<String, Object>>();
		PageUtil<Map<String, Object>> productPage = new PageUtil<Map<String, Object>>();
		Long shopId = product.getShopId();
		// 查询shop
		Shop shop = shopService.getById(shopId);
		if (shop == null) {
			return Resp.locked("not found shop");
		}
		productPage = productService.getMapListByParm(product, pageNo, pageSize);

		return Resp.success(productPage);
	}

	/**
	 * 商家详情 返回太多信息给前端
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "shopdetail", method = RequestMethod.GET)
	@ResponseBody
	public Resp<?> details(@NotNull Long shopId) {
		shopDto result = new shopDto();
		if (shopId == null) {
			return Resp.locked("not found shop");
		}

		// 查询shop
		Shop shop = shopService.getById(shopId);
		if (shop == null) {
			return Resp.locked("not found shop");
		}
		result.setShop(shop);

		// 商家图片
		Image image = new Image();
		image.setShopId(shopId);
		PageUtil<Image> images = imageService.getBeanListByParm(image, 1, -1);
		result.setImages(images);
		return Resp.success(result);
	}

}
