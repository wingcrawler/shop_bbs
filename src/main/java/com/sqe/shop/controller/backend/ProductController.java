package com.sqe.shop.controller.backend;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.servlet.ModelAndView;

import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.model.Shop;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.service.cached.CachedService;
import com.sqe.shop.util.PageUtil;
import com.sqe.shop.util.RegularUtil;

@Controller
@RequestMapping("/backend/product")
public class ProductController extends BaseBackendController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private CachedService cachedService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/product/list");
		/*Shop shop = new Shop();
		shop.setShopStatus(Constants.STORE_ON);
		PageUtil<Shop> shops = shopService.getBeanListByParm(shop, 0, -1);
		model.addObject("shopList", shops.getList());*/
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/product/edit");
		
		// 产品一级分类
		ProductType type = new ProductType();
		type.setTypeLevel(1);
		PageUtil<ProductType> typeOnePage = productTypeService.getBeanListByParm(type, 1, -1);
		if (cachedService.getLang().equals("zh")) {
			for (ProductType pt : typeOnePage.getList()) {
				pt.setTypeName(pt.getTypeNameCh());
			}
		}
		model.addObject("typeList", typeOnePage.getList());
		
		
		if(id!=null){
			Product entity = productService.getById(id);

			if(entity!=null){
				// 产品二级分类
				type.setTypeLevel(2);
				type.setParentId(entity.getProductTypeId());
				PageUtil<ProductType> typeTwoPage = productTypeService.getBeanListByParm(type, 1, -1);
				if (cachedService.getLang().equals("zh")) {
					for (ProductType pt : typeTwoPage.getList()) {
						pt.setTypeName(pt.getTypeNameCh());
					}
				}
				model.addObject("subtypeList", typeTwoPage.getList());
				
				/*entity.setStatusName(Constants.getProductStatus(entity.getProductStatus()));*/
				ProductType productType = productTypeService.getById(entity.getProductTypeId());
				if (productType!=null) {
					entity.setTypeName(productType.getTypeNameCh()+"/"+productType.getTypeName());	
				}
				
				Shop shop = shopService.getById(entity.getShopId());
				if(shop!=null){
					entity.setShopName(shop.getShopTitle());	
				}
				
				List<Image> images = imageService.getByProductId(entity.getId());
				model.addObject("imgList", images);
			}
			model.addObject("entity", entity);
		}
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/getList", method = RequestMethod.GET)
	public Map<String, Object> getList(Product product, String shopName,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  
			@RequestParam(name="pageSize", defaultValue="10") int pageSize) throws UnsupportedEncodingException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		
		if(StringUtils.isNotBlank(shopName)){
			shopName = URLDecoder.decode(shopName, "utf8");
			Shop shop = shopService.getByName(shopName);
			product.setShopId(shop.getId());
		}
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Product product) {
		Map<String, Object> checkMap = checkProduct(product);
		if (!checkMap.get(ERROR_NO).equals(ERRORCODE_SUCCESS)) {
			return checkMap;
		}
		productService.save(product);
		return responseOK("save_success");
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
	
	@ResponseBody
	@RequestMapping(value="/doDelete", method = RequestMethod.GET)
	public Map<String, Object> doDelete(Long id) {
		if(id==null){
			return responseError(-1, "error_no_item");
		}
		int i = productService.delete(id);
		if(i==0){
			return responseError(-1, "error_del_failed");
		}
		return responseOK("op_success");
	}

}
