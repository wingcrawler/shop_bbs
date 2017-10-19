package com.sqe.shop.service.biz;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseCommon;
import com.sqe.shop.common.Constants;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.Shop;
import com.sqe.shop.service.CommentService;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.MessageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;

@Component
public class BizProductService extends BaseCommon {
	
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

	public Map<String, Object> getProductDetail(Long productId, Product product) {
		Map<String, Object> resMap = this.responseOK1("");
		resMap.put("product", product);
		
		//产品对应的商家
		Shop shop =  shopService.getById(product.getShopId());
		resMap.put("shop", shop);
		
		//查询商品图片
		Image image = new Image();
		image.setProductId(productId);
		PageUtil<Image> imgPage = imageService.getBeanListByParm(image, 0, -1);
		resMap.put("imgList", imgPage.getList());
		
		//相关产品
		product = new Product();
		product.setProductTypeId(product.getProductTypeId());
		product.setProductStatus(Constants.PRODUCT_ON);
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, 1, 3);
		resMap.put("relateList", page.getList());
		
		//产品评论列表
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("productId", productId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> commentPage = commentService.getProductCommentListByParm(parmMap, 1, 10);
		resMap.put("commentPage", commentPage);
		
		return resMap;
	}

	public Map<String, Object> getProductComment(Long productId, int pageNo, int pageSize) {
		Map<String, Object> resMap = this.responseOK1("");
		//产品评论列表
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("productId", productId);
		parmMap.put("nullCommentId", true);
		parmMap.put("orderby", "c.date desc");
		PageUtil<Map<String, Object>> commentPage = commentService.getProductCommentListByParm(parmMap, pageNo, pageSize);
		resMap.put("commentPage", commentPage);
		Product product = productService.getById(productId);
		if(product==null){
			product = new Product();
			product.setId(productId);
			product.setProductName("");
		}
		resMap.put("product", product);
		return resMap;
	}
	
	
	
}
