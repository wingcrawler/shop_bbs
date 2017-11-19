package com.sqe.shop.controller.api;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Advertisement;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.AdvertisementService;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.biz.BizProductService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.Resp;

@Controller
@RequestMapping("/api/product")
public class ApiProductController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(ApiProductController.class);

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
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private BizProductService bizProductService;

	/**
	 * 获取产品列表
	 * 
	 * @param product
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public Resp<?> getProductList(Product product,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		product.setUserId(this.getCurrentUserId());
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		return Resp.success(page);
	}

	/**
	 * 产品列表页
	 * 
	 * @param model
	 * @param parentType
	 * @param childType
	 * @param typeName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "list")
	public PageUtil<Map<String, Object>> list(Long parentType, Long childType, String typeName, String searchText,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "12") int pageSize) throws UnsupportedEncodingException {
		/* pageSize=12; */

		Product product = new Product();
		ProductType productType = new ProductType();

		// 查询条件里拼接是一级分类还是二级分类
		if (parentType != null) {
			product.setProductTypeId(parentType);
		}
		if (childType != null) {
			product.setProductSubtypeId(childType);
		}
		product.setProductStatus(Constants.PRODUCT_ON);
		if (StringUtils.isNotBlank(searchText)) {
			searchText = URLDecoder.decode(searchText, UTF8);
			product.setProductName(searchText);
		}
		// 查询
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		return page;
	}

	/**
	 * // 查询单个商品商品详情
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "single", method = RequestMethod.GET)
	public Resp<?> single(Long productId) {
		// 查询单个商品
		Product product = productService.getById(productId);
		if (product == null) {
			return Resp.notFound("not found product");
		}
		if (!product.getProductStatus().equals(Constants.PRODUCT_ON)) {
			return Resp.notFound("not found product");

		}
		return Resp.success(bizProductService.getProductDetail(productId, product));
	}

	/**
	 * 产品私信
	 * 
	 * @param model
	 * @param message
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "postMessage")
	public Resp<?> postMessage(Message message) {
		if (message.getProductId() == null) {
			return Resp.customFail("-1", "getProductId null");
		}

		Product product = productService.getById(message.getProductId());
		if (product == null) {
			return Resp.customFail("-1", "product null");
		}

		message.setReceiveId(product.getUserId());
		message.setMessageStatus(Constants.USER_OFF);
		message.setPostId(this.getCurrentUserId());
		message.setCreateTime(new Date());
		messageService.insert(message);
		return Resp.success("success");
	}

	/**
	 * 产品搜索接口
	 * 
	 * @param searchText
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "searchs", method = RequestMethod.POST)
	@ResponseBody
	public Resp<?>  searchs(String searchText,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "12") int pageSize) {
		PageUtil<Map<String, Object>> productPage = new PageUtil<Map<String, Object>>();
		if (StringUtils.isBlank(searchText)) {
			Resp.customFail("-1","searchText null");
		}
		pageSize = 12;
		// 搜索产品
		Product product = new Product();
		product.setProductName(searchText);
		productPage = productService.getMapListByParm(product, pageNo, pageSize);

		return Resp.success(productPage);
	}

	/**
	 * 商城首页产品分类
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseBody
	public Resp<?> category() {
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("orderby", "type_rank asc");
		parm.put("typeLevel", "1");
		PageUtil<Map<String, Object>> pageUtil = productTypeService.getMapListByParm(parm, 1, 6);	
		return Resp.success(pageUtil);
	}

	/**
	 * 商城首页根据产品分类回去产品列表（10个）
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/category/products", method = RequestMethod.POST)
	@ResponseBody
	public Resp<?> ProductsByType(@NotNull @RequestParam(name = "typeId", required = true) Long typeId) {
		Map<String, Object> resMap = this.responseOK1("");
		Product product = new Product();
		product.setProductTypeId(typeId);
		PageUtil<Map<String, Object>> pageUtil = productService.getHotProductList(product, 1, 10);
		resMap.put("hotproducts", pageUtil.getList());
		return Resp.success(resMap);
	}

	/**
	 * Pc商城首页轮播图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/advertisement", method = RequestMethod.GET)
	@ResponseBody
	public Resp<?> advertisement() {
		// 轮播图
		Advertisement advertisement = new Advertisement();
		advertisement.setType(Constants.AD_TYPE_LB);
		advertisement.setDevice(Constants.AD_DEVICE_PC);
		PageUtil<Advertisement> adPage = advertisementService.getBeanListByParm(advertisement, 1, -1);
		return Resp.success(adPage);
	}

	/**
	 * 产品评论
	 * 
	 * @param productId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getProductComment")
	public Resp<?> getProductComment(@RequestParam Long productId, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		if (productId == null) {
			return Resp.notFound("product id is blank");
		}
		return Resp.success(bizProductService.getProductComment(productId, pageNo, pageSize));
	}
	
	/**
	 * 产品评论
	 * 
	 * @param productId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getProductType")
	public Resp<?> getProductType(ProductType productType, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		if (productType == null) {
			return Resp.notFound("productType is blank");
		}
		return Resp.success(productTypeService.getBeanListByParm(productType, pageNo, pageSize));
	}

}