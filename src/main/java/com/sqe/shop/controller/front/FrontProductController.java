package com.sqe.shop.controller.front;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.common.Constants;
import com.sqe.shop.controller.base.BaseFrontController;
import com.sqe.shop.model.Message;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.biz.BizProductService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;

@Controller
@RequestMapping("/product")
public class FrontProductController extends BaseFrontController {
	
	private static final Logger logger = LoggerFactory.getLogger(FrontProductController.class);
	
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
	private BizProductService bizProductService;
	
	/**
	 * 产品列表页
	 * @param model
	 * @param parentType
	 * @param childType
	 * @param typeName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="list")
	public ModelAndView list(ModelAndView model, Long parentType, Long childType,String typeName,String searchText,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="12") int pageSize) throws UnsupportedEncodingException {
		/*pageSize=12;*/
		model.addObject("parentType", parentType);
		
		Product product = new Product();
		ProductType productType = new ProductType();
		
		//没有一级分类 选取默认分类
		/*if(parentType==null && childType==null){
			productType = new ProductType();
			productType.setTypeLevel(1);
			PageUtil<ProductType> typePage = productTypeService.getBeanListByParm(productType, 1, -1);
			if(typePage!=null && !typePage.getList().isEmpty()){
				productType = typePage.getList().get(0);
				product.setProductTypeId(productType.getId());
			}
			model.setViewName("shop/404");
			return model;
		}*/
		
		//查询条件里拼接是一级分类还是二级分类
		if(parentType!=null){
			product.setProductTypeId(parentType);
		}
		if(childType!=null){
			product.setProductSubtypeId(childType);
		}
		product.setProductStatus(Constants.PRODUCT_ON);
		if(StringUtils.isNotBlank(searchText)){
			searchText = URLDecoder.decode(searchText, UTF8);
			product.setProductName(searchText);
		}
		//查询
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		model.addObject("page", page);
		
		//title参数国际化
		ProductType type= new ProductType();
		if(childType!=null){
			type = productTypeService.getById(childType);
		} else if(parentType!=null) {
			type = productTypeService.getById(parentType);
		} else {
			productType.setTypeNameCh("产品列表");
			productType.setTypeName("Product list");
			type = productType;
		}
		if(cachedService.getLang().equals("zh")){
			model.addObject("title", type.getTypeNameCh()==null?"":type.getTypeNameCh());
		} else {
			model.addObject("title", type.getTypeName()==null?"":type.getTypeName());
		}
		
		model.setViewName("shop/product/product_list");
		return model;
	}
	
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
		if(!product.getProductStatus().equals(Constants.PRODUCT_ON)){
			model.setViewName("shop/404");
			return model;
		}
		
		model.addAllObjects(bizProductService.getProductDetail(productId, product));
		
		model.setViewName("shop/product/single");
		return model;
	}
	
	/**
	 * 产品私信
	 * @param model
	 * @param message
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="postMessage")
	public Map<String, Object> postMessage(ModelAndView model, Message message) {
		if(message.getProductId()==null){
			return responseError(-1, "error_unknow");
		}
		
		Product product = productService.getById(message.getProductId());
		if(product==null){
			return responseError(-1, "error_unknow");
		}
		
		message.setReceiveId(product.getUserId());
		message.setMessageStatus(Constants.MSG_ON);
		message.setPostId(this.getCurrentUserId());
		message.setCreateTime(new Date());
		messageService.insert(message);
		return responseOK("send_success");
	}
	
	@RequestMapping(value="search", method = RequestMethod.POST)
	public ModelAndView search(ModelAndView model, String searchText,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="12") int pageSize) {
		if(StringUtils.isBlank(searchText)){
			model.setViewName("redirect:/shopIndex");
			return model;	
		}
		pageSize=12;
		try {
			model.addObject("searchText", URLDecoder.decode(searchText, UTF8));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//搜索产品
		Product product = new Product();
		product.setProductName(searchText);
		PageUtil<Map<String, Object>> productPage = productService.getMapListByParm(product, pageNo, pageSize);
		model.addObject("page", productPage);
		
		model.setViewName("shop/product/product_search");
		return model;
	}
	
	@RequestMapping(value="searchs", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil<Map<String, Object>>  searchs(String searchText,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="12") int pageSize) {
		PageUtil<Map<String, Object>> productPage=new PageUtil<Map<String, Object>>();
		if(StringUtils.isBlank(searchText)){
			return null;	
		}
		pageSize=12;	
		//搜索产品
		Product product = new Product();
		product.setProductName(searchText);
		productPage = productService.getMapListByParm(product, pageNo, pageSize);
		
		return productPage;
	}
	@RequestMapping(value="searchGet", method = RequestMethod.GET)
	public ModelAndView searchGet(ModelAndView model, String productName,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="12") int pageSize) {
		if(StringUtils.isBlank(productName)){
			model.setViewName("redirect:/shopIndex");
			return model;	
		}
		pageSize=2;
		model.addObject("productName", productName);
		
		Product product = new Product();
		product.setProductName(productName);
		PageUtil<Map<String, Object>> productPage = productService.getMapListByParm(product, pageNo, pageSize);
		model.addObject("page", productPage);
		
		model.setViewName("shop/product/product_search");
		return model;
	}
	
}
