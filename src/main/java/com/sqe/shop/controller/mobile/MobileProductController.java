package com.sqe.shop.controller.mobile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqe.shop.controller.base.BaseFrontController;
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
@RequestMapping("/h5/product")
public class MobileProductController extends BaseFrontController {
	
	@Autowired
	private BizProductService bizProductService;
	
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
	 * 产品分类
	 * @param productTypeId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> index(String productTypeId,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="12") int pageSize) {
		Map<String, Object> resMap = this.responseOK1("");
		pageSize=12;
		
		resMap.put("productTypeId", productTypeId);
		
		//产品分类列表
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("typeLevel", 1);
		List<ProductType> productTypes = productTypeService.getBeanListByParm("ProductTypeMapper", paramMap);
		if(cachedService.getLang().equals("zh")){
			for(ProductType p : productTypes){
				p.setTypeName(p.getTypeNameCh());
			}
		}
		resMap.put("productTypeList", productTypes);
		
		Product product = new Product();
		product.setProductStatus(1);
		if(StringUtils.isBlank(productTypeId)){
			//热门商品
			PageUtil<Map<String, Object>> hotProductPage = productService.getHotProductList(product, 1, 9); 
			resMap.put("productList", hotProductPage.getList());
		} else {
			//根据一级分类查询商品
			product.setProductTypeId(Long.valueOf(productTypeId));
			PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
			resMap.put("page", page);
		}
		
		return resMap;
	}
	
	/**
	 * 根据产品分类查询
	 * @param productTypeId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getByProductType")
	public Map<String, Object> getByProductType(String productTypeId,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="12") int pageSize) {
		if(StringUtils.isBlank(productTypeId)){
			return this.responseError1(-1, "product type id is blank");
		}
		
		Map<String, Object> resMap = this.responseOK1("");
		pageSize=12;
		
		resMap.put("productTypeId", productTypeId);
		
		Product product = new Product();
		product.setProductStatus(1);
		//根据一级分类查询商品
		product.setProductTypeId(Long.valueOf(productTypeId));
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		resMap.put("page", page);
		
		return resMap;
	}
	
	/**
	 * 产品详情
	 * @param productId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProductDetail")
	public Map<String, Object> getProductDetail(Long productId){
		if(productId == null){
			return this.responseError1(-1, "product id is blank");
		}
		Product product = productService.getById(productId);
		
		Map<String, Object> resMap = bizProductService.getProductDetail(productId, product);
		return resMap;
	}
	
	/**
	 * 产品评论
	 * @param productId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProductComment")
	public Map<String, Object> getProductComment(Long productId,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize){
		if(productId == null){
			return this.responseError1(-1, "product id is blank");
		}
		return bizProductService.getProductComment(productId, pageNo, pageSize);
	}
	
}
