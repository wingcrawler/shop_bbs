package com.sqe.shop.controller.backend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sqe.shop.controller.base.BaseBackendController;
import com.sqe.shop.model.Image;
import com.sqe.shop.model.Product;
import com.sqe.shop.model.ProductType;
import com.sqe.shop.model.Shop;
import com.sqe.shop.service.ImageService;
import com.sqe.shop.service.ProductService;
import com.sqe.shop.service.ProductTypeService;
import com.sqe.shop.service.ShopService;
import com.sqe.shop.util.PageUtil;

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
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("backend/product/list");
		Shop shop = new Shop();
		shop.setShopStatus(Constants.STORE_ON);
		PageUtil<Shop> shops = shopService.getBeanListByParm(shop, 0, -1);
		model.addObject("shopList", shops.getList());
		return model;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(Long id) {
		ModelAndView model = new ModelAndView("backend/product/edit");
		if(id!=null){
			Product entity = productService.getById(id);
			if(entity!=null){
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
	public Map<String, Object> getList(Product product,
			@RequestParam(name="pageNo", defaultValue="1") int pageNo,  @RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		PageUtil<Map<String, Object>> page = productService.getMapListByParm(product, pageNo, pageSize);
		resMap.put("list", page.getList());
		resMap.put("page", page);
		return resMap;
	}
	
	@ResponseBody
	@RequestMapping(value="/doSave", method = RequestMethod.POST)
	public Map<String, Object> save(Product product) {
		productService.save(product);
		return responseOK("save_success");
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
