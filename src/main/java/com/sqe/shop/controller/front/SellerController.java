package com.sqe.shop.controller.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

@Controller
@RequestMapping("/front/sell")
public class SellerController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
	
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
	 * 商家产品列表页
	 * @return
	 */
	@RequestMapping(value="/productListPage", method = RequestMethod.GET)
	public ModelAndView productListPage(ModelAndView model, Product product,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		pageSize = 10;
		model.addObject("productPage", getProductList(product, pageNo, pageSize));
		
		model.setViewName("shop/sell/product_list");
		return model;
	}
	
	/**
	 * 获取产品列表
	 * @param product
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getProductList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getProductList(Product product,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		product.setUserId(this.getCurrentUserId());
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		
		return page;
	}
	
	/**
	 * 产品编辑页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/editProduct", method = RequestMethod.GET)
	public ModelAndView editProduct(ModelAndView model, Long id) {
		model.setViewName("shop/sell/product_edit");
		
		//产品一级分类
		ProductType type = new ProductType();
		type.setTypeLevel(1);
		PageUtil<ProductType> typeOnePage = productTypeService.getBeanListByParm(type, 1, -1);
		if(cachedService.getLang().equals("zh")){
			for(ProductType pt : typeOnePage.getList()){
				pt.setTypeName(pt.getTypeNameCh());
			}
		}
		model.addObject("typeList", typeOnePage.getList());

		Product	entity = productService.getByIdAndUserId(id);
		if(entity==null){
			model.addObject("inputCount", 6);
			return model;
		}
		
		//产品二级分类
		type.setTypeLevel(2);
		type.setParentId(entity.getProductTypeId());
		PageUtil<ProductType> typeTwoPage = productTypeService.getBeanListByParm(type, 1, -1);
		if(cachedService.getLang().equals("zh")){
			for(ProductType pt : typeTwoPage.getList()){
				pt.setTypeName(pt.getTypeNameCh());
			}
		}
		model.addObject("subtypeList", typeTwoPage.getList());
		
		// 查询图片
		List<Image> images = imageService.getByProductId(entity.getId());
		List<Image> imgList = new ArrayList<Image>();
		for(Image img : images){
			if(img.getIndexShow()==1){
				model.addObject("img", img);
			}else {
				imgList.add(img);
			}
		}
		model.addObject("imgList", imgList);
		model.addObject("imgCount", images.size());//图片数量
		model.addObject("inputCount", 6-images.size());//图片输入框数量
		
		model.addObject("entity", entity);
		return model;
	}
	
	/**
	 * 商品详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/productDetail", method = RequestMethod.GET)
	public ModelAndView productDetail(ModelAndView model, Long id) {
		if(id==null){
			model.setViewName("shop/404");
			return model;
		}
		
		Product	entity = productService.getByIdAndUserId(id);
		if(entity==null){
			model.setViewName("shop/404");
			return model;
		}
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		ProductType typeOne = productTypeService.getById(entity.getProductTypeId());
		resMap.put("typeOne", typeOne.getTypeName());
		ProductType typeTwo = productTypeService.getById(entity.getProductSubtypeId());
		if(typeTwo!=null){
			resMap.put("typeTwo", typeTwo.getTypeName());	
		}
		
		List<Image> images = imageService.getByProductId(entity.getId());
		resMap.put("showImg", images.get(0));
		images.remove(0);
		resMap.put("imgList", images);
		
		resMap.put("count", entity.getProductCount());
		resMap.put("view", entity.getProductView());
		resMap.put("urlClick", entity.getProductUrlClick());
		resMap.put("price", entity.getProductPrice());
		resMap.put("status", entity.getProductStatus());
		resMap.put("statusStr", this.getProductStatus(entity.getProductStatus()));
		resMap.put("productName", entity.getProductName());
		resMap.put("description", entity.getProductDescripton());
		resMap.put("productNameEn", entity.getProductEnName());
		resMap.put("descriptionEn", entity.getProductEnDescription());
		
		model.addObject("entity", resMap);
		model.setViewName("shop/sell/product_details");
		return model;
	}
	
	/**
	 * 保存商品
	 * @param product
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSaveProduct", method = RequestMethod.POST)
	public Map<String, Object> doSaveProduct(Product product,
			@RequestParam(name = "indexFile",value="indexFile", required = false) MultipartFile indexFile,
			@RequestParam(name = "listFile",value="listFile", required = false) MultipartFile[] listFile) {
		if(product.getId()!=null){
			Product p = productService.getByIdAndUserId(product.getId());
			if(p==null){
				return responseError(-1, "save_failed");
			}
		}
		
		if(StringUtils.isBlank(product.getProductName())){
			return responseError(-1, "error_empty_product_name");
		}
		if(StringUtils.isBlank(product.getProductDescripton())){
			return responseError(-1, "error_empty_description");
		}
		if(product.getProductTypeId()==null || product.getProductTypeId()<0){
			return responseError(-1, "error_no_type");
		}
		if(product.getProductPrice()==null || product.getProductPrice()<0){
			return responseError(-1, "error_empty_product_price");
		}
		if(product.getProductCount()==null || product.getProductCount()<0){
			return responseError(-1, "error_empty_product_count");
		}
		
		product.setUserId(this.getCurrentUserId());
		Shop shop = shopService.getByUserId(this.getCurrentUserId());
		if(shop==null){
			return responseError(-1, "error_illegal");
		}
		
		product.setShopId(shop.getId());
		product.setProductStatus(Constants.PRODUCT_WAIT);
		int count = productService.save(product);
		if(count==0){
			return responseError(-1, "save_failed");
		}
		
		String fileName="";
		Map<String, Object> resMap = null;
		if(indexFile!=null){
		    resMap = imageFileService.uploadImage(indexFile);
		    fileName = resMap.get("errorInfo").toString(); 
		    if(resMap.get("errorNo").equals(0)){
				imageService.saveProductIndexImg(product, fileName, 1);	
			} else {
				return resMap;
			}
	    }
		for(MultipartFile file : listFile){
			resMap = imageFileService.uploadImage(file);
			fileName = resMap.get("errorInfo").toString(); 
		    if(resMap.get("errorNo").equals(0)){
				imageService.saveProductImg(product, fileName, 0);	
			} else {
				return resMap;
			}
		}
		
		return responseOK("save_success");
	}
	
	/**
	 * 保存商品
	 * @param product
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSaveProduct1", method = RequestMethod.POST)
	public Map<String, Object> doSaveProduct1(Product product,
			@RequestParam(name = "targetImgVal",value="targetImgVal", required = false) String targetImgVal,
			@RequestParam(name = "targetImg0Val",value="targetImg0Val", required = false) String targetImg0Val,
			@RequestParam(name = "targetImg1Val",value="targetImg1Val", required = false) String targetImg1Val,
			@RequestParam(name = "targetImg2Val",value="targetImg2Val", required = false) String targetImg2Val,
			@RequestParam(name = "targetImg3Val",value="targetImg3Val", required = false) String targetImg3Val,
			@RequestParam(name = "targetImg4Val",value="targetImg4Val", required = false) String targetImg4Val) {
	
		if(product.getId()!=null){
			Product p = productService.getByIdAndUserId(product.getId());
			if(p==null){
				return responseError(-1, "save_failed");
			}
		}
		
		if(StringUtils.isBlank(product.getProductName())){
			return responseError(-1, "error_empty_product_name");
		}
		if(StringUtils.isBlank(product.getProductDescripton())){
			return responseError(-1, "error_empty_description");
		}
		if(product.getProductTypeId()==null || product.getProductTypeId()<0){
			return responseError(-1, "error_no_type");
		}
		if(product.getProductPrice()==null || product.getProductPrice()<0){
			return responseError(-1, "error_empty_product_price");
		}
		if(product.getProductCount()==null || product.getProductCount()<0){
			return responseError(-1, "error_empty_product_count");
		}
		
		product.setUserId(this.getCurrentUserId());
		Shop shop = shopService.getByUserId(this.getCurrentUserId());
		if(shop==null){
			return responseError(-1, "error_illegal");
		}
		
		product.setShopId(shop.getId());
		product.setProductStatus(Constants.PRODUCT_WAIT);
		int count = productService.save(product);
		if(count==0){
			return responseError(-1, "save_failed");
		}
		
		String fileName="";
		Map<String, Object> resMap = null;
		if(targetImgVal!=null){
		    resMap = imageFileService.uploadBase64Image(targetImgVal);
		    fileName = resMap.get("errorInfo").toString(); 
		    if(resMap.get("errorNo").equals(0)){
				imageService.saveProductIndexImg(product, fileName, 1);	
			} else {
				return resMap;
			}
	    }
		if(targetImg0Val!=null){
		    resMap = imageFileService.uploadBase64Image(targetImg0Val);
		    fileName = resMap.get("errorInfo").toString(); 
		    if(resMap.get("errorNo").equals(0)){
				imageService.saveProductIndexImg(product, fileName, 1);	
			} else {
				return resMap;
			}
	    }if(targetImg1Val!=null){
		    resMap = imageFileService.uploadBase64Image(targetImg1Val);
		    fileName = resMap.get("errorInfo").toString(); 
		    if(resMap.get("errorNo").equals(0)){
				imageService.saveProductIndexImg(product, fileName, 1);	
			} else {
				return resMap;
			}
	    }if(targetImg2Val!=null){
		    resMap = imageFileService.uploadBase64Image(targetImg2Val);
		    fileName = resMap.get("errorInfo").toString(); 
		    if(resMap.get("errorNo").equals(0)){
				imageService.saveProductIndexImg(product, fileName, 1);	
			} else {
				return resMap;
			}
	    }if(targetImg3Val!=null){
		    resMap = imageFileService.uploadBase64Image(targetImg3Val);
		    fileName = resMap.get("errorInfo").toString(); 
		    if(resMap.get("errorNo").equals(0)){
				imageService.saveProductIndexImg(product, fileName, 1);	
			} else {
				return resMap;
			}
	    }
	    if(targetImg4Val!=null){
		    resMap = imageFileService.uploadBase64Image(targetImg4Val);
		    fileName = resMap.get("errorInfo").toString(); 
		    if(resMap.get("errorNo").equals(0)){
				imageService.saveProductIndexImg(product, fileName, 1);	
			} else {
				return resMap;
			}
	    }
		
		return responseOK("save_success");
	}
//	@ResponseBody
//	@RequestMapping(value="/doSaveProduct1", method = RequestMethod.POST)
//	public Map<String, Object> doSaveProduct1(Product product,
//			@RequestParam(name = "indexFile",value="indexFile", required = false) String indexFile,
//			@RequestParam(name = "listFile",value="listFile", required = false) MultipartFile[] listFile) {
//	
//		if(product.getId()!=null){
//			Product p = productService.getByIdAndUserId(product.getId());
//			if(p==null){
//				return responseError(-1, "save_failed");
//			}
//		}
//		
//		if(StringUtils.isBlank(product.getProductName())){
//			return responseError(-1, "error_empty_product_name");
//		}
//		if(StringUtils.isBlank(product.getProductDescripton())){
//			return responseError(-1, "error_empty_description");
//		}
//		if(product.getProductTypeId()==null || product.getProductTypeId()<0){
//			return responseError(-1, "error_no_type");
//		}
//		if(product.getProductPrice()==null || product.getProductPrice()<0){
//			return responseError(-1, "error_empty_product_price");
//		}
//		if(product.getProductCount()==null || product.getProductCount()<0){
//			return responseError(-1, "error_empty_product_count");
//		}
//		
//		product.setUserId(this.getCurrentUserId());
//		Shop shop = shopService.getByUserId(this.getCurrentUserId());
//		if(shop==null){
//			return responseError(-1, "error_illegal");
//		}
//		
//		product.setShopId(shop.getId());
//		product.setProductStatus(Constants.PRODUCT_WAIT);
//		int count = productService.save(product);
//		if(count==0){
//			return responseError(-1, "save_failed");
//		}
//		
//		String fileName="";
//		Map<String, Object> resMap = null;
//		if(indexFile!=null){
//		    resMap = imageFileService.uploadBase64Image(indexFile);
//		    fileName = resMap.get("errorInfo").toString(); 
//		    if(resMap.get("errorNo").equals(0)){
//				imageService.saveProductIndexImg(product, fileName, 1);	
//			} else {
//				return resMap;
//			}
//	    }
//		for(MultipartFile file : listFile){
//			resMap = imageFileService.uploadImage(file);
//			fileName = resMap.get("errorInfo").toString(); 
//		    if(resMap.get("errorNo").equals(0)){
//				imageService.saveProductImg(product, fileName, 0);	
//			} else {
//				return resMap;
//			}
//		}
//		
//		return responseOK("save_success");
//	}
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteProductById")
	public Map<String, Object> deleteProductById(String idList) {
		if(StringUtils.isBlank(idList)){
			return responseOK1("");	
		}
		String arr[] = idList.split(",");
		for(String str : arr){
			if(StringUtils.isNotBlank(str.trim()) && RegularUtil.isNumeric(str.trim())){
				Long productId = Long.valueOf(str.trim());
				productService.deleteByIdAndUserId(productId);	
			}
		}
		return responseOK1("");
	}
	/**
	 * 下架商品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/offProductById")
	public Map<String, Object> offProductById(String idList) {
		return productService.updateProductStatus(idList, Constants.PRODUCT_OFF);
	}
	
	/**
	 * 上架商品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/onProductById")
	public Map<String, Object> onProductById(String idList) {
		return productService.updateProductStatus(idList, Constants.PRODUCT_ON);
	}
	
	/*
	 * **************************************************
	 * *********************产品留言************************
	 * **************************************************
	 */
	
	/**
	 * 商品留言页面 
	 * @param model
	 * @param type 1:留言 2：私信
	 * @return
	 */
	@RequestMapping(value="/messagePage", method = RequestMethod.GET)
	public ModelAndView messagePage(ModelAndView model, String type,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		pageSize = 10;
		PageUtil<Map<String, Object>> page = new PageUtil<Map<String,Object>>();
		
		if(type.equals("1")){//产品评论
			page = getProductCommentList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/sell/my_product_comment");
		} else if(type.equals("2")){//私信
			page = getMessageList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/sell/my_message");
		} else if(type.equals("3")){//新闻资讯评论
			page = getNewsCommentList(this.getCurrentUserId(), pageNo, pageSize);
			model.setViewName("shop/sell/my_news_comment");
		} else {
			model.setViewName("shop/404");
			return model;
		}
		
		model.addObject("type", type);
		model.addObject("page", page);
		return model;
	}
	/**
	 * 私信列表
	 * @param message
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getMessageList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getMessageList(Long userId, int pageNo, Integer pageSize) {
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
	 * @param msg
	 * @param productId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/messageReply")
	public Map<String, Object> messageReply(String msgContent, String type,Long productId, Long messageId, Long commentId, Long replyToId) {
		if(StringUtils.isBlank(msgContent)){
			return responseError(-1, "error_empty_content");
		}
		if(type.equals("1")){//留言
			Comment comment = new Comment();
			comment.setContext(msgContent);
			comment.setDate(new Date());
			comment.setCommentId(commentId);
			comment.setUserId(this.getCurrentUserId());
			comment.setProductId(productId);
			comment.setStatus(Constants.COMMENT_ON); 
			comment.setReplyToUserId(replyToId);
			commentService.insert(comment);
		} else if(type.equals("2")){//私信
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
		return responseOK1("");
	}
	

	/*
	 * **************************************************
	 * 
	 * *********************商家资料************************
	 * 
	 * **************************************************
	 */
	/**
	 * 商家信息页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/merchantPage", method = RequestMethod.GET)
	public ModelAndView merchantPage(ModelAndView model){
		model.setViewName("shop/sell/merchantCA");
		
		Shop shop = shopService.getCurrentUserShop();
		
		model.addObject("shop", shop);
		model.addObject("user", this.getCurrentUser());
		model.addObject("img", shop.getShopLogoImg());
		return model;
	}
	/**
	 * 保存商家基本信息
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/doSaveMerchant", method = RequestMethod.POST)
	public Map<String, Object> doSaveMerchant(Shop shop,
			@RequestParam(name = "file",value="file", required = false) MultipartFile attachFile){
		if(StringUtils.isBlank(shop.getShopTitle())){
			return responseError(-1, "error_empty_shop_name");
		}
		
		boolean exitShop = shopService.exitShop(shop.getId());
		if(!exitShop){
			return responseError(-1, "error_illegal");
		}
		
		if(attachFile!=null){
		    Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
		    String fileName = resMap.get("errorInfo").toString(); 
		    if(!resMap.get("errorNo").equals(0)){
		    	return resMap;
		    }
		    String uploadPath = PropertiesUtil.get("upload_path_save"); 
		    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
		    shop.setShopLogoImg(uploadPath+fileName);
	    }
		
		shopService.save(shop);
		
		return responseOK1("");
	}
	/**
	 * 营业执照页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/businessLicense", method = RequestMethod.GET)
	public ModelAndView businessLicense(ModelAndView model){
		model.setViewName("shop/sell/business_license");
		Shop shop = shopService.getCurrentUserShop();
		model.addObject("shop", shop);
		return model;
	}
	/**
	 * 保存营业执照
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveBusinessLicense", method = RequestMethod.POST)
	public Map<String, Object> saveBusinessLicense(Shop shop,
			@RequestParam(name = "file",value="file", required = false) MultipartFile attachFile){
		boolean exitShop = shopService.exitShop(shop.getId());
		if(!exitShop){
			return responseError(-1, "error_illegal");
		}
		
		if(attachFile!=null){
		    Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
		    String fileName = resMap.get("errorInfo").toString(); 
		    if(!resMap.get("errorNo").equals(0)){
		    	return resMap;
		    }
		    String uploadPath = PropertiesUtil.get("upload_path_save"); 
		    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
		    shop.setShopLicenesImg(uploadPath+fileName);
	    }
		shopService.save(shop);
		return responseOK1("");
	}
	/**
	 * 商家介绍页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/merchantIntroduce", method = RequestMethod.GET)
	public ModelAndView merchantIntroduce(ModelAndView model){
		model.setViewName("shop/sell/merchant_introduce");
		Shop shop = shopService.getCurrentUserShop();
		model.addObject("shop", shop);
		return model;
	}
	/**
	 * 保存商家介绍
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveMerchantIntroduce", method = RequestMethod.POST)
	public Map<String, Object> saveMerchantIntroduce(Shop shop,
			@RequestParam(name = "file",value="file", required = false) MultipartFile attachFile){
		boolean exitShop = shopService.exitShop(shop.getId());
		if(!exitShop){
			return responseError(-1, "error_illegal");
		}
		
		if(attachFile!=null){
		    Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
		    String fileName = resMap.get("errorInfo").toString(); 
		    if(!resMap.get("errorNo").equals(0)){
		    	return resMap;
		    }
		    String uploadPath = PropertiesUtil.get("upload_path_save"); 
		    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
		    shop.setShopImg(uploadPath+fileName);
	    }
		shopService.save(shop);
		return responseOK1("");
	}
	/**
	 * 上架资质页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/businessQualif", method = RequestMethod.GET)
	public ModelAndView businessQualif(ModelAndView model){
		model.setViewName("shop/sell/shelf_qualification");
		Shop shop = shopService.getCurrentUserShop();
		model.addObject("shop", shop);
		return model;
	}
	/**
	 * 保存上架资质
	 * @param model
	 * @param shop
	 * @param attachFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveShelfQualification", method = RequestMethod.POST)
	public Map<String, Object> saveShelfQualification(Shop shop,
			@RequestParam(name = "file",value="file", required = false) MultipartFile attachFile){
		boolean exitShop = shopService.exitShop(shop.getId());
		if(!exitShop){
			return responseError(-1, "error_illegal");
		}
		
		if(attachFile!=null){
		    Map<String, Object> resMap = imageFileService.uploadImage(attachFile);
		    String fileName = resMap.get("errorInfo").toString(); 
		    if(!resMap.get("errorNo").equals(0)){
		    	return resMap;
		    }
		    String uploadPath = PropertiesUtil.get("upload_path_save"); 
		    uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"/";
		    shop.setShelfQualificationImg(uploadPath+fileName);
	    }
		shopService.save(shop);
		return responseOK1("");
	}
	
	//论坛
	/**
	 * 发帖回复页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/postReply", method = RequestMethod.GET)
	public ModelAndView postReply(ModelAndView model, Thread thread,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize){
		thread.setUserId(this.getCurrentUserId());
		PageUtil<Map<String, Object>> page = threadService.getSellThreadList(thread, pageNo, pageSize);
		
		model.addObject("page", page);
		model.setViewName("shop/sell/posted_reply");
		return model;
	}
	
	/**
	 * 删除产品图片
	 * @param imgId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteByParm", method = RequestMethod.GET)
	public Map<String, Object> deleteByParm(Long productId, Long imgId){
		if(productId==null || imgId==null){
			return responseError(-1, "error_unknow");
		}
		imageService.deleteByIdAndProductId(imgId,productId);	
		return responseOK1("");
	}
	@ResponseBody
	@RequestMapping(value="/deleteImg", method = RequestMethod.GET)
	public Map<String, Object> deleteImg(Long id, String type){
		if(id==null || StringUtils.isBlank(type)){
			return responseError(-1, "error_unknow");
		}

		Shop shop = new Shop();
		shop.setId(id);
		if(type.equals("logo")){
			shop.setShopLogoImg("");
		} else if(type.equals("license")){
			shop.setShopLicenesImg("");
		} else if(type.equals("shopimg")){
			shop.setShopImg("");
		} else if(type.equals("shelfQualificationImg")){
			shop.setShelfQualificationImg("");
		}
		shopService.update(shop);
		return responseOK1("");
	}
	@RequestMapping(value="/goCutPicture", method = RequestMethod.GET)
	public ModelAndView goCutPicture(){
		return new ModelAndView("shop/sell/cutPicture");
	}
}