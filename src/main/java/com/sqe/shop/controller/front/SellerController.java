package com.sqe.shop.controller.front;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
	
	//商品管理
	/**
	 * 商家产品列表页
	 * @return
	 */
	@RequestMapping(value="/productListPage", method = RequestMethod.GET)
	public ModelAndView productListPage(ModelAndView model, Product product,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
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
	@RequiresRoles(value="sell")
	public PageUtil<Map<String, Object>> getProductList(Product product,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		product.setUserId(this.getCurrentUserId());
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		
		if(page.getList().size()>0){
			String productId = "";
			List<Image> images = null;
			for(Map<String, Object> map : page.getList()){
				productId = map.get("id").toString();
				images = imageService.getByProductId(Long.valueOf(productId));
				map.put("img", images.get(0));
			}
		}
		
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
		
		List<Image> images = imageService.getByProductId(entity.getId());
		model.addObject("img", images.get(0));
		images.remove(0);
		model.addObject("imgList", images);
		
		model.addObject("imgCount", images.size());
		model.addObject("inputCount", 6-images.size());
		
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
		resMap.put("typeTwo", typeTwo.getTypeName());
		
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
		int count = productService.save(product);
		if(count==0){
			return responseError(-1, "save_failed");
		}
		
		String indexFileName="";
		Map<String, Object> resMap = null;
		if(indexFile!=null){
		    resMap = imageFileService.uploadImage(indexFile);
		    indexFileName = resMap.get("errorInfo").toString(); 
		    if(StringUtils.isNotBlank(indexFileName)){
				imageService.saveProductImg(product, indexFileName, 1);	
			}
	    }
		for(MultipartFile file : listFile){
			resMap = imageFileService.uploadImage(file);
		    indexFileName = resMap.get("errorInfo").toString(); 
		    if(StringUtils.isNotBlank(indexFileName)){
				imageService.saveProductImg(product, indexFileName, 0);	
			}
		}
		
		return responseOK("save_success");
	}
	
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteProductById", method = RequestMethod.GET)
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
	@RequestMapping(value="/offProductById", method = RequestMethod.POST)
	public Map<String, Object> offProductById(String idList) {
		return productService.updateProductStatus(idList, Constants.PRODUCT_OFF);
	}
	
	/**
	 * 上架商品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/onProductById", method = RequestMethod.POST)
	public Map<String, Object> onProductById(String idList) {
		return productService.updateProductStatus(idList, Constants.PRODUCT_ON);
	}
	
	//私信
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
		PageUtil<Map<String, Object>> page = new PageUtil<Map<String,Object>>();
		if(type.equals("1")){//产品留言
			page = getCommentList(this.getCurrentUserId(), pageNo, pageSize);
		} else if(type.equals("2")){//私信
			page = getMessageList(this.getCurrentUserId(), pageNo, pageSize);
		} else {
			model.setViewName("shop/404");
			return model;
		}
		
		model.addObject("type", type);
		model.addObject("page", page);
		model.setViewName("shop/sell/leave_message");
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
		PageUtil<Map<String, Object>> msgPage = messageService.getSellerMessageListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}
	/**
	 * 产品评论列表
	 * @param comment
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCommentList", method = RequestMethod.GET)
	public PageUtil<Map<String, Object>> getCommentList(Long userId, int pageNo, Integer pageSize) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> msgPage = commentService.getSellerProductCommentListByParm(parmMap, pageNo, pageSize);
		return msgPage;
	}
	/**
	 * 回复私信或者产品留言
	 * @param msg
	 * @param productId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/messageReply", method = RequestMethod.GET)
	public Map<String, Object> messageReply(String msgContent, String type,Long productId, Long messageId, Long commentId, Long replyToId) {
		if(type.equals("1")){//留言
			Comment comment = new Comment();
			comment.setContext(msgContent);
			comment.setDate(new Date());
			comment.setCommentId(commentId);
			comment.setUserId(this.getCurrentUserId());
			comment.setProductId(productId);
			comment.setStatus(Constants.COMMENT_ON); 
			commentService.insert(comment);
		} else if(type.equals("2")){//私信
			Message message = new Message();
			message.setMessageContext(msgContent);
			message.setCreateTime(new Date());
			message.setProductId(productId);
			message.setPostId(this.getCurrentUserId());
			message.setReceiveId(replyToId);
			messageService.insert(message);
		} else {
			responseError(-1, "error_illegal");
		}
		return responseOK1("");
	}
	
	//商家资料
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
			@RequestParam(name = "attachFile",value="attachFile", required = false) MultipartFile attachFile){
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
		    shop.setShopImg(uploadPath+fileName);
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
			@RequestParam(name = "attachFile",value="attachFile", required = false) MultipartFile attachFile){
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
			@RequestParam(name = "attachFile",value="attachFile", required = false) MultipartFile attachFile){
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

}