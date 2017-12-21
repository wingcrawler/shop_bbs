package com.sqe.shop.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Comment;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.model.Shop;
import com.sqe.shop.model.Thread;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.ThreadService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.service.file.ImageFileService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.PropertiesUtil;
import com.sqe.shop.util.RegularUtil;
import com.sqe.shop.util.Resp;
import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/sell")
public class ApiSellerController extends BaseFrontController {

	private static final Logger logger = LoggerFactory.getLogger(ApiSellerController.class);

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private CachedService cachedService;
	@Autowired
	private ImageFileService imageFileService;
	@Autowired
	private ThreadService threadService;
	@Autowired
	private CommentService commentService;

	/*
	 * **************************************************
	 * *********************商品管理************************
	 * **************************************************
	 */

	/**
	 * 卖家获取产品列表
	 * 
	 * @param product
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getProductList", method = RequestMethod.GET)
	@ApiOperation(value = "获取当前登录用户商品列表", notes = "商家登录 获取 商家的产品列表  需要登录")
	public Resp<?> getProductList(Product product, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {

		if (this.isLogin()) {
			product.setUserId(this.getCurrentUserId());
			PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
			return Resp.success(page);
		} else {
			return Resp.forbidden("need login in");
		}
	}

	/**
	 * 产品编辑页接口
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doEditProduct", method = RequestMethod.GET)
	@ApiOperation(value = "商家编辑列表中的产品", notes = "传入商品ID 需要商家登录")
	public Resp<?> doEditProduct(Long productId) {
		Map<String, Object> response = new HashMap<String, Object>();
		Product product = productService.getByIdAndUserId(productId);

		if (product == null) {
			return Resp.notFound("not found");
		}

		// 产品一级分类
		ProductType type = new ProductType();
		type.setTypeLevel(1);
		PageUtil<ProductType> typeOnePage = productTypeService.getBeanListByParm(type, 1, -1);
		if (cachedService.getLang().equals("zh")) {
			for (ProductType pt : typeOnePage.getList()) {
				pt.setTypeName(pt.getTypeNameCh());
			}
		}
		response.put("typeList", typeOnePage.getList());

		// 产品二级分类
		type.setTypeLevel(2);
		type.setParentId(product.getProductTypeId());
		PageUtil<ProductType> typeTwoPage = productTypeService.getBeanListByParm(type, 1, -1);
		if (cachedService.getLang().equals("zh")) {
			for (ProductType pt : typeTwoPage.getList()) {
				pt.setTypeName(pt.getTypeNameCh());
			}
		}
		response.put("subtypeList", typeTwoPage.getList());

		// 查询图片
		List<Image> images = imageService.getByProductId(product.getId());
		List<Image> imgList = new ArrayList<Image>();

		for (Image img : images) {
			if (img.getIndexShow() == 1) {
				response.put("imgCover", img);
			} else {
				imgList.add(img);
			}
		}
		response.put("imgList", imgList);
		response.put("imgCount", images.size());// 图片数量
		response.put("inputCount", 6 - images.size());// 图片输入框数量

		response.put("entity", product);
		return Resp.success(response);
	}

	/**
	 * 商品详情
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	@ApiOperation(value = "商家查看商品详情", notes = "传入商品ID 需要商家登录")
	public Resp<?> productDetail(Long id) {

		Product entity = productService.getByIdAndUserId(id);
		if (entity == null) {
			return Resp.notFound("not found");
		}
		return Resp.success(entity);
	}

	/**
	 * 保存商品
	 * 
	 * @param product
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doSaveProduct", method = RequestMethod.POST)
	@ApiOperation(value = "保存商品", notes = "废弃")
	public Map<String, Object> doSaveProduct(@RequestBody Product product,
			@RequestParam(name = "indexFile", value = "indexFile", required = false) MultipartFile indexFile,
			@RequestParam(name = "listFile", value = "listFile", required = false) MultipartFile[] listFile) {
		if (product.getId() != null) {
			Product p = productService.getByIdAndUserId(product.getId());
			if (p == null) {
				return responseError(-1, "save_failed");
			}
		}

		if (StringUtils.isBlank(product.getProductName())) {
			return responseError(-1, "error_empty_product_name");
		}
		if (StringUtils.isBlank(product.getProductDescripton())) {
			return responseError(-1, "error_empty_description");
		}
		if (product.getProductTypeId() == null || product.getProductTypeId() < 0) {
			return responseError(-1, "error_no_type");
		}
		if (product.getProductPrice() == null || product.getProductPrice() < 0) {
			return responseError(-1, "error_empty_product_price");
		}
		if (product.getProductCount() == null || product.getProductCount() < 0) {
			return responseError(-1, "error_empty_product_count");
		}

		product.setUserId(this.getCurrentUserId());
		Shop shop = shopService.getByUserId(this.getCurrentUserId());
		if (shop == null) {
			return responseError(-1, "error_illegal");
		}

		product.setShopId(shop.getId());
		product.setProductStatus(Constants.PRODUCT_WAIT);
		int count = productService.save(product);
		if (count == 0) {
			return responseError(-1, "save_failed");
		}

		String fileName = "";
		Map<String, Object> resMap = null;
		if (indexFile != null) {
			resMap = imageFileService.uploadImage(indexFile);
			fileName = resMap.get("errorInfo").toString();
			if (resMap.get("errorNo").equals(0)) {
				imageService.saveProductIndexImg(product, fileName, 1);
			} else {
				return resMap;
			}
		}
		for (MultipartFile file : listFile) {
			resMap = imageFileService.uploadImage(file);
			fileName = resMap.get("errorInfo").toString();
			if (resMap.get("errorNo").equals(0)) {
				imageService.saveProductImg(product, fileName, 0);
			} else {
				return resMap;
			}
		}

		return responseOK("save_success");
	}

	/**
	 * 保存商品new 接口
	 * 
	 * @param product
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doSaveProduct1", method = RequestMethod.POST)
	@ApiOperation(value = "商家查看商品详情", notes = "保存商品new 接口   base64 图片")
	public Resp<?> doSaveProduct1(Product product,
			@RequestParam(name = "targetImgVal", value = "targetImgVal", required = false) String targetImgVal,
			@RequestParam(name = "targetImg5Val", value = "targetImg5Val", required = false) String targetImg5Val,
			@RequestParam(name = "targetImg1Val", value = "targetImg1Val", required = false) String targetImg1Val,
			@RequestParam(name = "targetImg2Val", value = "targetImg2Val", required = false) String targetImg2Val,
			@RequestParam(name = "targetImg3Val", value = "targetImg3Val", required = false) String targetImg3Val,
			@RequestParam(name = "targetImg4Val", value = "targetImg4Val", required = false) String targetImg4Val) {
		try {
			if (product.getId() != null) {
				Product p = productService.getByIdAndUserId(product.getId());
				if (p == null) {
					return Resp.customFail("-1", "save_failed");
				}

			} else {
				// 首次上传 需要上传默认图片
				if (StringUtils.isBlank(targetImgVal)) {
					return Resp.customFail("-1", "error_no_default_img");
				}
			}

			Map<String, Object> checkMap = checkProduct(product);
			if (!checkMap.get(ERROR_NO).equals(ERRORCODE_SUCCESS)) {
				return Resp.success(checkMap);
			}

			product.setUserId(this.getCurrentUserId());
			Shop shop = shopService.getByUserId(this.getCurrentUserId());
			if (shop == null) {
				return Resp.customFail("-1", "error_illegal");
			}

			product.setShopId(shop.getId());
			product.setProductStatus(Constants.PRODUCT_WAIT);
			int count = productService.save(product);
			if (count == 0) {
				return Resp.customFail("-1", "save_failed");
			}

			String fileName = "";
			Map<String, Object> resMap = null;

			if (!StringUtils.isBlank(targetImgVal)) {
				if (targetImgVal.contains("data:image")) {
					resMap = imageFileService.uploadBase64Image(targetImgVal);
					fileName = resMap.get("errorInfo").toString();
					if (resMap.get("errorNo").equals(0)) {
						imageService.saveProductIndexImg(product, fileName, 1);
					} else {
						return Resp.success(resMap);
					}
				}

			}
			if (!StringUtils.isBlank(targetImg1Val)) {
				if (targetImg1Val.contains("data:image")) {
					resMap = imageFileService.uploadBase64Image(targetImg1Val);
					fileName = resMap.get("errorInfo").toString();
					if (resMap.get("errorNo").equals(0)) {
						imageService.saveProductIndexImg(product, fileName, 2);
					} else {
						return Resp.success(resMap);
					}
				}
			}
			if (!StringUtils.isBlank(targetImg2Val)) {
				if (targetImg2Val.contains("data:image")) {
					resMap = imageFileService.uploadBase64Image(targetImg2Val);
					fileName = resMap.get("errorInfo").toString();
					if (resMap.get("errorNo").equals(0)) {
						imageService.saveProductIndexImg(product, fileName, 3);
					} else {
						return Resp.success(resMap);
					}
				}
			}
			if (!StringUtils.isBlank(targetImg3Val)) {
				if (targetImg3Val.contains("data:image")) {
					resMap = imageFileService.uploadBase64Image(targetImg3Val);
					fileName = resMap.get("errorInfo").toString();
					if (resMap.get("errorNo").equals(0)) {
						imageService.saveProductIndexImg(product, fileName, 4);
					} else {
						return Resp.success(resMap);
					}
				}
			}
			if (!StringUtils.isBlank(targetImg5Val)) {
				if (targetImg5Val.contains("data:image")) {
					resMap = imageFileService.uploadBase64Image(targetImg5Val);
					fileName = resMap.get("errorInfo").toString();
					if (resMap.get("errorNo").equals(0)) {
						imageService.saveProductIndexImg(product, fileName, 5);
					} else {
						return Resp.success(resMap);
					}
				}
			}
			if (!StringUtils.isBlank(targetImg4Val)) {
				if (targetImg4Val.contains("data:image")) {
					resMap = imageFileService.uploadBase64Image(targetImg4Val);
					fileName = resMap.get("errorInfo").toString();
					if (resMap.get("errorNo").equals(0)) {
						imageService.saveProductIndexImg(product, fileName, 6);
					} else {
						return Resp.success(resMap);
					}
				}
			}

			return Resp.success("success");
		} catch (Exception e) {
			logger.error("doSaveProduct1 ex" + e.getCause());
			return Resp.customFail("-1", "error_sys");
		}

	}

	private Map<String, Object> checkProduct(Product product) {
		// 产品名称
		if (StringUtils.isBlank(product.getProductName())) {
			return responseError(-1, "error_empty_product_name");
		}
		if (product.getProductName().length() > 80) {
			return responseError(-1, "product_name_too_long");
		}
		if (StringUtils.isBlank(product.getProductEnName())) {
			return responseError(-1, "error_empty_product_name_en");
		}
		if (product.getProductEnName().length() > 80) {
			return responseError(-1, "product_name_too_long_en");
		}

		// 产品描述
		/*
		 * if(StringUtils.isBlank(product.getProductDescripton())){ return
		 * responseError(-1, "error_empty_description"); }
		 */
		if (StringUtils.isNotBlank(product.getProductDescripton()) && product.getProductDescripton().length() > 3000) {
			return responseError(-1, "description_too_long");
		}
		/*
		 * if(StringUtils.isBlank(product.getProductEnDescription())){ return
		 * responseError(-1, "error_empty_description_en"); }
		 */
		if (StringUtils.isNotBlank(product.getProductEnDescription())
				&& product.getProductEnDescription().length() > 3000) {
			return responseError(-1, "description_too_long_en");
		}

		// 产品数量
		if (product.getProductCount() == null || product.getProductCount() < 0) {
			return responseError(-1, "error_empty_product_count");
		}
		if (String.valueOf(product.getProductCount()).length() > 9) {
			return responseError(-1, "product_count_too_long");
		}
		if (!RegularUtil.isPositiveInt(product.getProductCount())) {
			return responseError(-1, "error_product_count_formate");
		}

		// 产品类别
		if (product.getProductTypeId() == null || product.getProductTypeId() < 0) {
			return responseError(-1, "error_no_type");
		}

		// 产品价格
		if (product.getProductPrice() == null || product.getProductPrice() < 0) {
			return responseError(-1, "error_empty_product_price");
		}

		/*
		 * if(String.valueOf(product.getProductPrice()).length()>9){ >>>>>>>
		 * branch 'master' of https://github.com/wingcrawler/shop_bbs.git return
		 * responseError(-1, "product_price_too_long"); <<<<<<< HEAD } if
		 * (!RegularUtil.isFloat(product.getProductPrice() + "")) { ======= }
		 */
		/*
		 * if(!RegularUtil.isFloat(product.getProductPrice()+"")){ >>>>>>>
		 * branch 'master' of https://github.com/wingcrawler/shop_bbs.git return
		 * responseError(-1, "error_product_price_formate"); <<<<<<< HEAD }
		 * 
		 * // 产品外链 if (StringUtils.isNotBlank(product.getProductUrl()) &&
		 * product.getProductUrl().length() > 180) { ======= }
		 */

		// 产品外链
		if (StringUtils.isNotBlank(product.getProductUrl()) && product.getProductUrl().length() > 180) {
			return responseError(-1, "product_url_too_long");
		}

		return this.responseOK1("");
	}

	/**
	 * 删除商品
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@DeleteMapping(value = "/deleteProductById")
	@ApiOperation(value = "商家编辑列表中的产品", notes = "传入商品ID 需要商家登录")
	public Resp<?> deleteProductById(List<String> idList) {
		if (idList.isEmpty()) {
			return Resp.customFail("-1", "List null");
		}
		for (String str : idList) {
			if (StringUtils.isNotBlank(str.trim()) && RegularUtil.isNumeric(str.trim())) {
				Long productId = Long.valueOf(str.trim());
				productService.deleteByIdAndUserId(productId);
			}
		}
		return Resp.success("success");
	}

	/**
	 * 下架商品
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/offProductById")
	@ApiOperation(value = "下架商品", notes = "批量下架商品")
	public Resp<?> offProductById(@RequestBody List<String> idList) {
		return Resp.success(productService.updateProductStatus(idList, Constants.PRODUCT_OFF));
	}

	/**
	 * 上架商品
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@PostMapping(value = "/onProductById")
	@ApiOperation(value = "上架商品", notes = "批量上架商品")
	public Resp<?> onProductById(@RequestBody List<String> idList) {
		return Resp.success(productService.updateProductStatus(idList, Constants.PRODUCT_ON));
	}

	/*
	 * **************************************************
	 * *********************产品留言************************
	 * **************************************************
	 */

	/**
	 * 商品留言页面
	 * 
	 * @param model
	 * @param type
	 *            1:留言 2：私信
	 * @return
	 */
	@RequestMapping(value = "/messagePage", method = RequestMethod.GET)
	@ApiOperation(value = " 商品留言页面", notes = "type 1:留言 2：私信")
	public Resp<?> messagePage(@RequestParam String type,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		pageSize = 10;
		PageUtil<Map<String, Object>> page = new PageUtil<Map<String, Object>>();

		if (type.equals("1")) {// 产品评论
			page = getProductCommentList(this.getCurrentUserId(), pageNo, pageSize);

		} else if (type.equals("2")) {// 私信
			page = getMessageList(this.getCurrentUserId(), pageNo, pageSize);

		} else if (type.equals("3")) {// 新闻资讯评论
			page = getNewsCommentList(this.getCurrentUserId(), pageNo, pageSize);

		} 
		return Resp.success(page);
	}

	/**
	 * 私信列表
	 * 
	 * @param message
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMessageList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getMessageList(@RequestParam Long userId, @RequestParam int pageNo, @RequestParam Integer pageSize) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("receiveId", userId);
		parmMap.put("orderby", "m.create_time desc");
		parmMap.put("nullMessageId", true);
		PageUtil<Map<String, Object>> msgPage = messageService.getSellerMessageListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}

	public PageUtil<Map<String, Object>> getProductCommentList(Long userId, int pageNo, Integer pageSize) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("productUserId", userId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> msgPage = commentService.getProductCommentListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}

	public PageUtil<Map<String, Object>> getNewsCommentList(Long userId, int pageNo, Integer pageSize) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> msgPage = commentService.getNewsCommentListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}

	/**
	 * 回复私信或者产品留言
	 * 
	 * @param msg
	 * @param productId
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/messageReply")
	@ApiOperation(value = " 回复私信或者产品留言", notes = "回复私信或者产品留言")
	public Resp<?> messageReply(@RequestParam String msgContent, @RequestParam String type, @RequestParam Long productId, @RequestParam Long messageId,@RequestParam  Long commentId,
			@RequestParam Long replyToId) {
		if (StringUtils.isBlank(msgContent)) {
			return Resp.customFail("-1", "error_empty_content");
		}
		if (type.equals("1")) {// 留言
			Comment comment = new Comment();
			comment.setContext(msgContent);
			comment.setDate(new Date());
			comment.setCommentId(commentId);
			comment.setUserId(this.getCurrentUserId());
			comment.setProductId(productId);
			comment.setStatus(Constants.COMMENT_ON);
			comment.setReplyToUserId(replyToId);
			commentService.insert(comment);
		} else if (type.equals("2")) {// 私信
			Message message = new Message();
			message.setMessageContext(msgContent);
			message.setCreateTime(new Date());
			message.setProductId(productId);
			message.setPostId(this.getCurrentUserId());
			message.setReceiveId(replyToId);
			message.setMessageStatus(Constants.MSG_ON);
			message.setMessageId(messageId);
			messageService.insert(message);
		} else {
			responseError(-1, "error_illegal");
		}
		return Resp.success("");
	}

	/*
	 * **************************************************
	 * 
	 * *********************商家资料************************
	 * 
	 * **************************************************
	 */

	/**
	 * 保存商家基本信息
	 * 
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doSaveMerchant", method = RequestMethod.POST)
	@ApiOperation(value = " 保存商家基本信息", notes = " 保存商家基本信息")
	public Resp<?> doSaveMerchant(@RequestBody Shop shop,
			@RequestParam(name = "file", value = "file", required = false) MultipartFile attachFile) {
		if (StringUtils.isBlank(shop.getShopTitle())) {
			return Resp.customFail("-1", "error_empty_shop_name");
		}

		boolean exitShop = shopService.exitShop(shop.getId());
		if (!exitShop) {
			return Resp.customFail("-1", "error_illegal");
		}

		if (attachFile != null) {
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
			String fileName = resMap.get("errorInfo").toString();
			if (!resMap.get("errorNo").equals(0)) {
				return Resp.success(resMap);
			}
			String uploadPath = PropertiesUtil.get("upload_path_save");
			uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1) + "/";
			shop.setShopLogoImg(uploadPath + fileName);
		}

		shopService.save(shop);

		return Resp.success("success");
	}

	/**
	 * 营业执照页面 店铺详细信息 商家介绍 资质   获取店铺申请信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/businessLicense", method = RequestMethod.GET)
	@ApiOperation(value = "  营业执照页面 店铺详细信息 商家介绍 资质   获取店铺申请信息", notes = "  营业执照页面、 店铺详细信息、 商家介绍 资质 、  获取店铺申请信息 四合一接口")
	public Resp<Shop> businessLicense() {
		Long UserId=this.getCurrentUserId();
		if(null==UserId){
		 return Resp.customFail("-1", "need login in");
		}
		Shop shop = shopService.getCurrentUserShop(UserId);
		return Resp.success(shop);
	}

	/**
	 * 保存营业执照
	 * 
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveBusinessLicense", method = RequestMethod.POST)
	public Resp<?> saveBusinessLicense(@RequestBody Shop shop,
			@RequestParam(name = "file", value = "file", required = false) MultipartFile attachFile) {
		boolean exitShop = shopService.exitShop(shop.getId());
		if (!exitShop) {
			return Resp.customFail("-1", "error_illegal");
		}

		if (attachFile != null) {
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
			String fileName = resMap.get("errorInfo").toString();
			if (!resMap.get("errorNo").equals(0)) {
				return Resp.success(resMap);
			}
			String uploadPath = PropertiesUtil.get("upload_path_save");
			uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1) + "/";
			shop.setShopLicenesImg(uploadPath + fileName);
		}
		shopService.save(shop);
		return Resp.success("success");
	}

	/**
	 * 保存商家介绍
	 * 
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveMerchantIntroduce", method = RequestMethod.POST)
	@ApiOperation(value = "保存商家介绍", notes = "保存商家介绍  资质")
	public Resp<?> saveMerchantIntroduce(@RequestBody Shop shop,
			@RequestParam(name = "file", value = "file", required = false) MultipartFile attachFile) {
		boolean exitShop = shopService.exitShop(shop.getId());
		if (!exitShop) {
			return Resp.customFail("-1", "error_illegal");
		}

		if (attachFile != null) {
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
			String fileName = resMap.get("errorInfo").toString();
			if (!resMap.get("errorNo").equals(0)) {
				return Resp.success(resMap);
			}
			String uploadPath = PropertiesUtil.get("upload_path_save");
			uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1) + "/";
			shop.setShopImg(uploadPath + fileName);
		}
		shopService.save(shop);
		return Resp.success("success");
	}

	/**
	 * 保存商家信息 资质
	 * 
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
/*	@ResponseBody
	@RequestMapping(value = "/saveShelfQualification", method = RequestMethod.POST)
	@ApiOperation(value = "保存商家介绍", notes = "保存商家信息 资质")
	public Resp<?> saveShelfQualification(Shop shop,
			@RequestParam(name = "file", value = "file", required = false) MultipartFile attachFile) {
		boolean exitShop = shopService.exitShop(shop.getId());
		if (!exitShop) {
			return Resp.customFail("-1", "error_illegal");
		}

		if (attachFile != null) {
			Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
			String fileName = resMap.get("errorInfo").toString();
			if (!resMap.get("errorNo").equals(0)) {
				return Resp.success(resMap);
			}
			String uploadPath = PropertiesUtil.get("upload_path_save");
			uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1) + "/";
			shop.setShelfQualificationImg(uploadPath + fileName);
		}
		shopService.save(shop);
		return Resp.success("success");
	}*/

	// 论坛
	/**
	 * 发帖回复页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/postReply", method = RequestMethod.GET)
	@ApiOperation(value = "发帖回复页面", notes = "发帖回复页面")
	public Resp<?> postReply(@RequestBody Thread thread,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		thread.setUserId(this.getCurrentUserId());
		PageUtil<Map<String, Object>> page = threadService.getSellThreadList(thread, pageNo, pageSize);
		return Resp.success(page);
	}

	/**
	 * 删除产品图片
	 * 
	 * @param imgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteByParm", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除产品图片", notes = "删除产品图片")
	public Resp<?> deleteByParm(@RequestParam Long productId, @RequestParam Long imgId) {
		if (productId == null || imgId == null) {
			return Resp.customFail("-1", "productId or imgId is null");
		}
		imageService.deleteByIdAndProductId(imgId, productId);
		return Resp.success("success");
	}

	@ResponseBody
	@RequestMapping(value = "/deleteImg", method = RequestMethod.DELETE)
	@ApiOperation(value = "删除商家资质图片", notes = "删除商家资质图片")
	public Resp<?> deleteImg(@RequestParam Long id, @RequestParam String type) {
		if (id == null || StringUtils.isBlank(type)) {
			return Resp.customFail("-1", "id or type is null");
		}

		Shop shop = new Shop();
		shop.setId(id);
		if (type.equals("logo")) {
			shop.setShopLogoImg("");
		} else if (type.equals("license")) {
			shop.setShopLicenesImg("");
		} else if (type.equals("shopimg")) {
			shop.setShopImg("");
		} else if (type.equals("shelfQualificationImg")) {
			shop.setShelfQualificationImg("");
		}
		shopService.update(shop);
		return Resp.success("success");
	}

}